package fr.esgi.ticketapi.infrastructure.dataprovider.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "state")
    private Collection<OrderState> ordersStates;

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

    public Collection<OrderState> getOrdersStates() {
        return ordersStates;
    }

    public void setOrdersStates(Collection<OrderState> ordersStates) {
        this.ordersStates = ordersStates;
    }
}
