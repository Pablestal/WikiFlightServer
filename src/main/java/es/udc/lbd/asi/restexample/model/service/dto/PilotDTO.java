package es.udc.lbd.asi.restexample.model.service.dto;

import java.time.LocalDate;

import es.udc.lbd.asi.restexample.model.domain.Pilot;


public class PilotDTO {
	private Long id;
    private String login;
    private String password;
    private String authority;
    private String name;  
    private String surname1;  
    private String surname2;
    private String email;  
    private String country;  
    private String city;
    private LocalDate birthDate;
    private LocalDate regisDate;
    
    public PilotDTO() {
    }

	public PilotDTO(Pilot pilot) {
		this.id = pilot.getId();
		this.login = pilot.getLogin();
		this.authority = pilot.getAuthority().name();
		this.name = pilot.getName();
		this.surname1 = pilot.getSurname1();
		this.surname2 = pilot.getSurname2();
		this.email = pilot.getEmail();
		this.country = pilot.getCountry();
		this.city = pilot.getCity();
		this.birthDate = pilot.getBirthDate();
		this.regisDate = pilot.getRegisDate();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getRegisDate() {
		return regisDate;
	}

	public void setRegisDate(LocalDate regisDate) {
		this.regisDate = regisDate;
	}
  
}
