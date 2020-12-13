package fr.esgi.ticketapi.dataprovider.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class TState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "state")
    private Collection<TOrderState> ordersStates;

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

    public Collection<TOrderState> getOrdersStates() {
        return ordersStates;
    }

    public void setOrdersStates(Collection<TOrderState> ordersStates) {
        this.ordersStates = ordersStates;
    }
}
