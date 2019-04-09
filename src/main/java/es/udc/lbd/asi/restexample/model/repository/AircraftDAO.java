package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Aircraft;

public interface AircraftDAO {
	
	List<Aircraft> findAll();
	
	void save(Aircraft aircraft);
	
	Aircraft findById(Long id);
	
	void deleteById(Long id);

}
