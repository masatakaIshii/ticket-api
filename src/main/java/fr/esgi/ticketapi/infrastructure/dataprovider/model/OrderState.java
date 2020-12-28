package fr.esgi.ticketapi.infrastructure.dataprovider.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class OrderState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "state_id")
    private Integer stateId;

    @CreatedDate
    private LocalDate date;

    public OrderState(Integer orderId, Integer stateId) {
        this.orderId = orderId;
        this.stateId = stateId;
    }

    public OrderState(Integer id, Integer orderId, Integer stateId, LocalDate date) {
        this.id = id;
        this.orderId = orderId;
        this.stateId = stateId;
        this.date = date;
    }

    public OrderState() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
