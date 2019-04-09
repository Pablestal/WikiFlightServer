package es.udc.lbd.asi.restexample.model.service.dto;

import javax.validation.constraints.NotEmpty;

import es.udc.lbd.asi.restexample.model.domain.Aircraft;

public class AircraftDTO {

	private Long id;
	
	@NotEmpty
	private String manufacturer;
	
	@NotEmpty
	private String model;
	
	

	public AircraftDTO() {

	}

	public AircraftDTO(Aircraft aircraft) {
		this.id = aircraft.getId();
		this.manufacturer = aircraft.getManufacturer();
		this.model = aircraft.getModel();
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
	
	
}
