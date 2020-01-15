package es.udc.lbd.asi.restexample.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    
    public Path load(String path) {
        return location.resolve(path);
    }
    
    public Resource getImageAsResource(String fileName) throws Exception {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new Exception(
                        "Could not read file: " + fileName);
            }
        }
        catch (MalformedURLException e) {
            throw new Exception("Could not read file: " + fileName, e);
        }
    }    
    
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
    	bdPilot.setFollowers(pilot.getFollowers());
    	bdPilot.setFavRoutes(pilot.getFavRoutes());
    	
    	if (pilot.getPassword() != null) {
    	String encryptedPassword = passwordEncoder.encode(pilot.getPassword());
    	bdPilot.setPassword(encryptedPassword);
    	}
    	
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
