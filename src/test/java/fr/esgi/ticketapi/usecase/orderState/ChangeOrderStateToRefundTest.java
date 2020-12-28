package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.core.entity.State;
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

    @BeforeEach
    public void setup() {
        orderStateTested = new OrderState(3, 2, State.REFUND, LocalDate.now().minusDays(5));
        changeOrderStateToRefund = new ChangeOrderStateToRefund(mockOrderStateDao);
    }

    @Test
    void should_call_order_state_dao_once() {
        changeOrderStateToRefund.execute(1);
        Mockito.verify(mockOrderStateDao, Mockito.atLeastOnce()).changeOrderStateToRefund(1);
    }

    @Test
    void should_returns_what_dao_returns() {
        Mockito.when(mockOrderStateDao.changeOrderStateToRefund(orderStateTested.getOrderId())).thenReturn(orderStateTested);
        OrderState orderState = changeOrderStateToRefund.execute(orderStateTested.getOrderId());
        assertEquals(orderStateTested, orderState);
    }

}
