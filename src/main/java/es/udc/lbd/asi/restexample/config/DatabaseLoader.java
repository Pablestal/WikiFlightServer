package es.udc.lbd.asi.restexample.config;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.annotation.PostConstruct;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;



import es.udc.lbd.asi.restexample.model.domain.Aerodrome;
import es.udc.lbd.asi.restexample.model.domain.Aircraft;
import es.udc.lbd.asi.restexample.model.domain.Flight;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.repository.AerodromeDAO;
import es.udc.lbd.asi.restexample.model.repository.AircraftDAO;
import es.udc.lbd.asi.restexample.model.repository.PilotDAO;
import es.udc.lbd.asi.restexample.model.service.FlightService;
import es.udc.lbd.asi.restexample.model.service.UserService;

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
    
    
    /*
     * Para hacer que la carga de datos sea transaccional, hay que cargar el propio
     * objeto como un bean y lanzar el método una vez cargado, ya que en el
     * PostConstruct (ni similares) se tienen en cuenta las anotaciones de
     * transaciones.
     */
    @PostConstruct
    public void init() {
        try {
            databaseLoader.loadData();
        } catch (UserLoginExistsException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void loadData() throws UserLoginExistsException {
    	
    	// USERS
    	LocalDate date = LocalDate.of(1946, 9, 11);
    	LocalDate date1 = LocalDate.of(1864, 9, 29);
    	LocalDate date2 = LocalDate.of(1867, 4, 16);
    	LocalDate date3 = LocalDate.of(1871, 8, 19);

    	
    	userService.registerAdmin("pablo", "pablo", "pablestal@gmail.com");
    	userService.registerAdmin("pepe", "pepe", "pepitopepe@gmail.com");
        userService.registerPilot("antonio", "antonio", "Antonio", "Lorenzo", "Fernandez", "antonitotoni@gmail.com",
        		"España", "Verin", date);
        userService.registerPilot("miguel", "miguel", "Miguel", "de Unamuno", "y Jugo", "miguelinmigue@gmail.com",
        		"España", "Bilbao", date1); 
        userService.registerPilot("wilbur", "wilbur", "Wilbur", "Wright", null, "wilburwilbi@gmail.com",
        		"United States", "Millville", date2);
        userService.registerPilot("orville", "orville", "Orville", "Wright", null, "orvilleorvi@gmail.com",
        		"United States", "Dayton", date3);
        
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
        		"Ruta por Coruña", "AA123", aerodromeDAO.findById(1L), aerodromeDAO.findById(1L), pilotDAO.findByLogin("miguel"),
        		aircraftDAO.findById(1L));
        
        Flight flight2 = new Flight(dep_arrD, depT, dep_arrD, arrT, total, set, met, mpt, 1, 0, 1, 0, night, ifr, pic, coop, dual, instr, 
        		"De Coruña a Santiago", "AA123", aerodromeDAO.findById(1L), aerodromeDAO.findById(2L), pilotDAO.findByLogin("miguel"),
        		aircraftDAO.findById(2L));
        
        Flight flight3 = new Flight(dep_arrD, depT, dep_arrD, arrT, total, set, met, mpt, 1, 0, 1, 0, night, ifr, pic, coop, dual, instr, 
        		"El de Antonio", "AA123", aerodromeDAO.findById(1L), aerodromeDAO.findById(2L), pilotDAO.findByLogin("antonio"),
        		aircraftDAO.findById(1L));
        
        flightService.save(flight1);
        flightService.save(flight2);
        flightService.save(flight3);

    }
}
