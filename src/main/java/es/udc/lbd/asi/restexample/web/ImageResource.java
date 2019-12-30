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

import es.udc.lbd.asi.restexample.model.domain.Image;
import es.udc.lbd.asi.restexample.model.service.ImageService;
import es.udc.lbd.asi.restexample.model.service.dto.ImageDTO;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/images")
public class ImageResource {

	@Autowired
	private ImageService imageService;
	
	@GetMapping("/{id}")
	public List<ImageDTO> findByRoute(@PathVariable Long id) {
		return imageService.findByRoute(id);
	}
	
	@GetMapping
	public ImageDTO findById(@RequestParam Long id) {
		return imageService.findById(id);
	}
	
	@PostMapping("/{id}")
	public ImageDTO save(@PathVariable Long id, @RequestBody Image image, Errors errors) throws RequestBodyNotValidException {
		errorHandler(errors);
		return imageService.save(image, id);
	}
	
	@PutMapping("/{id}")
	public ImageDTO update(@PathVariable Long id, @RequestBody @Valid Image image, Errors errors)
			throws RequestBodyNotValidException {
		errorHandler(errors);
		if (id != image.getId()) {
			throw new RequestBodyNotValidException(null);
		}
				
		return imageService.update(image);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		imageService.deleteById(id);
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
