package es.udc.lbd.asi.restexample.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.lbd.asi.restexample.model.domain.Route;
import es.udc.lbd.asi.restexample.model.service.RouteService;
import es.udc.lbd.asi.restexample.model.service.dto.RouteDTO;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/routes")
public class RouteResource {

	@Autowired
	private RouteService routeService;
	
	@GetMapping("/{login}")
	public List<RouteDTO> findByPilot(@PathVariable String login) {
		return routeService.findByPilot(login);
	}
	
	@GetMapping
	public RouteDTO findById(@RequestParam Long id) {
		return routeService.findById(id);
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
