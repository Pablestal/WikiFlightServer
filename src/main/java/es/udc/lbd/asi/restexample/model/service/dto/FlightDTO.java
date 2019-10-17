package es.udc.lbd.asi.restexample.model.service.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import es.udc.lbd.asi.restexample.model.domain.Aerodrome;
import es.udc.lbd.asi.restexample.model.domain.Aircraft;
import es.udc.lbd.asi.restexample.model.domain.Flight;
import es.udc.lbd.asi.restexample.model.domain.Pilot;

public class FlightDTO {

	private Long id;
	
	private LocalDateTime departureTime;
	
	private LocalDateTime arrivalTime;
	
	private LocalTime totalTime;
	
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
	
	/// Associated aerodromes, pilot and aircraft ///
	
	private Aerodrome takeoffAerodrome;
	
	private Aerodrome landingAerodrome;
	
	private Pilot picUser;
	
	private Aircraft aircraft;
	
	public FlightDTO() {
		
	}

	public FlightDTO(Flight flight) {
		
		this.id = flight.getId();
		this.departureTime = flight.getDepartureTime();
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
		this.takeoffAerodrome = flight.getTakeoffAerodrome();
		this.landingAerodrome = flight.getLandingAerodrome();
		this.picUser = flight.getPicUser();
		this.aircraft = flight.getAircraft();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(LocalTime totalTime) {
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

	public Aerodrome getTakeoffAerodrome() {
		return takeoffAerodrome;
	}

	public void setTakeoffAerodrome(Aerodrome takeoffAerodrome) {
		this.takeoffAerodrome = takeoffAerodrome;
	}

	public Aerodrome getLandingAerodrome() {
		return landingAerodrome;
	}

	public void setLandingAerodrome(Aerodrome landingAerodrome) {
		this.landingAerodrome = landingAerodrome;
	}

	public Pilot getPicUser() {
		return picUser;
	}

	public void setPicUser(Pilot picUser) {
		this.picUser = picUser;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	@Override
	public String toString() {
		return "FlightDTO [id=" + id + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ ", totalTime=" + totalTime + ", seTime=" + seTime + ", meTime=" + meTime + ", mpTime=" + mpTime
				+ ", takeoffsDay=" + takeoffsDay + ", takeoffsNight=" + takeoffsNight + ", landingsDay=" + landingsDay
				+ ", landingsNight=" + landingsNight + ", nightTime=" + nightTime + ", ifrTime=" + ifrTime
				+ ", picTime=" + picTime + ", coopilotTime=" + coopilotTime + ", dualTime=" + dualTime
				+ ", instructorTime=" + instructorTime + ", observations=" + observations + ", aircraftReg="
				+ aircraftReg + ", takeoffAerodrome=" + takeoffAerodrome + ", landingAerodrome=" + landingAerodrome
				+ ", picUser=" + picUser + ", aircraft=" + aircraft + "]";
	}
	
	
}
