package fr.esgi.ticketapi.usecase.orderState;


import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import org.springframework.stereotype.Service;

@Service
public class ChangeOrderStateToRefund {

    private final OrderStateDao orderStateDao;

    public ChangeOrderStateToRefund(OrderStateDao orderStateDao) {
        this.orderStateDao = orderStateDao;
    }

    public OrderState execute(Integer orderId) {
        return this.orderStateDao.changeOrderStateToRefund(orderId);
    }
}
