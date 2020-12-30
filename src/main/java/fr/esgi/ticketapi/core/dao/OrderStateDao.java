package fr.esgi.ticketapi.core.dao;

import fr.esgi.ticketapi.core.entity.OrderState;

import java.util.List;

public interface OrderStateDao {
    OrderState changeOrderState(Integer orderId, int stateId);

    void deleteOrdersStates();

    List<OrderState> getStatesOfOneOrder(Integer orderId);

    List<OrderState> getStatesOfOrders();

    List<OrderState> getCurrentStateOrders();
}
