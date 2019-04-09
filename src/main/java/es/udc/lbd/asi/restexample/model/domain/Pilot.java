package es.udc.lbd.asi.restexample.model.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="pilotId")
@Table(name="Pilot")
public class Pilot extends User {
	
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String surname1;
    
    @Column
    private String surname2;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false) 
    private String country;
    
    @Column(nullable = false) 
    private String city;
    
    @Column(nullable = false)
    private LocalDate birthDate;
    
    @Column(nullable = false)
    private LocalDate regisDate;
 
    public Pilot () {
    	
    }

	public Pilot(String login, String password, UserAuthority authority, String name, String surname1, String surname2, 
			String email, String country, String city, LocalDate birthDate, LocalDate regisDate) {
		super(login, password, authority);
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.email = email;
		this.country = country;
		this.city = city;
		this.birthDate = birthDate;
		this.regisDate = regisDate;
	}
	
	public Pilot(String name, String surname1, String surname2, String email, String country, String city,
			LocalDate birthDate, LocalDate regisDate) {
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.email = email;
		this.country = country;
		this.city = city;
		this.birthDate = birthDate;
		this.regisDate = regisDate;
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
