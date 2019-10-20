package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import es.udc.lbd.asi.restexample.CustomGeometryDeserializer;
import es.udc.lbd.asi.restexample.CustomGeometrySerializer;


@Entity
@Table(name="Aerodrome")
public class Aerodrome {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private String codIATA;
	
	@Column
	private String codOACI;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String country;
	
	@Column(nullable=false)
	private String city;
	
	@Column
	private Double elevation;
	
	@Column(nullable=false)
	@JsonSerialize(using = CustomGeometrySerializer.class, as= Point.class)
	@JsonDeserialize(using = CustomGeometryDeserializer.class, as= Point.class)
	private Point position;

	public Aerodrome() {
	}

	public Aerodrome(String codIATA, String codOACI, String name, String country, String city, Double elevation,
			Point position) {
		super();
		this.codIATA = codIATA;
		this.codOACI = codOACI;
		this.name = name;
		this.country = country;
		this.city = city;
		this.elevation = elevation;
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodIATA() {
		return codIATA;
	}

	public void setCodIATA(String codIATA) {
		this.codIATA = codIATA;
	}

	public String getCodOACI() {
		return codOACI;
	}

	public void setCodOACI(String codOACI) {
		this.codOACI = codOACI;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Aerodrome [id=" + id + ", codIATA=" + codIATA + ", codOACI=" + codOACI + ", name=" + name + ", country="
				+ country + ", city=" + city + ", elevation=" + elevation + ", position=" + position + "]";
	}
	
	
}
