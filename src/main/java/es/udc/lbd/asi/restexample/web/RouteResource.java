package es.udc.lbd.asi.restexample.web;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.udc.lbd.asi.restexample.model.domain.Route;
import es.udc.lbd.asi.restexample.model.service.RouteService;
import es.udc.lbd.asi.restexample.model.service.dto.RouteDTO;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/routes")
public class RouteResource {

	@Autowired
	private RouteService routeService;
	
	@GetMapping
	public List<RouteDTO> findPublic() {
		return routeService.findPublic();
	}
	
	@GetMapping("/{login}")
	public List<RouteDTO> findByPilot(@PathVariable String login) {
		return routeService.findByPilot(login);
	}
	
	@GetMapping("/detail/{id}")
	public RouteDTO findById(@PathVariable Long id) {
		return routeService.findById(id);
	}
	
	@RequestMapping(value = "/downloadGPX/{id}", method = RequestMethod.GET, produces = "application/gpx")
	public @ResponseBody Resource downloadC(@PathVariable Long id, HttpServletResponse response) throws FileNotFoundException {
	    File file = new File("./resources/images/route"+id+"pathFile.gpx");
	    response.setContentType("application/gpx");
	    response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
	    response.setHeader("Content-Length", String.valueOf(file.length()));
	    return new FileSystemResource(file);
	}

	@PostMapping
	public RouteDTO save(@RequestBody @Valid Route route, Errors errors) throws RequestBodyNotValidException {
		errorHandler(errors);		
		return routeService.save(route);
	}
	
	@PutMapping("/{id}")
	public RouteDTO update(@PathVariable Long id, @RequestBody @Valid Route route, Errors errors)
			throws RequestBodyNotValidException {
		errorHandler(errors);
		if (id != route.getId()) {
			throw new RequestBodyNotValidException(null);
		}
				
		return routeService.update(route);
	}
	
	@PutMapping("/uploadfiles/{id}")
    public void uploadRouteFiles(@PathVariable Long id, @ModelAttribute MultipartFile file, ModelMap modelMap) 
    		throws Exception{
		
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		String fileFormat = filename.substring(filename.length() - 4);
		
        if (fileFormat.equals(".gpx")) {
        	routeService.store(file);
        	routeService.parsePathFile(file, id);
        } else {
        	modelMap.addAttribute("file", file);
			routeService.store(file);
		}
        			
    }
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		routeService.deleteById(id);
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
