package es.udc.lbd.asi.restexample.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.PasswordResetToken;
import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.repository.PasswordTokenDAO;
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
    private PasswordTokenDAO passwordTokenDAO;

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
    
    
    @Transactional(readOnly = false)
    public PilotDTO updatePilot(Pilot pilot) {
    	Pilot bdPilot = pilotDAO.findByLogin(pilot.getLogin());
    	bdPilot.setName(pilot.getName());
    	bdPilot.setSurname1(pilot.getSurname1());
    	bdPilot.setSurname2(pilot.getSurname2());
    	bdPilot.setEmail(pilot.getEmail());
    	bdPilot.setCountry(pilot.getCountry());
    	bdPilot.setCity(pilot.getCity());
    	bdPilot.setBirthDate(pilot.getBirthDate());
    	
    	pilotDAO.save(bdPilot);
    	return new PilotDTO(bdPilot);
    }
    
    public UserDTOPrivate getCurrentUserWithAuthority() {
        String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null) {
            return new UserDTOPrivate(userDAO.findByLogin(currentUserLogin));
        }
        return null;
    }
    
    public PilotDTO findByLogin(String login) {
    	return new PilotDTO(pilotDAO.findByLogin(login));
    }
    
    public UserDTOPublic findByEmail(String email) {
    	return new UserDTOPublic(userDAO.findByEmail(email));
    }
    
    @Transactional(readOnly = false)
    public UserDTOPrivate resetUserPassword(PasswordResetToken token, String password) {
    	User bduser = token.getUser();
    	String encryptedPassword = passwordEncoder.encode(password);
    	System.out.println("Password: " + password);
    	bduser.setPassword(encryptedPassword);
    	userDAO.save(bduser);
    	return new UserDTOPrivate (bduser);
    }
    
    @Transactional(readOnly = false)
    public void createPasswordResetTokenForUser(UserDTOPublic user, String token) {
    	User bduser = userDAO.findById(user.getId());
        PasswordResetToken myToken = new PasswordResetToken(token, bduser, LocalDateTime.now().plusMinutes(15));
        passwordTokenDAO.save(myToken);
        this.sendResetPasswordEmail(myToken);
    }
    
    public PasswordResetToken findByToken (String token) {
    	return passwordTokenDAO.findByToken(token);
    }
    
    public void sendResetPasswordEmail(PasswordResetToken token) {
    	Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("wikiflight.help", "wikiflight2019");
			}
		});
		
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("wikiflight.help@gmail.com"));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(token.getUser().getEmail()));
			message.setSubject("[Wikiflight] Reset your password");
			String contenido = "<div style = \"margin: 0% 1.5%\">\r\n" + 
					"	<div style=\"font-size:120%;text-align:center;\">\r\n" + 
					"    	<h1> Click this link to reset your password</h1>\r\n" + 
					"       <p>http://localhost:3000/resetPassword/"+token.getToken()+"</p>\r\n" + 
					"    </div>\r\n" +  
					"    \r\n" + 
					"</div>";
			message.setContent(contenido, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }
}
