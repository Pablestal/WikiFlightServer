package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Aerodrome;

public interface AerodromeDAO {
	
	List<Aerodrome> findAll();
	
	void save(Aerodrome aerodrome);
	
	Aerodrome findById(Long id);
	
	Aerodrome findByName(String name);
	
	void deleteById(Long id);
	
}
