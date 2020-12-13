package fr.esgi.ticketapi.dataprovider.model;

import javax.persistence.*;

@Entity
public class TUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String latName;

    private String email;

    @ManyToOne
    private TRole role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLatName() {
        return latName;
    }

    public void setLatName(String latName) {
        this.latName = latName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TRole getRole() {
        return role;
    }

    public void setRole(TRole role) {
        this.role = role;
    }
}
