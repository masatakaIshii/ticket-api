package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.usecase.emails.SendEmail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddOrderStates {

    private final OrderStateDao orderStateDao;
    private final SendEmail sendEmail;

    public AddOrderStates(OrderStateDao orderStateDao, SendEmail sendEmail) {
        this.orderStateDao = orderStateDao;
        this.sendEmail = sendEmail;
    }

    public String execute(List<OrderState> orderStates) {
        String result = this.orderStateDao.addOrderStates(orderStates);
        this.sendEmail.execute(result + " added");
        return result;
    }
}
