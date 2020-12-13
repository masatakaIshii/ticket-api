package fr.esgi.ticketapi.dataprovider.model;

import fr.esgi.ticketapi.core.entity.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class TOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer price;

    @OneToMany(mappedBy = "order")
    private Collection<TOrderState> ordersStates;

    @ManyToOne
    private TUser user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }
}
