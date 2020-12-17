package fr.esgi.ticketapi.usecase.orders;

import fr.esgi.ticketapi.core.dao.OrderDao;
import fr.esgi.ticketapi.core.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrders {

    private final OrderDao orderDao;

    public GetOrders(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> execute() {
        return this.orderDao.getOrders();
    }
}
