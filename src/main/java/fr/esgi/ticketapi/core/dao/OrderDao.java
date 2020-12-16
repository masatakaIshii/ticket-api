package fr.esgi.ticketapi.core.dao;

import fr.esgi.ticketapi.core.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getOrders();
}
