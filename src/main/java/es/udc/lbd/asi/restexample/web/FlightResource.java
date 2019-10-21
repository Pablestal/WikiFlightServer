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

import es.udc.lbd.asi.restexample.model.domain.Flight;
import es.udc.lbd.asi.restexample.model.service.FlightService;
import es.udc.lbd.asi.restexample.model.service.dto.FlightDTO;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/flights")
public class FlightResource {

	@Autowired
	private FlightService flightService;
	
	@GetMapping("/{login}")
	public List<FlightDTO> findByPilot(@PathVariable String login) {
		return flightService.findByPilot(login);
	}
	
	@GetMapping
	public FlightDTO findById(@RequestParam Long id) {
		return flightService.findById(id);
	}
	
	@PostMapping
	public FlightDTO save(@RequestBody @Valid Flight flight, Errors errors) throws RequestBodyNotValidException {
		errorHandler(errors);
		return flightService.save(flight);
	}
	
	@PutMapping("/{id}")
	public FlightDTO update(@PathVariable Long id, @RequestBody @Valid Flight flight, Errors errors)
			throws RequestBodyNotValidException {
		errorHandler(errors);
		if (id != flight.getId()) {
			throw new RequestBodyNotValidException(null);
		}
		
		return flightService.update(flight);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		flightService.deleteById(id);
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
