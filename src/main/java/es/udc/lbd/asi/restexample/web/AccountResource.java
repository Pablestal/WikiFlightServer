package es.udc.lbd.asi.restexample.web;

import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.lbd.asi.restexample.PasswordResetToken;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.service.UserService;
import es.udc.lbd.asi.restexample.model.service.dto.LoginDTO;
import es.udc.lbd.asi.restexample.model.service.dto.PilotDTO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTOPrivate;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTOPublic;
import es.udc.lbd.asi.restexample.security.JWTConfigurer;
import es.udc.lbd.asi.restexample.security.JWTToken;
import es.udc.lbd.asi.restexample.security.TokenProvider;
import es.udc.lbd.asi.restexample.web.exception.CredentialsAreNotValidException;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;
import es.udc.lbd.asi.restexample.web.exception.UserNotFoundException;

/**
 * Este controlador va por separado que el UserResource porque se encarga de
 * tareas relacionadas con la autenticación, registro, etc.
 * 
 * También permite a cada usuario logueado en la aplicación obtener información
 * de su cuenta
 */
@RestController
@RequestMapping("/api")
public class AccountResource {
    private final Logger logger = LoggerFactory.getLogger(AccountResource.class);

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public JWTToken authenticate(@Valid @RequestBody LoginDTO loginDTO) throws CredentialsAreNotValidException {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getLogin(), loginDTO.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new JWTToken(jwt);
        } catch (AuthenticationException e) {
            logger.warn(e.getMessage(), e);
            throw new CredentialsAreNotValidException(e.getMessage());
        }
    }

    @GetMapping("/account")
    public UserDTOPrivate getAccount() {
        return userService.getCurrentUserWithAuthority();
    }

    @PostMapping("/register")
    public void registerAccount(@Valid @RequestBody PilotDTO account) throws UserLoginExistsException {
        userService.registerPilot(account.getLogin(), account.getPassword(), account.getName(), account.getSurname1(),
        		account.getSurname2(), account.getEmail(), account.getCountry(), account.getCity(), account.getBirthDate());
    }
    
    @PostMapping("/forgotpassword")
    public void forgotPassword(@Valid @RequestBody Map<String, Object> email ) throws UserNotFoundException {
    			String usermail = (String) email.get("email");

    		    UserDTOPublic user = userService.findByEmail(usermail);
    		    if (user == null) {
    		        throw new UserNotFoundException("User not found");
    		    }
    		    String token = UUID.randomUUID().toString();
    		    userService.createPasswordResetTokenForUser(user, token);
    		    
    		    
    		}
    
    @PutMapping("/resetpassword/{token}")
    public void resetPassword(@PathVariable String token, @Valid @RequestBody Map<String, Object> password) 
    		throws RequestBodyNotValidException {
    	if (userService.findByToken(token) == null) {
    		throw new RequestBodyNotValidException("Token no existe");
    	}
    	String pass = (String) password.get("password");
    	PasswordResetToken bdtoken = userService.findByToken(token);
    	userService.resetUserPassword(bdtoken, pass);
    }
    
}
