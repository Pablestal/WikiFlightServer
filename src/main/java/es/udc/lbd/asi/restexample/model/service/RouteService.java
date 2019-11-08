package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.domain.Route;
import es.udc.lbd.asi.restexample.model.repository.PilotDAO;
import es.udc.lbd.asi.restexample.model.repository.RouteDAO;
import es.udc.lbd.asi.restexample.model.service.dto.RouteDTO;

@Service
@Transactional(readOnly= true, rollbackFor = Exception.class)
public class RouteService {

	@Autowired 
	private RouteDAO routeDAO;
	
	@Autowired
	private PilotDAO pilotDAO;
	
	public List<RouteDTO> findAll() {
		return routeDAO.findAll().stream().map(route -> new RouteDTO(route)).collect(Collectors.toList());
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
		Route bdRoute = new Route(route.getName(), route.getIsPublic(), route.getPublicationDay(), route.getDescription(),
				route.getPath(), route.getTakeoffAerodrome(), route.getLandingAerodrome(), route.getPilot(), route.getFlights(),
				route.getComments(), route.getImages());
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
}
