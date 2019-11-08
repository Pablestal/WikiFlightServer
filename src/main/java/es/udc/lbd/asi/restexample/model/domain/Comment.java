package es.udc.lbd.asi.restexample.model.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Comment")
public class Comment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty
	@Column
	private String description;
	
	@NotEmpty
	@Column
	private LocalDateTime date;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY) 
	private Route route;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Pilot pilot;
	
	public Comment() {
	}

	public Comment(String description, LocalDateTime date, Route route, Pilot pilot) {
		super();
		this.description = description;
		this.date = date;
		this.route = route;
		this.pilot = pilot;
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

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", description=" + description + ", date=" + date + ", route=" + route + ", pilot="
				+ pilot + "]";
	}
	
}
