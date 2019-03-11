package es.udc.lbd.asi.restexample.model.service.dto;

import es.udc.lbd.asi.restexample.model.domain.User;

public class UserDTOPrivate {
    private Long id;
    private String login;
    private String password;
    private String authority;

    public UserDTOPrivate() {
    }

    public UserDTOPrivate(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        // la contraseña no se rellena, nunca se envía al cliente
        this.authority = user.getAuthority().name();
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
}
