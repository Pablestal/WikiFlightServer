package es.udc.lbd.asi.restexample.model.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.locationtech.jts.geom.LineString;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import es.udc.lbd.asi.restexample.CustomGeometryDeserializer;
import es.udc.lbd.asi.restexample.CustomGeometrySerializer;

@Entity
@Table(name="Route")
public class Route {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty
	@Column
	private String name;
	
	@Column
	private Boolean isPublic;
	
	@Column
	private LocalDate publicationDay;
	
	@NotEmpty
	@Column
	private String description;
	
	@Column
	@JsonSerialize(using = CustomGeometrySerializer.class, as= LineString.class)
	@JsonDeserialize(using = CustomGeometryDeserializer.class, as= LineString.class)
	private LineString path;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Aerodrome takeoffAerodrome;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Aerodrome landingAerodrome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pilot pilot;
	
	@OneToMany(mappedBy="route", fetch = FetchType.LAZY)
	private Set<Flight> flights = new HashSet<>();
	
	@OneToMany(mappedBy="route", fetch = FetchType.LAZY)
	private Set<Comment> comments = new HashSet<>();
	
	@OneToMany(mappedBy="route", fetch = FetchType.LAZY)
	private Set<Image> images = new HashSet<>();
	
	public Route() {
	}
	
	public Route(String name, Boolean isPublic, LocalDate publicationDay, String description, 
			Aerodrome takeoffAerodrome, Aerodrome landingAerodrome, Pilot pilot, Set<Image> images) {
		super();
		this.name = name;
		this.isPublic = isPublic;
		this.publicationDay = publicationDay;
		this.description = description;
		this.takeoffAerodrome = takeoffAerodrome;
		this.landingAerodrome = landingAerodrome;
		this.pilot = pilot;
		this.images = images;
	}

	public Route(String name, Boolean isPublic, LocalDate publicationDay, String description, 
			Aerodrome takeoffAerodrome, Aerodrome landingAerodrome, Pilot pilot, Set<Flight> flights,
			Set<Comment> comments, Set<Image> images) {
		super();
		this.name = name;
		this.isPublic = isPublic;
		this.publicationDay = publicationDay;
		this.description = description;
//		this.path = path;
		this.takeoffAerodrome = takeoffAerodrome;
		this.landingAerodrome = landingAerodrome;
		this.pilot = pilot;
		this.flights = flights;
		this.comments = comments;
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public LocalDate getPublicationDay() {
		return publicationDay;
	}

	public void setPublicationDay(LocalDate publicationDay) {
		this.publicationDay = publicationDay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LineString getPath() {
		return path;
	}

	public void setPath(LineString path) {
		this.path = path;
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

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", name=" + name + ", isPublic=" + isPublic + ", publicationDay=" + publicationDay
				+ ", description=" + description + ", path=" + path + ", takeoffAerodrome=" + takeoffAerodrome
				+ ", landingAerodrome=" + landingAerodrome + ", pilot=" + pilot.getName() + "]";
	}
	
	
}
