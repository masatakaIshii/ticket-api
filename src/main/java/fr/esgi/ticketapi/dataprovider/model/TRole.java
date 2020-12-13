package fr.esgi.ticketapi.dataprovider.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class TRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany()
    private Collection<TUser> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<TUser> gettUser() {
        return users;
    }

    public void settUser(Collection<TUser> tUser) {
        this.users = tUser;
    }

    public Collection<TUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<TUser> users) {
        this.users = users;
    }
}
