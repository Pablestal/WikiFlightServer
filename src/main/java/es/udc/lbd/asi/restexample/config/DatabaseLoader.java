package es.udc.lbd.asi.restexample.config;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Aircraft;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
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
    	
    	// USERS
    	userService.registerAdmin("pablo", "pablo", "pablestal@gmail.com");
    	userService.registerAdmin("pepe", "pepe", "pepitopepe@gmail.com");
        userService.registerPilot("antonio", "antonio", "Antonio", "Lorenzo", "Fernandez", "antonitotoni@gmail.com",
        		"España", "Verin", date);
        
        //AIRCRAFTS
        Aircraft aircraft1 = new Aircraft("Airbus", "A380");
        aircraftService.save(aircraft1);
        Aircraft aircraft2 = new Aircraft("Boeing", "747-8");
        aircraftService.save(aircraft2);
    }
}
