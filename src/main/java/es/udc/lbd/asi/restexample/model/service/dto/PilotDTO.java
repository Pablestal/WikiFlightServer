package es.udc.lbd.asi.restexample.model.service.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.domain.Route;


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
    private Set<PilotFollDTO> followers = new HashSet<>();
    private Set<PilotFollDTO> following = new HashSet<>();
    private Set<RouteDTO> createdRoutes = new HashSet<>();
    private Set<RouteDTO> favRoutes = new HashSet<>();

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
		
		Set<Pilot> pil = pilot.getFollowers();
		for(Pilot p : pil) {
			this.followers.add(new PilotFollDTO(p));
		}
		
		Set<Pilot> pil1 = pilot.getFollowing();
		for(Pilot pt : pil1) {
			this.following.add(new PilotFollDTO(pt));
		}

		Set<Route> cre = pilot.getCreatedRoutes();
		for(Route rtc : cre) {
			this.createdRoutes.add(new RouteDTO(rtc));
		}
		
		Set<Route> fav = pilot.getCreatedRoutes();
		for(Route rtf : fav) {
			this.favRoutes.add(new RouteDTO(rtf));
		}
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

	public Set<PilotFollDTO> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<PilotFollDTO> followers) {
		this.followers = followers;
	}

	public Set<PilotFollDTO> getFollowing() {
		return following;
	}

	public void setFollowing(Set<PilotFollDTO> following) {
		this.following = following;
	}

	public Set<RouteDTO> getCreatedRoutes() {
		return createdRoutes;
	}

	public void setCreatedRoutes(Set<RouteDTO> createdRoutes) {
		this.createdRoutes = createdRoutes;
	}

	public Set<RouteDTO> getFavRoutes() {
		return favRoutes;
	}

	public void setFavRoutes(Set<RouteDTO> favRoutes) {
		this.favRoutes = favRoutes;
	}
  
}
