package es.udc.lbd.asi.restexample.config;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.geo.Point;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Aerodrome;
import es.udc.lbd.asi.restexample.model.domain.Aircraft;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.service.AerodromeService;
import es.udc.lbd.asi.restexample.model.service.AircraftService;
import es.udc.lbd.asi.restexample.model.service.UserService;

@Configuration
public class DatabaseLoader {
    private final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

    @Autowired
    private UserService userService;

    @Autowired
    private DatabaseLoader databaseLoader;

    @Autowired
    private AircraftService aircraftService;
    
    @Autowired
    private AerodromeService aerodromeService;
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
    	
    	LocalDate date = LocalDate.of(1946, 9, 11);
    	LocalDate date1 = LocalDate.of(1864, 9, 29);
    	
    	// USERS
    	userService.registerAdmin("pablo", "pablo", "pablestal@gmail.com");
    	userService.registerAdmin("pepe", "pepe", "pepitopepe@gmail.com");
        userService.registerPilot("antonio", "antonio", "Antonio", "Lorenzo", "Fernandez", "antonitotoni@gmail.com",
        		"España", "Verin", date);
        userService.registerPilot("miguel", "miguel", "Miguel", "de Unamuno", "y Jugo", "miguelinmigue@gmail.com",
        		"España", "Bilbao", date1);
        //AIRCRAFTS
        Aircraft aircraft1 = new Aircraft("Airbus", "A380");
        aircraftService.save(aircraft1);
        Aircraft aircraft2 = new Aircraft("Boeing", "747-8");
        aircraftService.save(aircraft2);
        
        //AERODROMES
        Point p1 = new Point(43.301944,  -8.377222);
        Aerodrome aerodrome1 = new Aerodrome("LCG", "LECO", "Alvedro", "España", "A Coruña", 328.0, p1);
        aerodromeService.save(aerodrome1);
        Point p2 = new Point(42.896333, -8.415145);
        Aerodrome aerodrome2 = new Aerodrome("SCQ", "LEST", "Lavacolla", "España", "Santiago de Compostela", 1213.0, p2);
        aerodromeService.save(aerodrome2);
    }
}
