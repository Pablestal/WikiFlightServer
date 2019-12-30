package es.udc.lbd.asi.restexample.model.domain;

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

import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import es.udc.lbd.asi.restexample.CustomGeometryDeserializer;
import es.udc.lbd.asi.restexample.CustomGeometrySerializer;

@Entity
@Table(name="Image")
public class Image {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty
	@Column
	private String name;
	
	@NotEmpty
	@Column
	private String description;
	
	@NotNull
	@Column
	@JsonSerialize(using = CustomGeometrySerializer.class, as= Point.class)
	@JsonDeserialize(using = CustomGeometryDeserializer.class, as= Point.class)
	private Point position;
	
	@Column
	private String path;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Route route;
	
	
	
	public Image() {
	}

	public Image(String name, String description, Point position, String path, Route route) {
		super();
		this.name = name;
		this.description = description;
		this.position = position;
		this.path = path;
		this.route = route;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", description=" + description + ", position=" + position
				+ ", path=" + path + "]";
	}
	
	
}
