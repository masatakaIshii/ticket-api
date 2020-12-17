package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import org.springframework.stereotype.Service;

@Service
public class ChangeOrderStateToKeep {

    private final OrderStateDao orderStateDao;

    public ChangeOrderStateToKeep(OrderStateDao orderStateDao) {
        this.orderStateDao = orderStateDao;
    }

    public OrderState execute(Integer orderId) {
        return this.orderStateDao.changeOrderStateToKeep(orderId);
    }
}
