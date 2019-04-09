package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Pilot;

public interface PilotDAO {
	
	List<Pilot> findAll();

    void save(Pilot pilot);

    Pilot findById(Long id);

}
