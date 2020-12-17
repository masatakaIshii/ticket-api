package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
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
        orderStateTested = new OrderState(2, 1, 1, LocalDate.of(2020, 12, 12));
        changeOrderStateToKeep = new ChangeOrderStateToKeep(mockOrderStateDao);
    }

    @Test
    void should_call_order_state_dao_once() {
        OrderState orderState = changeOrderStateToKeep.execute(1);
        Mockito.verify(mockOrderStateDao, Mockito.atLeastOnce()).changeOrderStateToKeep(1);
    }

    @Test
    void should_return_new_order_state() {
        Mockito.when(mockOrderStateDao.changeOrderStateToKeep(Mockito.anyInt())).thenReturn(orderStateTested);
        OrderState orderState = changeOrderStateToKeep.execute(orderStateTested.getId());
        assertEquals(orderStateTested, orderState);
    }


}
