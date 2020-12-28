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
class ChangeOrderStateToKeepTest {

    ChangeOrderStateToKeep changeOrderStateToKeep;
    OrderState orderStateTested;

    @Mock
    OrderStateDao mockOrderStateDao;

    @BeforeEach
    public void setUp() {
        orderStateTested = new OrderState(3, 2, State.KEEP, LocalDate.now().minusDays(5));
        changeOrderStateToKeep = new ChangeOrderStateToKeep(mockOrderStateDao);
    }

    @Test
    void should_call_order_state_dao_once() {
        changeOrderStateToKeep.execute(1);
        Mockito.verify(mockOrderStateDao, Mockito.atLeastOnce()).changeOrderState(1, State.KEEP);
    }

    @Test
    void should_returns_what_dao_returns() {
        Mockito.when(mockOrderStateDao.changeOrderState(orderStateTested.getOrderId(), State.KEEP)).thenReturn(orderStateTested);
        OrderState orderState = changeOrderStateToKeep.execute(orderStateTested.getOrderId());
        assertEquals(orderStateTested, orderState);
    }

}
