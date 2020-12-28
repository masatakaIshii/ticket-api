package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrdersStates {
    OrderStateDao orderStateDao;

    public GetOrdersStates(OrderStateDao orderStateDao) {
        this.orderStateDao = orderStateDao;
    }

    public List<OrderState> execute() {
        return orderStateDao.getStatesOfOrders();
    }
}
