package es.udc.lbd.asi.restexample.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.service.UserService;
import es.udc.lbd.asi.restexample.model.service.dto.PilotDTO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTOPublic;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTOPublic> findAll() {
        return userService.findAll();
    }
    
    @GetMapping("/{login}")
    public PilotDTO findByLogin(@PathVariable String login) {
    	return userService.findByLogin(login);
    }
    
    @PutMapping("/{login}")
    public PilotDTO updatePilot(@PathVariable String login, @ModelAttribute @Valid Pilot pilot, @ModelAttribute MultipartFile image, Errors errors) 
    		throws RequestBodyNotValidException, IOException {
    	errorHandler(errors);
  
    	if (!login.equals(pilot.getLogin())) {
			throw new RequestBodyNotValidException("Body not valid");
		}
    	if (image == null) 
    		return userService.updatePilot(pilot);
    	else {
    		 String destination = "C:\\Users\\pable\\Documents\\FIC\\2º Cuatrimestre\\tfgclient\\public\\images\\avatars\\"+ pilot.getLogin() + "avatar.jpg";
    		 File file = new File(destination);
    		 image.transferTo(file);
    		return null;
    		}
    }
    
    private void errorHandler(Errors errors) throws RequestBodyNotValidException {
		if (errors.hasErrors()) {
			String errorMsg = errors.getFieldErrors().stream()
					.map(fe -> String.format("%s.%s %s", fe.getObjectName(), fe.getField(), fe.getDefaultMessage()))
					.collect(Collectors.joining("; "));
			throw new RequestBodyNotValidException(errorMsg);
		}
	}
}
