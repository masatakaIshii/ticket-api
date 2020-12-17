package fr.esgi.ticketapi.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class OrderState implements Serializable {
    private Integer id;
    private Integer orderId;
    private Integer StateId;
    private Date date;

    public OrderState(Integer id, Integer orderId, Integer stateId, Date date) {
        this.id = id;
        this.orderId = orderId;
        StateId = stateId;
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
        return StateId;
    }

    public void setStateId(Integer stateId) {
        StateId = stateId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderState that = (OrderState) o;
        return Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId) && Objects.equals(StateId, that.StateId) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, StateId, date);
    }
}
