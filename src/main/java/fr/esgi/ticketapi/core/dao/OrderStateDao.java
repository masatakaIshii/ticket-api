package fr.esgi.ticketapi.core.dao;

import fr.esgi.ticketapi.core.entity.OrderState;

import java.util.List;

public interface OrderStateDao {
    OrderState changeOrderStateToKeep(Integer orderId);

    OrderState changeOrderStateToRefund(Integer orderId);

    void deleteOrdersStates();

    List<OrderState> getStatesOfOneOrder(Integer orderId);

    List<OrderState> getStatesOfOrders();

}
