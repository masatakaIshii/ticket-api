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
    OrderState orderStateKept;
    OrderState orderStateAlreadyKept;
    OrderState orderStateRefund;

    @Mock
    OrderStateDao mockOrderStateDao;

    @BeforeEach
    public void setUp() {
        orderStateRefund = new OrderState(1, 1, State.REFUND, LocalDate.now().minusDays(5));
        orderStateKept = new OrderState(2, 1, State.KEEP, LocalDate.now());
        orderStateAlreadyKept = new OrderState(3, 2, State.KEEP, LocalDate.now().minusDays(5));
        changeOrderStateToKeep = new ChangeOrderStateToKeep(mockOrderStateDao);
    }

    @Test
    void should_call_order_state_dao_once() {
        OrderState orderState = changeOrderStateToKeep.execute(1);
        Mockito.verify(mockOrderStateDao, Mockito.atLeastOnce()).changeOrderStateToKeep(1);
    }

    @Test
    void should_return_order_state_keep_if_refund_before() {
        Mockito.when(mockOrderStateDao.changeOrderStateToKeep(Mockito.anyInt())).thenReturn(orderStateAlreadyKept);
        OrderState orderState = changeOrderStateToKeep.execute(orderStateAlreadyKept.getOrderId());
        assertEquals(orderStateAlreadyKept, orderState);
    }

}
