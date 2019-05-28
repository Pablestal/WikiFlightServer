package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "itsUser")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserAuthority authority;
    
    @Column(nullable = false)
    private String email;

	public User() {
    }
    
    public User(String login, String password, UserAuthority authority, String email) {
		super();
		this.login = login;
		this.password = password;
		this.authority = authority;
		this.email = email;
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
     
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(UserAuthority authority) {
        this.authority = authority;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", authority=" + authority
				+ ", email=" + email + "]";
	}
    
    
}
