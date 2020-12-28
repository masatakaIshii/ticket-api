package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class getOrdersStatesTest {

    GetOrdersStates getOrdersStates;

    @Mock
    OrderStateDao mockOrderStateDao;

    List<OrderState> orderStatesExpected;

    @BeforeEach
    public void setup() {
        getOrdersStates = new GetOrdersStates(mockOrderStateDao);
        orderStatesExpected = new ArrayList<>();
        orderStatesExpected.add(new OrderState(1,1,1, LocalDate.now().minusDays(2)));
        orderStatesExpected.add(new OrderState(2,2,2, LocalDate.now().minusDays(1)));
        orderStatesExpected.add(new OrderState(3,2,1, LocalDate.now()));
        orderStatesExpected.add(new OrderState(4,3,1, LocalDate.now().minusDays(1)));
        orderStatesExpected.add(new OrderState(5,3,2, LocalDate.now()));
        Mockito.when(mockOrderStateDao.getStatesOfOrders()).thenReturn(orderStatesExpected);
    }

    @Test
    public void should_return_states_of_orders() {
        List<OrderState> orderStates = getOrdersStates.execute();

        assertEquals(orderStatesExpected,orderStates);
    }

    @Test
    public void should_call_get_states_of_order_from_order_state_dao_at_least_once() { ;
        List<OrderState> orderStates = getOrdersStates.execute();

        Mockito.verify(mockOrderStateDao, Mockito.atLeastOnce()).getStatesOfOrders();
    }
}
