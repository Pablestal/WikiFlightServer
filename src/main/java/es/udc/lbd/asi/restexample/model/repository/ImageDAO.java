package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Image;
import es.udc.lbd.asi.restexample.model.domain.Route;

public interface ImageDAO {

	Image findById(Long id);
	
	List<Image> findByRoute(Route route);
	
	void save (Image image);
	
	void deleteById(Long id);

}
