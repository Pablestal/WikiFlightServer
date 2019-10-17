package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Flight;
import es.udc.lbd.asi.restexample.model.domain.Pilot;

public interface FlightDAO {
	
	List<Flight> findByPilot(Pilot pilot);
	
	void save(Flight flight);
	
	Flight findById(Long id);
	
	void deleteById(Long id);
	
}
