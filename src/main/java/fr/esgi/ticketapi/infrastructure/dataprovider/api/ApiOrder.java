package fr.esgi.ticketapi.infrastructure.dataprovider.api;

import fr.esgi.ticketapi.core.entity.Order;

import java.util.List;

public interface ApiOrder {

    public List<Order> getOrders();
}
