package es.udc.lbd.asi.restexample.model.domain;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
    private String country;
    
    @Column(nullable = false) 
    private String city;
    
    @Column(nullable = false)
    private LocalDate birthDate;
    
    @Column(nullable = false)
    private LocalDate regisDate;
    
    @Column
    @Lob
    private byte[] image;
 
    public Pilot () {
    	
    }

	public Pilot(String login, String password, UserAuthority authority, String name, String surname1, String surname2, 
			 String email, String country, String city, LocalDate birthDate, LocalDate regisDate, byte[] image) {
		super(login, password, authority, email);
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.country = country;
		this.city = city;
		this.birthDate = birthDate;
		this.regisDate = regisDate;
		this.image = image;
	}
	
	public Pilot(String name, String surname1, String surname2, String country, String city,
			LocalDate birthDate, LocalDate regisDate, byte[] image) {
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.country = country;
		this.city = city;
		this.birthDate = birthDate;
		this.regisDate = regisDate;
		this.image = image;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Pilot [name=" + name + ", surname1=" + surname1 + ", surname2=" + surname2 + ", country=" + country
				+ ", city=" + city + ", birthDate=" + birthDate + ", regisDate=" + regisDate + ", image="
				+ Arrays.toString(image) + "]";
	}

}
