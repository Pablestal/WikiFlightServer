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

import es.udc.lbd.asi.restexample.model.domain.Aerodrome;
import es.udc.lbd.asi.restexample.model.service.AerodromeService;
import es.udc.lbd.asi.restexample.model.service.dto.AerodromeDTO;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/aerodromes")
public class AerodromeResource {
	
	@Autowired
	private AerodromeService aerodromeService;
	
	@GetMapping
	public List<AerodromeDTO> findAll() {
		return aerodromeService.findAll();
	}
	
	@GetMapping("/{id}")
	public AerodromeDTO findById(@PathVariable Long id) {
		return aerodromeService.findById(id);
	}
	
	
	@PostMapping
	public AerodromeDTO save(@RequestBody @Valid Aerodrome aerodrome, Errors errors) throws RequestBodyNotValidException {
		errorHandler(errors);
		return aerodromeService.save(aerodrome);
	}
	
	@PutMapping("/{id}")
	public AerodromeDTO update(@PathVariable Long id, @RequestBody @Valid Aerodrome aerodrome, Errors errors)
			throws RequestBodyNotValidException {
		errorHandler(errors);
		if (id != aerodrome.getId()) {
			throw new RequestBodyNotValidException(null);
		}
		
		return aerodromeService.update(aerodrome);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@RequestParam Long id) {
		aerodromeService.deleteById(id);
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
