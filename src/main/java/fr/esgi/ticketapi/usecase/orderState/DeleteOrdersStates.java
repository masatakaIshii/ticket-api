package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.usecase.emails.SendEmail;
import org.springframework.stereotype.Service;

@Service
public class DeleteOrdersStates {

    private final OrderStateDao orderStateDao;
    private final SendEmail sendEmail;

    public DeleteOrdersStates(OrderStateDao orderStateDao, SendEmail sendEmail) {
        this.orderStateDao = orderStateDao;
        this.sendEmail = sendEmail;
    }

    public void execute() {
        this.orderStateDao.deleteOrdersStates();
        this.sendEmail.execute("Your choice has been deleted");
    }
}
