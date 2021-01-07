package fr.esgi.ticketapi.core.dao;

import fr.esgi.ticketapi.core.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getOrders();

    public List<Order> getOrdersByUserId(Integer userId);

    public Order getOrderByOrderIdAndUserId(Integer userId, Integer orderId);
}
