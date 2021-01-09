package fr.esgi.ticketapi.core.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class OrderState implements Serializable {
    private Integer id;
    private Integer orderId;
    private Integer stateId;
    private LocalDate date;

    public OrderState(Integer id, Integer orderId, Integer stateId, LocalDate date) {
        this.id = id;
        this.orderId = orderId;
        this.stateId = stateId;
        this.date = date;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderState that = (OrderState) o;
        return Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId) && Objects.equals(stateId, that.stateId) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, stateId, date);
    }

    @Override
    public String toString() {
        return "OrderState{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", stateId=" + stateId +
                ", date=" + date +
                '}';
    }
}
