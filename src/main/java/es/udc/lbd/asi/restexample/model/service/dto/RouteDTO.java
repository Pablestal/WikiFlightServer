package es.udc.lbd.asi.restexample.model.service.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.locationtech.jts.geom.LineString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import es.udc.lbd.asi.restexample.CustomGeometryDeserializer;
import es.udc.lbd.asi.restexample.CustomGeometrySerializer;
import es.udc.lbd.asi.restexample.model.domain.Comment;
import es.udc.lbd.asi.restexample.model.domain.Flight;
import es.udc.lbd.asi.restexample.model.domain.Image;
import es.udc.lbd.asi.restexample.model.domain.Route;

public class RouteDTO {
	
	private Long id;
	private String name;
	private Boolean isPublic;
	private LocalDate publicationDay;
	private String description;
	@JsonSerialize(using = CustomGeometrySerializer.class, as= LineString.class)
	@JsonDeserialize(using = CustomGeometryDeserializer.class, as= LineString.class)
	private LineString path;
	private AerodromeDTO takeoffAerodrome;
	private AerodromeDTO landingAerodrome;
	private PilotRouteDTO pilot;
	@JsonIgnore
	private Set<FlightDTO> flights = new HashSet<>();
	private Set<CommentDTO> comments = new HashSet<>();
	private Set<ImageDTO> images = new HashSet<>();
	
	public RouteDTO(Route route) {
		this.id = route.getId();
		this.name = route.getName();
		this.isPublic = route.getIsPublic();
		this.publicationDay = route.getPublicationDay();
		this.description = route.getDescription();
		this.path = route.getPath();
		this.takeoffAerodrome = new AerodromeDTO(route.getTakeoffAerodrome());
		this.landingAerodrome = new AerodromeDTO(route.getLandingAerodrome());
		this.pilot = new PilotRouteDTO(route.getPilot());
		
		Set<Flight> fl = route.getFlights();
		for(Flight f : fl) {
			this.flights.add(new FlightDTO(f));
		}
		
		Set<Comment> cms = route.getComments();
		for(Comment c : cms) {
			this.comments.add(new CommentDTO(c));
		}
		
		Set<Image> imgs = route.getImages();
		for(Image i : imgs) {
			this.images.add(new ImageDTO(i));
		}
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

	public PilotRouteDTO getPilot() {
		return pilot;
	}

	public void setPilot(PilotRouteDTO pilot) {
		this.pilot = pilot;
	}

	public Set<FlightDTO> getFlights() {
		return flights;
	}

	public void setFlights(Set<FlightDTO> flights) {
		this.flights = flights;
	}

	public Set<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDTO> comments) {
		this.comments = comments;
	}

	public Set<ImageDTO> getImages() {
		return images;
	}

	public void setImages(Set<ImageDTO> images) {
		this.images = images;
	}
	
	
}
