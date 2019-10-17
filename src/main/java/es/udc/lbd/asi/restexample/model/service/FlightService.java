package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Flight;
import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.repository.FlightDAO;
import es.udc.lbd.asi.restexample.model.repository.PilotDAO;
import es.udc.lbd.asi.restexample.model.service.dto.FlightDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class FlightService {
	
	@Autowired
	private FlightDAO flightDAO;
	
	@Autowired
	private PilotDAO pilotDAO;
	
	public List<FlightDTO> findByPilot(String login) {
		Pilot pilot = pilotDAO.findByLogin(login);
		return flightDAO.findByPilot(pilot).stream().map(flight -> new FlightDTO(flight)).collect(Collectors.toList());
	}
	
	public FlightDTO findById(Long id) {
		return new FlightDTO(flightDAO.findById(id));
	}
	
	@Transactional(readOnly = false)
	public FlightDTO save(Flight flight) {
		Flight bdFlight = new Flight(flight.getDepartureTime(), flight.getArrivalTime(), flight.getTotalTime(), flight.getSeTime(),
				flight.getMeTime(), flight.getMpTime(), flight.getTakeoffsDay(), flight.getTakeoffsNight(), flight.getLandingsDay(),
				flight.getLandingsNight(), flight.getNightTime(), flight.getIfrTime(), flight.getPicTime(), flight.getCoopilotTime(),
				flight.getDualTime(), flight.getInstructorTime(), flight.getObservations(), flight.getAircraftReg(), flight.getTakeoffAerodrome(),
				flight.getLandingAerodrome(), flight.getPicUser(), flight.getAircraft());
				
		flightDAO.save(bdFlight);
		return new FlightDTO(bdFlight);
	}
	
	@Transactional(readOnly = false)
	public FlightDTO update (Flight flight) {
		Flight bdFlight = flightDAO.findById(flight.getId());
		bdFlight.setDepartureTime(flight.getDepartureTime());
		bdFlight.setArrivalTime(flight.getArrivalTime());
		bdFlight.setTotalTime(flight.getTotalTime());
		bdFlight.setSeTime(flight.getSeTime());
		bdFlight.setMeTime(flight.getMeTime());
		bdFlight.setMpTime(flight.getMpTime());
		bdFlight.setTakeoffsDay(flight.getTakeoffsDay());
		bdFlight.setTakeoffsNight(flight.getTakeoffsNight());
		bdFlight.setLandingsDay(flight.getLandingsDay());
		bdFlight.setLandingsNight(flight.getLandingsNight());
		bdFlight.setNightTime(flight.getNightTime());
		bdFlight.setIfrTime(flight.getIfrTime());
		bdFlight.setPicTime(flight.getPicTime());
		bdFlight.setCoopilotTime(flight.getCoopilotTime());
		bdFlight.setDualTime(flight.getDualTime());
		bdFlight.setInstructorTime(flight.getInstructorTime());
		bdFlight.setObservations(flight.getObservations());
		bdFlight.setAircraftReg(flight.getAircraftReg());
		bdFlight.setTakeoffAerodrome(flight.getTakeoffAerodrome());
		bdFlight.setLandingAerodrome(flight.getLandingAerodrome());
		bdFlight.setPicUser(flight.getPicUser());
		bdFlight.setAircraft(flight.getAircraft());
		
		flightDAO.save(bdFlight);
		return new FlightDTO(bdFlight);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		flightDAO.deleteById(id);
	}
}
