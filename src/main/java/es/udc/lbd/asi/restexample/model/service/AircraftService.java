package es.udc.lbd.asi.restexample.model.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Aircraft;
import es.udc.lbd.asi.restexample.model.repository.AircraftDAO;
import es.udc.lbd.asi.restexample.model.service.dto.AircraftDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AircraftService {
	
	@Autowired
	private AircraftDAO aircraftDAO;
	
	public List<AircraftDTO> findAll() {
		return aircraftDAO.findAll().stream().map(aircraft -> new AircraftDTO(aircraft)).collect(Collectors.toList());
	}
	
	public AircraftDTO findById(Long id) {
		return new AircraftDTO(aircraftDAO.findById(id));
	}
	
	public AircraftDTO findByModel(String model) {
		return new AircraftDTO(aircraftDAO.findByModel(model));
	}
	
	@Transactional(readOnly = false)
	public AircraftDTO save(Aircraft aircraft) {
		Aircraft bdAircraft = new Aircraft(aircraft.getManufacturer(), aircraft.getModel());
		aircraftDAO.save(bdAircraft);
		return new AircraftDTO(bdAircraft);
	}
	
	@Transactional(readOnly = false)
	public AircraftDTO update (Aircraft aircraft) {
		Aircraft bdAircraft = aircraftDAO.findById(aircraft.getId());
		bdAircraft.setManufacturer(aircraft.getManufacturer());;
		bdAircraft.setModel(aircraft.getModel());
		
		aircraftDAO.save(bdAircraft);
		return new AircraftDTO(bdAircraft);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		aircraftDAO.deleteById(id);
	}

}
