package es.udc.lbd.asi.restexample.model.service.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import es.udc.lbd.asi.restexample.model.domain.Flight;


public class FlightDTO {

	private Long id;
	
	// Departure and Arrival times //
	private LocalDate departureDate;
	private LocalTime departureTime;
	private LocalDate arrivalDate;
	private LocalTime arrivalTime;
	private Long totalTime;
	
	// Single pilot and multipilot times //
	private LocalTime seTime;	
	private LocalTime meTime;
	private LocalTime mpTime;
	
	// Takeoffs and landings //
	private int takeoffsDay;
	private int takeoffsNight;
	private int landingsDay;
	private int landingsNight;
	
	// Operational conditions time //
	private LocalTime nightTime;
	private LocalTime ifrTime;
	
	// Pilot function time //
	private LocalTime picTime;
	private LocalTime coopilotTime;
	private LocalTime dualTime;
	private LocalTime instructorTime;
	
	/////////////////////////////////////////
	
	private String observations;
	private String aircraftReg;
	
	/// Associated aerodromes, pilot, aircraft and route ///
	private AerodromeDTO takeoffAerodrome;
	private AerodromeDTO landingAerodrome;
	private PilotRouteDTO picUser;
	private AircraftDTO aircraft;
	private RouteFlightDTO route;
	
	public FlightDTO() {
		
	}

	public FlightDTO(Flight flight) {
		
		this.id = flight.getId();
		this.departureDate = flight.getDepartureDate();
		this.departureTime = flight.getDepartureTime();
		this.arrivalDate = flight.getArrivalDate();
		this.arrivalTime = flight.getArrivalTime();
		this.totalTime = flight.getTotalTime();
		this.seTime = flight.getSeTime();
		this.meTime = flight.getMeTime();
		this.mpTime = flight.getMpTime();
		this.takeoffsDay = flight.getTakeoffsDay();
		this.takeoffsNight = flight.getTakeoffsNight();
		this.landingsDay = flight.getLandingsDay();
		this.landingsNight = flight.getLandingsNight();
		this.nightTime = flight.getNightTime();
		this.ifrTime = flight.getIfrTime();
		this.picTime = flight.getPicTime();
		this.coopilotTime = flight.getCoopilotTime();
		this.dualTime = flight.getDualTime();
		this.instructorTime = flight.getInstructorTime();
		this.observations = flight.getObservations();
		this.aircraftReg = flight.getAircraftReg();
		this.takeoffAerodrome = new AerodromeDTO(flight.getTakeoffAerodrome());
		this.landingAerodrome = new AerodromeDTO(flight.getLandingAerodrome());
		this.aircraft = new AircraftDTO(flight.getAircraft());
		this.picUser = new PilotRouteDTO(flight.getPicUser());
		if (flight.getRoute() != null) this.route = new RouteFlightDTO(flight.getRoute());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	public LocalTime getSeTime() {
		return seTime;
	}

	public void setSeTime(LocalTime seTime) {
		this.seTime = seTime;
	}

	public LocalTime getMeTime() {
		return meTime;
	}

	public void setMeTime(LocalTime meTime) {
		this.meTime = meTime;
	}

	public LocalTime getMpTime() {
		return mpTime;
	}

	public void setMpTime(LocalTime mpTime) {
		this.mpTime = mpTime;
	}

	public int getTakeoffsDay() {
		return takeoffsDay;
	}

	public void setTakeoffsDay(int takeoffsDay) {
		this.takeoffsDay = takeoffsDay;
	}

	public int getTakeoffsNight() {
		return takeoffsNight;
	}

	public void setTakeoffsNight(int takeoffsNight) {
		this.takeoffsNight = takeoffsNight;
	}

	public int getLandingsDay() {
		return landingsDay;
	}

	public void setLandingsDay(int landingsDay) {
		this.landingsDay = landingsDay;
	}

	public int getLandingsNight() {
		return landingsNight;
	}

	public void setLandingsNight(int landingsNight) {
		this.landingsNight = landingsNight;
	}

	public LocalTime getNightTime() {
		return nightTime;
	}

	public void setNightTime(LocalTime nightTime) {
		this.nightTime = nightTime;
	}

	public LocalTime getIfrTime() {
		return ifrTime;
	}

	public void setIfrTime(LocalTime ifrTime) {
		this.ifrTime = ifrTime;
	}

	public LocalTime getPicTime() {
		return picTime;
	}

	public void setPicTime(LocalTime picTime) {
		this.picTime = picTime;
	}

	public LocalTime getCoopilotTime() {
		return coopilotTime;
	}

	public void setCoopilotTime(LocalTime coopilotTime) {
		this.coopilotTime = coopilotTime;
	}

	public LocalTime getDualTime() {
		return dualTime;
	}

	public void setDualTime(LocalTime dualTime) {
		this.dualTime = dualTime;
	}

	public LocalTime getInstructorTime() {
		return instructorTime;
	}

	public void setInstructorTime(LocalTime instructorTime) {
		this.instructorTime = instructorTime;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getAircraftReg() {
		return aircraftReg;
	}

	public void setAircraftReg(String aircraftReg) {
		this.aircraftReg = aircraftReg;
	}

	public AerodromeDTO getTakeoffAerodrome() {
		return takeoffAerodrome;
	}

	public void setTakeoffAerodrome(AerodromeDTO takeoffAerodrome) {
		this.takeoffAerodrome = takeoffAerodrome;
	}

	public AerodromeDTO getLandingAerodrome() {
		return landingAerodrome;
	}

	public void setLandingAerodrome(AerodromeDTO landingAerodrome) {
		this.landingAerodrome = landingAerodrome;
	}

	public PilotRouteDTO getPicUser() {
		return picUser;
	}

	public void setPicUser(PilotRouteDTO picUser) {
		this.picUser = picUser;
	}

	public AircraftDTO getAircraft() {
		return aircraft;
	}

	public void setAircraft(AircraftDTO aircraft) {
		this.aircraft = aircraft;
	}
	
	public RouteFlightDTO getRoute() {
		return route;
	}

	public void setRoute(RouteFlightDTO route) {
		this.route = route;
	}
	
}
