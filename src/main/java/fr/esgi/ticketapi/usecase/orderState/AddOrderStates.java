package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.core.entity.State;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddOrderStates {

    private final OrderStateDao orderStateDao;

    public AddOrderStates(OrderStateDao orderStateDao) {
        this.orderStateDao = orderStateDao;
    }

    public String execute(List<OrderState> orderStates) {
        return this.orderStateDao.addOrderStates(orderStates);
    }
}
