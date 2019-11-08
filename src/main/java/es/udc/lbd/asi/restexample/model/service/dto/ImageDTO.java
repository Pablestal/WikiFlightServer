package es.udc.lbd.asi.restexample.model.service.dto;

import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import es.udc.lbd.asi.restexample.CustomGeometryDeserializer;
import es.udc.lbd.asi.restexample.CustomGeometrySerializer;
import es.udc.lbd.asi.restexample.model.domain.Image;

public class ImageDTO {

    private Long id;
	private String name;
	private String description;
	@JsonSerialize(using = CustomGeometrySerializer.class, as= Point.class)
	@JsonDeserialize(using = CustomGeometryDeserializer.class, as= Point.class)
	private Point location;
	private Integer order;
	
	public ImageDTO(Image image) {
		this.id = image.getId();
		this.name = image.getName();
		this.description = image.getDescription();
		this.location = image.getLocation();
		this.order = image.getOrder();
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
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	
	
}
