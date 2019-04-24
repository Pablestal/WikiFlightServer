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

import es.udc.lbd.asi.restexample.model.domain.Aircraft;
import es.udc.lbd.asi.restexample.model.service.AircraftService;
import es.udc.lbd.asi.restexample.model.service.dto.AircraftDTO;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/aircrafts")
public class AircraftResource {
	
	@Autowired
	private AircraftService aircraftService;
	
	@GetMapping
	public List<AircraftDTO> findAll() {
		return aircraftService.findAll();
	}
	
	@GetMapping("/{id}")
	public AircraftDTO findById(@PathVariable Long id) {
		return aircraftService.findById(id);
	}
	
	
	@PostMapping
	public AircraftDTO save(@RequestBody @Valid Aircraft aircraft, Errors errors) throws RequestBodyNotValidException {
		errorHandler(errors);
		return aircraftService.save(aircraft);
	}
	
	@PutMapping("/{id}")
	public AircraftDTO update(@PathVariable Long id, @RequestBody @Valid Aircraft aircraft, Errors errors)
			throws RequestBodyNotValidException {
		errorHandler(errors);
		if (id != aircraft.getId()) {
			throw new RequestBodyNotValidException(null);
		}
		return aircraftService.update(aircraft);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@RequestParam Long id) {
		aircraftService.deleteById(id);
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
