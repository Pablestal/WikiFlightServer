package es.udc.lbd.asi.restexample.model.service.dto;

import java.time.LocalDate;

import es.udc.lbd.asi.restexample.model.domain.Comment;


public class CommentDTO {
	

    private Long id;
	private String description;
	private LocalDate date;
	private PilotRouteDTO pilot;
	
	public CommentDTO(Comment comment) {
		this.id = comment.getId();
		this.description = comment.getDescription();
		this.date = comment.getDate();
		this.pilot = new PilotRouteDTO(comment.getPilot());
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public PilotRouteDTO getPilot() {
		return pilot;
	}

	public void setPilot(PilotRouteDTO pilot) {
		this.pilot = pilot;
	}
	
	
}
