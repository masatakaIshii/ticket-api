package fr.esgi.ticketapi.infrastructure.entrypoints.rest;

import fr.esgi.ticketapi.core.entity.Order;
import fr.esgi.ticketapi.usecase.orders.GetOrders;
import fr.esgi.ticketapi.usecase.orders.GetOrdersByUserId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final GetOrders getOrders;
    private final GetOrdersByUserId getOrdersByUserId;

    public OrdersController(GetOrders getOrders, GetOrdersByUserId getOrdersByUserId) {
        this.getOrders = getOrders;
        this.getOrdersByUserId = getOrdersByUserId;
    }

    @GetMapping("")
    public List<Order> getAllOrders() {
        return getOrders.execute();
    }

    @GetMapping("user/{id}")
    public List<Order> getAllUserOrders(@PathVariable("id") Integer userId) {

        return getOrders.execute();
    }

}
