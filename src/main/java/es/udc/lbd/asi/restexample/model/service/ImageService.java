package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Image;
import es.udc.lbd.asi.restexample.model.domain.Route;
import es.udc.lbd.asi.restexample.model.repository.ImageDAO;
import es.udc.lbd.asi.restexample.model.repository.RouteDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ImageDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ImageService {

	@Autowired
	private ImageDAO imageDAO;
	
	@Autowired
	private RouteDAO routeDAO;
	
	public List<ImageDTO> findByRoute(Long id) {
		Route route = routeDAO.findById(id);
		return imageDAO.findByRoute(route).stream().map(image -> new ImageDTO(image)).collect(Collectors.toList());
	}
	
	public ImageDTO findById(Long id) {
		return new ImageDTO(imageDAO.findById(id));
	}
	
	@Transactional(readOnly = false)
	public ImageDTO save(Image image, Long id) {
			Route route = routeDAO.findById(id);
			String path = "http://localhost:8080/api/users/image/route"+ id +"Image" + image.getPath() + ".jpg";
			
			Image bdImage = new Image(image.getName(), image.getDescription(), image.getPosition(), path, 
					route);
			imageDAO.save(bdImage);		
		return new ImageDTO(bdImage);
	}
	
	@Transactional(readOnly = false)
	public ImageDTO update (Image image) {
		Image bdImage= imageDAO.findById(image.getId());
		
		bdImage.setName(image.getName());
		bdImage.setDescription(image.getDescription());
		bdImage.setPosition(image.getPosition());
		bdImage.setPath(image.getPath());
		bdImage.setRoute(image.getRoute());
		
		imageDAO.save(bdImage);
		return new ImageDTO(bdImage);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		imageDAO.deleteById(id);
	}
}
	
