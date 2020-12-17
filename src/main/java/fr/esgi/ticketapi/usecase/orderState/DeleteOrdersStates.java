package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import org.springframework.stereotype.Service;

@Service
public class DeleteOrdersStates {

    private final OrderStateDao orderStateDao;

    public DeleteOrdersStates(OrderStateDao orderStateDao) {
        this.orderStateDao = orderStateDao;
    }

    public void execute() {
        this.orderStateDao.deleteOrdersStates();
    }
}
