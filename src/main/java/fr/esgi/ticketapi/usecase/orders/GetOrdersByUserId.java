package fr.esgi.ticketapi.usecase.orders;

import fr.esgi.ticketapi.core.dao.OrderDao;
import fr.esgi.ticketapi.core.entity.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrdersByUserId {
    OrderDao orderDao;

    public GetOrdersByUserId(@Qualifier("mockableOrderDao") OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> execute(Integer userId) {
        
        return this.orderDao.getOrdersByUserId(userId);
    }
}
