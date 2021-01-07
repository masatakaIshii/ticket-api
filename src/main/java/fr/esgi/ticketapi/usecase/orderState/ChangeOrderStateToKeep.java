package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.core.entity.State;
import fr.esgi.ticketapi.usecase.emails.SendEmail;
import org.springframework.stereotype.Service;

@Service
public class ChangeOrderStateToKeep {

    private final OrderStateDao orderStateDao;
    private final SendEmail sendEmail;

    public ChangeOrderStateToKeep(OrderStateDao orderStateDao, SendEmail sendEmail) {
        this.orderStateDao = orderStateDao;
        this.sendEmail = sendEmail;
    }

    public OrderState execute(Integer orderId) {
        OrderState orderState = this.orderStateDao.changeOrderState(orderId, State.KEEP);
        this.sendEmail.execute("Answer changed for this command " + orderState);
        return orderState;
    }
}
