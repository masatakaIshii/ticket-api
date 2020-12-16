package fr.esgi.ticketapi.infrastructure.dataprovider.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer price;

    @OneToMany(mappedBy = "order")
    private Collection<OrderState> ordersStates;

    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<OrderState> getOrdersStates() {
        return ordersStates;
    }

    public void setOrdersStates(Collection<OrderState> ordersStates) {
        this.ordersStates = ordersStates;
    }

    public fr.esgi.ticketapi.core.entity.Order asOrderEntity() {
        return new fr.esgi.ticketapi.core.entity.Order(this.id, this.price, this.user.getId());
    }
}
