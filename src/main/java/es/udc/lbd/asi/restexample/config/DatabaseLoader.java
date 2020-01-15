package es.udc.lbd.asi.restexample.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;



import es.udc.lbd.asi.restexample.model.domain.Aerodrome;
import es.udc.lbd.asi.restexample.model.domain.Aircraft;
import es.udc.lbd.asi.restexample.model.domain.Comment;
import es.udc.lbd.asi.restexample.model.domain.Flight;
import es.udc.lbd.asi.restexample.model.domain.Image;
import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.domain.Route;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.repository.AerodromeDAO;
import es.udc.lbd.asi.restexample.model.repository.AircraftDAO;
import es.udc.lbd.asi.restexample.model.repository.CommentDAO;
import es.udc.lbd.asi.restexample.model.repository.ImageDAO;
import es.udc.lbd.asi.restexample.model.repository.PilotDAO;
import es.udc.lbd.asi.restexample.model.repository.RouteDAO;
import es.udc.lbd.asi.restexample.model.service.FlightService;
import es.udc.lbd.asi.restexample.model.service.UserService;
import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;

@Configuration
public class DatabaseLoader {
    private final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

   

    @Autowired
    private DatabaseLoader databaseLoader;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private FlightService flightService;

    @Autowired
    private AircraftDAO aircraftDAO;
    
    @Autowired
    private AerodromeDAO aerodromeDAO;
    
    @Autowired 
    private PilotDAO pilotDAO;
    
    @Autowired 
    private RouteDAO routeDAO;
    
    @Autowired
    private ImageDAO imageDAO;
    
    @Autowired
    private CommentDAO commentDAO;
    
