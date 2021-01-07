package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.usecase.emails.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeleteOrdersStatesTest {

    DeleteOrdersStates deleteOrdersStates;


    @Mock
    OrderStateDao mockOrderStateDao;

    @Mock
    SendEmail mockSendEmail;


    @BeforeEach
    public void setUp() {
        deleteOrdersStates = new DeleteOrdersStates(mockOrderStateDao, mockSendEmail);
    }

    @Test
    void should_call_order_state_dao_once() {
        deleteOrdersStates.execute();
        Mockito.verify(mockOrderStateDao, Mockito.atLeastOnce()).deleteOrdersStates();
    }

    @Test
    void should_send_mail_after_deleted() {
        deleteOrdersStates.execute();
        Mockito.verify(mockSendEmail, Mockito.atLeastOnce()).execute(Mockito.anyString());
    }


}
