package es.udc.lbd.asi.restexample.model.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name="pilotId")
@Table(name="Pilot")
public class Pilot extends User {
	
    @NotEmpty
    @Column(nullable = false)
    private String name;
    
    @NotEmpty
    @Column(nullable = false)
    private String surname1;
    
    @Column
    private String surname2;
    
    @NotEmpty
    @Column(nullable = false) 
    private String country;
    
    @NotEmpty
    @Column(nullable = false) 
    private String city;
    
    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;
    
    @Column(nullable = false)
    private LocalDate regisDate;
    
    @OneToMany(mappedBy="picUser", fetch = FetchType.LAZY)
    private Set<Flight> flights = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
			name = "Followers",
			joinColumns = {@JoinColumn(name = "follower_id")},
			inverseJoinColumns = {@JoinColumn (name = "followed_id")}
			)
    private Set<Pilot> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    private Set<Pilot> following = new HashSet<>();
    
    @OneToMany(mappedBy="pilot", fetch = FetchType.LAZY)
    private Set<Route> createdRoutes = new HashSet<>();
    
    @OneToMany
    private Set<Route> favRoutes = new HashSet<>();
 
    public Pilot () {
    	
    }

	public Pilot(String login, String password, UserAuthority authority, String name, String surname1, String surname2, 
			 String email, String country, String city, LocalDate birthDate, LocalDate regisDate) {
		super(login, password, authority, email);
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.country = country;
		this.city = city;
		this.birthDate = birthDate;
		this.regisDate = regisDate;
		
	}
	
	public Pilot(String name, String surname1, String surname2, String country, String city,
			LocalDate birthDate, LocalDate regisDate) {
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.country = country;
		this.city = city;
		this.birthDate = birthDate;
		this.regisDate = regisDate;
		
	}

	public Pilot(String name, String surname1, String surname2, String country, String city, LocalDate birthDate,
			LocalDate regisDate, Set<Flight> flights, Set<Pilot> followers, Set<Pilot> following) {
		super();
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.country = country;
		this.city = city;
		this.birthDate = birthDate;
		this.regisDate = regisDate;
		this.flights = flights;
		this.followers = followers;
		this.following = following;
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

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public Set<Pilot> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<Pilot> followers) {
		this.followers = followers;
	}

	public Set<Pilot> getFollowing() {
		return following;
	}

	public void setFollowing(Set<Pilot> following) {
		this.following = following;
	}
	
	public Set<Route> getCreatedRoutes() {
		return createdRoutes;
	}

	public void setCreatedRoutes(Set<Route> createdRoutes) {
		this.createdRoutes = createdRoutes;
	}

	public Set<Route> getFavRoutes() {
		return favRoutes;
	}

	public void setFavRoutes(Set<Route> favRoutes) {
		this.favRoutes = favRoutes;
	}

	@Override
	public String toString() {
		return "Pilot [name=" + name + ", surname1=" + surname1 + ", surname2=" + surname2 + ", country=" + country
				+ ", city=" + city + ", birthDate=" + birthDate + ", regisDate=" + regisDate + ", flights=" + flights
				+ ", login=" + super.getLogin() + 
				", email=" + super.getEmail() + ", authority=" + super.getAuthority() + "]";
	}


}
