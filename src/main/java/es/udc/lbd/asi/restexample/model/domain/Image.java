package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	@JsonSerialize(using = CustomGeometrySerializer.class, as= Point.class)
	@JsonDeserialize(using = CustomGeometryDeserializer.class, as= Point.class)
	private Point location;
	
	@Column
	private Integer imageOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Route route;
	
	public Image() {
	}

	public Image(String name, String description, Point location, Integer imageOrder, Route route) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.imageOrder = imageOrder;
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

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Integer getOrder() {
		return imageOrder;
	}

	public void setOrder(Integer imageOrder) {
		this.imageOrder = imageOrder;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
	
	
}
