package fr.esgi.ticketapi.infrastructure.dataprovider.dao;

import fr.esgi.ticketapi.core.dao.OrderDao;
import fr.esgi.ticketapi.core.entity.Order;
import fr.esgi.ticketapi.infrastructure.dataprovider.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("MySQLOrderDao")
public class MySQLOrderDao implements OrderDao {

    private final OrderRepository orderRepository;

    public MySQLOrderDao(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrders() {
        return this.orderRepository
                .findAll()
                .stream()
                .map(order -> order.asOrderEntity())
                .collect(Collectors.toList());
    }
}