    /*
     * Para hacer que la carga de datos sea transaccional, hay que cargar el propio
     * objeto como un bean y lanzar el método una vez cargado, ya que en el
     * PostConstruct (ni similares) se tienen en cuenta las anotaciones de
     * transaciones.
     */
    @PostConstruct
    public void init() throws IOException {
        try {
            databaseLoader.loadData();
        } catch (UserLoginExistsException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void loadData() throws UserLoginExistsException, IOException {
    	
    	// USERS
    	LocalDate date1 = LocalDate.of(1895, 9, 21);
    	LocalDate date2 = LocalDate.of(1867, 4, 16);
    	LocalDate date3 = LocalDate.of(1871, 8, 19);

    	
    	userService.registerAdmin("pablo", "pablo", "pablestal@gmail.com");
    	userService.registerAdmin("pepe", "pepe", "pepitopepe@gmail.com");
      
        userService.registerPilot("delacierva", "delacierva", "Juan", "de la Cierva", "y Codorníu", "delaciervajuan@gmail.com",
        		"España", "Murcia", date1); 
        userService.registerPilot("wilbur", "wilbur", "Wilbur", "Wright", null, "wilburwilbi@gmail.com",
        		"United States", "Millville", date2);
        userService.registerPilot("orville", "orville", "Orville", "Wright", null, "orvilleorvi@gmail.com",
        		"United States", "Dayton", date3);
        
        Pilot wil = pilotDAO.findByLogin("wilbur");
        Pilot orv = pilotDAO.findByLogin("orville");
        Pilot pil = pilotDAO.findByLogin("delacierva");
        Set<Pilot> piset = new HashSet<>();
        piset.add(wil);
        piset.add(orv);
        pil.setFollowers(piset);
        
        
        //AIRCRAFTS
        Aircraft aircraft1 = new Aircraft("Airbus", "A380");
        aircraftDAO.save(aircraft1);
        Aircraft aircraft2 = new Aircraft("Boeing", "747-8");
        aircraftDAO.save(aircraft2);
        Aircraft aircraft3 = new Aircraft("Boeing", "777-300");
        aircraftDAO.save(aircraft3);
        Aircraft aircraft4 = new Aircraft("Airbus", "A320neo");
        aircraftDAO.save(aircraft4);
        Aircraft aircraft5 = new Aircraft("Cessna", "140");
        aircraftDAO.save(aircraft5);
        
        //AERODROMES
        PrecisionModel pm = new PrecisionModel();
        Point p1 = new GeometryFactory(pm, 4326).createPoint(new Coordinate(43.301944, -8.377222));
        Point p2 = new GeometryFactory(pm, 4326).createPoint(new Coordinate(42.896333, -8.415145));
        Point p3 = new GeometryFactory(pm, 4326).createPoint(new Coordinate(41.296944, 2.078333));
        Point p4 = new GeometryFactory(pm, 4326).createPoint(new Coordinate(40.472222, -3.560833));
       
        Aerodrome aerodrome1 = new Aerodrome("LCG", "LECO", "Alvedro", "España", "A Coruña", 328.0, p1);
        aerodromeDAO.save(aerodrome1);
        Aerodrome aerodrome2 = new Aerodrome("SCQ", "LEST", "Lavacolla", "España", "Santiago de Compostela", 1213.0, p2);
        aerodromeDAO.save(aerodrome2);
        Aerodrome aerodrome3 = new Aerodrome("BCN", "LEBL", "Barcelona-El Prat", "España", "Barcelona", 12.0, p3);
        aerodromeDAO.save(aerodrome3);
        Aerodrome aerodrome4 = new Aerodrome("MAD", "LEMD", "Madrid-Barajas", "España", "Madrid", 2000.0, p4);
        aerodromeDAO.save(aerodrome4);

        
     // ROUTES
        LocalDate routeDate = LocalDate.now();
        
        List<Coordinate> coords = new ArrayList<>();

        GPX.read("./resources/images/route1PathFile.gpx").tracks()
	    .flatMap(Track::segments)
	    .flatMap(TrackSegment::points)
	    .forEach(x -> coords.add(new Coordinate(x.getLatitude().doubleValue(), x.getLongitude().doubleValue())));
      
        Coordinate[] coordsArray = new Coordinate[coords.size()];
        coordsArray = coords.toArray(coordsArray);
	    LineString routePath = new GeometryFactory(pm, 4326).createLineString(coordsArray);

        Route route1 = new Route("Route of the snowy mountains", true, routeDate, 
        		"This is the first route I upload and it is from a flight from Santiago to Barcelona made this last summer.",
        		routePath, aerodromeDAO.findById(2L), aerodromeDAO.findById(3L), 
        		pilotDAO.findByLogin("delacierva"));
        
        routeDAO.save(route1);
        
        Comment cmnt1 = new Comment("Nice route, I wish one day I will be there.", LocalDate.now(), routeDAO.findById(1L), 
        		pilotDAO.findByLogin("orville"));
        
        commentDAO.save(cmnt1);
        
        Point imgP1 = new GeometryFactory(pm, 4326).createPoint(new Coordinate(42.99003839253555, -6.357787884771825));
        Point imgP2 = new GeometryFactory(pm, 4326).createPoint(new Coordinate(43.05287113266024, -5.608520172536373));
        Point imgP3 = new GeometryFactory(pm, 4326).createPoint(new Coordinate(42.11987167346775, -0.4062194656580687));
        Point imgP4 = new GeometryFactory(pm, 4326).createPoint(new Coordinate(41.64656262791085, 1.1425781250000002));
        Point imgP5 = new GeometryFactory(pm, 4326).createPoint(new Coordinate(41.5525148446006, 1.630233773030341));
        Point imgP6 = new GeometryFactory(pm, 4326).createPoint(new Coordinate(41.57917236133973, 1.47216796875));

        
        Image img1 = new Image("Picos de Europa", "Beautifull snowed mountains.", imgP1, "http://localhost:8080/api/users/image/route1Image1.jpg",
        		routeDAO.findById(1L));
        Image img2 = new Image("Mountains", "Snow in mountains.", imgP2, "http://localhost:8080/api/users/image/route1Image2.jpg",
        		routeDAO.findById(1L));
        Image img3 = new Image("Huesca", "City of Huesca.", imgP3, "http://localhost:8080/api/users/image/route1Image2.jpg",
        		routeDAO.findById(1L));
        Image img4 = new Image("Tárrega", "City of Tárrega, a few minutes after Huesca.", imgP4, "http://localhost:8080/api/users/image/route1Image2.jpg",
        		routeDAO.findById(1L));
        Image img5 = new Image("Igualada", "City of Igualada, almost there.", imgP5, "http://localhost:8080/api/users/image/route1Image2.jpg",
        		routeDAO.findById(1L));
        Image img6 = new Image("Pirineos", "Pirineos on the horizont.", imgP6, "http://localhost:8080/api/users/image/route1Image2.jpg",
        		routeDAO.findById(1L));
        
        
        imageDAO.save(img1);
        imageDAO.save(img2);
        imageDAO.save(img3);
        imageDAO.save(img4);
        imageDAO.save(img5);
        imageDAO.save(img6);
        
        
        //FLIGHTS
        LocalDate dep_arrD = LocalDate.of(2019, 10, 22);
        LocalTime depT = LocalTime.of(12, 00);
        LocalTime arrT = LocalTime.of(13, 30);
        Long total = 90L;
        LocalTime set = LocalTime.of(1, 30);
        LocalTime met = LocalTime.of(0, 0);
        LocalTime mpt = LocalTime.of(0, 0);
        LocalTime night = LocalTime.of(0, 0);
        LocalTime ifr = LocalTime.of(0, 30);
        LocalTime pic = LocalTime.of(1, 30);
        LocalTime coop = LocalTime.of(0, 0);
        LocalTime dual = LocalTime.of(0, 0);
        LocalTime instr = LocalTime.of(0, 0);
        
        Flight flight1 = new Flight(dep_arrD, depT, dep_arrD, arrT, total, set, met, mpt, 1, 0, 1, 0, night, ifr, pic, coop, dual, instr, 
        		"Ruta por Coruña", "AA123", aerodromeDAO.findById(1L), aerodromeDAO.findById(1L), pilotDAO.findByLogin("delacierva"),
        		aircraftDAO.findById(1L), null);
        
        Flight flight2 = new Flight(dep_arrD, depT, dep_arrD, arrT, total, set, met, mpt, 1, 0, 1, 0, night, ifr, pic, coop, dual, instr, 
        		"De Santiago a Barcelona", "AA123", aerodromeDAO.findById(2L), aerodromeDAO.findById(3L), pilotDAO.findByLogin("delacierva"),
        		aircraftDAO.findById(2L), routeDAO.findById(1L));
        
        Flight flight3 = new Flight(dep_arrD, depT, dep_arrD, arrT, total, set, met, mpt, 1, 0, 1, 0, night, ifr, pic, coop, dual, instr, 
        		"De Coruña a Barcelona", "AA123", aerodromeDAO.findById(1L), aerodromeDAO.findById(3L), pilotDAO.findByLogin("delacierva"),
        		aircraftDAO.findById(1L), null);
        
        flightService.save(flight1);
        flightService.save(flight2);
        flightService.save(flight3);
        
        
    }
}
