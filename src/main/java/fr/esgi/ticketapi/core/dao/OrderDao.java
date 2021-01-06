package fr.esgi.ticketapi.core.dao;

import fr.esgi.ticketapi.core.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getOrders();

    public List<Order> getOrdersByUserId(Long userId);

    public Order getOrderByOrderIdAndUserId(Long userId, Long orderId);
}
