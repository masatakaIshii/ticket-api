package fr.esgi.ticketapi.infrastructure.entrypoints.rest;

import fr.esgi.ticketapi.core.entity.Order;
import fr.esgi.ticketapi.usecase.orders.GetOrders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final GetOrders getOrders;

    public OrdersController(GetOrders getOrders) {
        this.getOrders = getOrders;
    }

    @GetMapping("")
    public List<Order> getAllOrders() {
        return getOrders.execute();
    }

}
