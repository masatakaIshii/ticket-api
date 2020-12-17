package fr.esgi.ticketapi.usecase.orders;

import fr.esgi.ticketapi.core.dao.OrderDao;
import fr.esgi.ticketapi.core.entity.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrders {

    private final OrderDao orderDao;

    public GetOrders(@Qualifier("MySQLOrderDao") OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> execute() {
        return this.orderDao.getOrders();
    }
}
