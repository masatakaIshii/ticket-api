package fr.esgi.ticketapi.core.dao;

import fr.esgi.ticketapi.core.entity.OrderState;

public interface OrderStateDao {
    OrderState changeOrderStateToKeep(Integer orderId);

    OrderState changeOrderStateToRefund(Integer orderId);

    void deleteOrdersStates();
}
