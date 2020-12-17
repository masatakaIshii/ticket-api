package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChangeOrderStateToKeepTest {

    ChangeOrderStateToKeep changeOrderStateToKeep;
    OrderState orderStateTested;

    @Mock
    OrderStateDao orderStateDao;

    @BeforeEach
    public void setUp() {
        orderStateTested = new OrderState(2, 1, 1, new Date());
        changeOrderStateToKeep = new ChangeOrderStateToKeep(orderStateDao);
    }

    @Test
    void should_return_new_order_state() {
        OrderState orderState = changeOrderStateToKeep.execute(1);
        assertEquals(orderStateTested, orderState);
    }


}
