package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Aircraft", uniqueConstraints=
@UniqueConstraint(columnNames={"manufacturer", "model"}))
public class Aircraft {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable=false)
	private String manufacturer;
	
	@Column(nullable=false)
	private String model;
	
	public Aircraft ( ) {
		
	}

	public Aircraft(String manufacturer, String model) {
		this.manufacturer = manufacturer;
		this.model = model;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Aircraft [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + "]";
	}
	
}
