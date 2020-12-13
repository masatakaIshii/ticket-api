package fr.esgi.ticketapi.dataprovider.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TOrderState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private TOrder order;

    @ManyToOne
    private TState state;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TOrder getOrder() {
        return order;
    }

    public void setOrder(TOrder order) {
        this.order = order;
    }

    public TState getState() {
        return state;
    }

    public void setState(TState state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
