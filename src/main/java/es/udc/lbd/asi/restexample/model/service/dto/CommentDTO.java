package es.udc.lbd.asi.restexample.model.service.dto;

import java.time.LocalDateTime;

import es.udc.lbd.asi.restexample.model.domain.Comment;


public class CommentDTO {
	

    private Long id;
	private String description;
	private LocalDateTime date;
	private RouteDTO route;
	private PilotDTO pilot;
	
	public CommentDTO(Comment comment) {
		this.id = comment.getId();
		this.description = comment.getDescription();
		this.date = comment.getDate();
		this.pilot = new PilotDTO(comment.getPilot());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public RouteDTO getRoute() {
		return route;
	}

	public void setRoute(RouteDTO route) {
		this.route = route;
	}

	public PilotDTO getPilot() {
		return pilot;
	}

	public void setPilot(PilotDTO pilot) {
		this.pilot = pilot;
	}
	
	
}
