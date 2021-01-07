package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.core.entity.State;
import fr.esgi.ticketapi.usecase.emails.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChangeOrderStateToRefundTest {

    ChangeOrderStateToRefund changeOrderStateToRefund;
    OrderState orderStateTested;

    @Mock
    OrderStateDao mockOrderStateDao;

    @Mock
    SendEmail mockSendEmail;

    @BeforeEach
    public void setup() {
        orderStateTested = new OrderState(3, 2, State.REFUND, LocalDate.now().minusDays(5));
        changeOrderStateToRefund = new ChangeOrderStateToRefund(mockOrderStateDao, mockSendEmail);
    }

    @Test
    void should_call_order_state_dao_once() {
        changeOrderStateToRefund.execute(1);
        Mockito.verify(mockOrderStateDao, Mockito.atLeastOnce()).changeOrderState(1, State.REFUND);
    }

    @Test
    void should_call_send_email_after_changing() {
        changeOrderStateToRefund.execute(1);
        Mockito.verify(mockSendEmail, Mockito.atLeastOnce()).execute(Mockito.anyString());
    }

    @Test
    void should_returns_what_dao_returns() {
        Mockito.when(mockOrderStateDao.changeOrderState(orderStateTested.getOrderId(), State.REFUND)).thenReturn(orderStateTested);
        OrderState orderState = changeOrderStateToRefund.execute(orderStateTested.getOrderId());
        assertEquals(orderStateTested, orderState);
    }

}
