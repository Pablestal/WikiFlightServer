package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.domain.Route;

public interface RouteDAO {

	List<Route> findAll();
	
	List<Route> findPublic();
	
	List<Route> findByPilot(Pilot pilot);
	
	void save(Route route);
	
	Route findById(Long id);
	
	void deleteById(Long id);
}
