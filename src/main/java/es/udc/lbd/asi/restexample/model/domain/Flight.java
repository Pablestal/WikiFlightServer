package es.udc.lbd.asi.restexample.model.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Flight")
public class Flight {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@Column
	private LocalDate departureDate;
	
	@NotNull
	@Column
	private LocalTime departureTime;
	
	@NotNull
	@Column
	private LocalDate arrivalDate;
	
	@NotNull
	@Column
	private LocalTime arrivalTime;
	
	@Column
	private Long totalTime;
	
	// Single pilot and multipilot times //
	
	@Column
	private LocalTime seTime; // Single engine time
	
	@Column
	private LocalTime meTime; // Multi engine time
	
	@Column
	private LocalTime mpTime; // Multi pilot time
	
	// Takeoffs and landings //
	
	@Column
	private int takeoffsDay;
	
	@Column
	private int takeoffsNight;
	
	@Column
	private int landingsDay;
	
	@Column
	private int landingsNight;
	
	// Operational conditions time //
	
	@Column
	private LocalTime nightTime;
	
	@Column
	private LocalTime ifrTime; //Instrumental Flight Rules Time
	
	// Pilot function time //
	
	@Column
	private LocalTime picTime;
	
	@Column
	private LocalTime coopilotTime;
	
	@Column
	private LocalTime dualTime;
	
	@Column
	private LocalTime instructorTime;
	
	/////////////////////////////////////////
	
	@Column
	private String observations;
	
	@Column
	private String aircraftReg;
	
	/// Associated aerodromes, pilot and aircraft ///
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Aerodrome takeoffAerodrome;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Aerodrome landingAerodrome;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Pilot picUser;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Aircraft aircraft;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Route route;
		

	public Flight() {
	}

	public Flight(LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime, 
			Long totalTime, LocalTime seTime, LocalTime meTime, LocalTime mpTime, int takeoffsDay, int takeoffsNight,
			int landingsDay, int landingsNight, LocalTime nightTime, LocalTime ifrTime, LocalTime picTime,
			LocalTime coopilotTime, LocalTime dualTime, LocalTime instructorTime, String observations,
			String aircraftReg, Aerodrome takeoffAerodrome, Aerodrome landingAerodrome, Pilot picUser,
			Aircraft aircraft, Route route) {
		super();
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.totalTime = totalTime;
		this.seTime = seTime;
		this.meTime = meTime;
		this.mpTime = mpTime;
		this.takeoffsDay = takeoffsDay;
		this.takeoffsNight = takeoffsNight;
		this.landingsDay = landingsDay;
		this.landingsNight = landingsNight;
		this.nightTime = nightTime;
		this.ifrTime = ifrTime;
		this.picTime = picTime;
		this.coopilotTime = coopilotTime;
		this.dualTime = dualTime;
		this.instructorTime = instructorTime;
		this.observations = observations;
		this.aircraftReg = aircraftReg;
		this.takeoffAerodrome = takeoffAerodrome;
		this.landingAerodrome = landingAerodrome;
		this.picUser = picUser;
		this.aircraft = aircraft;
		this.route = route;
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

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
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

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", totalTime="
				+ totalTime + ", seTime=" + seTime + ", meTime=" + meTime + ", mpTime=" + mpTime + ", takeoffsDay="
				+ takeoffsDay + ", takeoffsNight=" + takeoffsNight + ", landingsDay=" + landingsDay + ", landingsNight="
				+ landingsNight + ", nightTime=" + nightTime + ", ifrTime=" + ifrTime + ", picTime=" + picTime
				+ ", coopilotTime=" + coopilotTime + ", dualTime=" + dualTime + ", instructorTime=" + instructorTime
				+ ", observations=" + observations + ", aircraftReg=" + aircraftReg + ", takeoffAerodrome="
				+ takeoffAerodrome + ", landingAerodrome=" + landingAerodrome + ", picUser=" + picUser + ", aircraft="
				+ aircraft + "]";
	}
	
	
	
}
