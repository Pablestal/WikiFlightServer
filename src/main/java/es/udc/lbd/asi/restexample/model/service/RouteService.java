package es.udc.lbd.asi.restexample.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.domain.Route;
import es.udc.lbd.asi.restexample.model.repository.PilotDAO;
import es.udc.lbd.asi.restexample.model.repository.RouteDAO;
import es.udc.lbd.asi.restexample.model.service.dto.RouteDTO;
import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;

@Service
@Transactional(readOnly= true, rollbackFor = Exception.class)
public class RouteService {

	@Autowired 
	private RouteDAO routeDAO;
	
	@Autowired
	private PilotDAO pilotDAO;
	
	@Autowired
	private es.udc.lbd.asi.restexample.config.Properties properties;
	 
	private Path location;
    
    @PostConstruct
    public void initUserService() {
        this.location = Paths.get(properties.getResourcePath());
        try {
            Files.createDirectories(this.location);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public List<RouteDTO> findAll() {
		return routeDAO.findAll().stream().map(route -> new RouteDTO(route)).collect(Collectors.toList());
	}
	
	public List<RouteDTO> findPublic() {
		return routeDAO.findPublic().stream().map(route -> new RouteDTO(route)).collect(Collectors.toList());
	}
	
	public RouteDTO findById(Long id) {
		return new RouteDTO(routeDAO.findById(id));
	}
	
	public List<RouteDTO> findByPilot(String login) {
		Pilot pilot = pilotDAO.findByLogin(login);
		return routeDAO.findByPilot(pilot).stream().map(route -> new RouteDTO(route)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = false)
	public RouteDTO save(Route route) {
		
		Route bdRoute = new Route(route.getName(), route.getIsPublic(), LocalDate.now(), route.getDescription(),
				route.getTakeoffAerodrome(), route.getLandingAerodrome(), route.getPilot(), 
				 route.getImages());
		routeDAO.save(bdRoute);
		return new RouteDTO(bdRoute);
	}
	
	@Transactional(readOnly = false)
	public RouteDTO update(Route route) {
		Route bdRoute = routeDAO.findById(route.getId());
		bdRoute.setName(route.getName());
		bdRoute.setIsPublic(route.getIsPublic());
		bdRoute.setPublicationDay(route.getPublicationDay());
		bdRoute.setDescription(route.getDescription());
		bdRoute.setPath(route.getPath());
		bdRoute.setTakeoffAerodrome(route.getTakeoffAerodrome());
		bdRoute.setLandingAerodrome(route.getLandingAerodrome());
		bdRoute.setPilot(route.getPilot());
		bdRoute.setFlights(route.getFlights());
		bdRoute.setComments(route.getComments());
		bdRoute.setImages(route.getImages());
		
		routeDAO.save(bdRoute);
		return new RouteDTO(bdRoute);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		routeDAO.deleteById(id);
	}
	
	public void parsePathFile(MultipartFile file, Long id) throws IOException {
		
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
	    String filepath = location.toString();
	    
	    List<Coordinate> coords = new ArrayList<>();
	    
	    GPX.read(filepath + "/" + filename).tracks()
	    .flatMap(Track::segments)
	    .flatMap(TrackSegment::points)
	    .forEach(x -> coords.add(new Coordinate(x.getLatitude().doubleValue(), x.getLongitude().doubleValue())));
      
        Coordinate[] coordsArray = new Coordinate[coords.size()];
        coordsArray = coords.toArray(coordsArray);
        
        PrecisionModel pm = new PrecisionModel();
	    LineString routePath = new GeometryFactory(pm, 4326).createLineString(coordsArray);
	    
	    routeDAO.findById(id).setPath(routePath);
	    System.out.println("ROUTE: "+ routeDAO.findById(id).toString());
	}
	
	public void store(MultipartFile file) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        
        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new Exception(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.location.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (Exception e) {
            throw new Exception("Failed to store file " + filename, e);
        }
    }
	
}
