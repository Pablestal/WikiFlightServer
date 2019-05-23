package es.udc.lbd.asi.restexample.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.repository.PilotDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.dto.PilotDTO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTOPrivate;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTOPublic;
import es.udc.lbd.asi.restexample.security.SecurityUtils;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Autowired 
    private PilotDAO pilotDAO;
    
    public List<UserDTOPublic> findAll() {
        return userDAO.findAll().stream().map(user -> new UserDTOPublic(user)).collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    public void registerAdmin(String login, String password, String email) throws UserLoginExistsException {
    	if (userDAO.findByLogin(login) != null) {
            throw new UserLoginExistsException("User login " + login + " already exists");
        }
    	
    	String encryptedPassword = passwordEncoder.encode(password);
    	User admin = new User(login, encryptedPassword, UserAuthority.ADMIN, email);
    	
    	userDAO.save(admin);
    }
    
    @Transactional(readOnly = false)
    public PilotDTO registerPilot(String login, String password, String name, String surname1, String surname2, 
			String email, String country, String city, LocalDate birthDate) throws UserLoginExistsException {
        if (userDAO.findByLogin(login) != null) {
            throw new UserLoginExistsException("User login " + login + " already exists");
        }

        
        String encryptedPassword = passwordEncoder.encode(password);
        
        Pilot  pilot = new Pilot(login, encryptedPassword, UserAuthority.PILOT , name, surname1, surname2, 
        		email, country, city, birthDate, LocalDate.now());
     

        pilotDAO.save(pilot);
        return new PilotDTO(pilot);
    }

    public UserDTOPrivate getCurrentUserWithAuthority() {
        String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null) {
            return new UserDTOPrivate(userDAO.findByLogin(currentUserLogin));
        }
        return null;
    }
}
