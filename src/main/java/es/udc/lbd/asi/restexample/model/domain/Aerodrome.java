package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Point;


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
	private Float elevation;
	
	@Column(nullable=false)
	private Point position;

	public Aerodrome() {
	}

	public Aerodrome(Long id, String codIATA, String codOACI, String name, String country, String city, Float elevation,
			Point position) {
		super();
		this.id = id;
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

	public Float getElevation() {
		return elevation;
	}

	public void setElevation(Float elevation) {
		this.elevation = elevation;
	}
	
	
}
