package fr.esgi.ticketapi.core.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserOrderState implements Serializable {
    private Integer userId;
    private List<OrderState> orderStates;

    public UserOrderState(Integer userId, List<OrderState> orderStates) {
        this.userId = userId;
        this.orderStates = orderStates;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<OrderState> getOrderStates() {
        return orderStates;
    }

    public void setOrderStates(List<OrderState> orderStates) {
        this.orderStates = orderStates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrderState that = (UserOrderState) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(orderStates, that.orderStates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, orderStates);
    }

    @Override
    public String toString() {
        return "UserOrderState{" +
                "userId=" + userId +
                ", orderStates=" + orderStates +
                '}';
    }
}
