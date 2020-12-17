package fr.esgi.ticketapi.infrastructure.dataprovider.dao;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.infrastructure.dataprovider.repository.OrderStateRepository;
import org.springframework.stereotype.Service;

@Service
public class MockableOrderStateDao implements OrderStateDao {

    private final OrderStateRepository orderStateRepository;

    public MockableOrderStateDao(OrderStateRepository orderStateRepository) {
        this.orderStateRepository = orderStateRepository;
    }

    @Override
    public OrderState changeOrderStateToKeep(Integer orderId) {
        return null;
    }

    @Override
    public OrderState changeOrderStateToRefund(Integer orderId) {
        return null;
    }

    @Override
    public boolean deleteOrdersStates(Integer orderId) {
        return false;
    }
}
