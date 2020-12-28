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
public class GetOrderStatesTest {

    GetOrderStates getOrderStates;

    @Mock
    OrderStateDao mockOrderStateDao;

    List<OrderState> orderStatesExpected;

    @BeforeEach
    public void setup() {
        getOrderStates = new GetOrderStates(mockOrderStateDao);
        orderStatesExpected = new ArrayList<>();
        orderStatesExpected.add(new OrderState(1,1,1, LocalDate.now().minusDays(2)));
        orderStatesExpected.add(new OrderState(2,1,2, LocalDate.now().minusDays(1)));
        orderStatesExpected.add(new OrderState(3,1,1, LocalDate.now()));
        Mockito.when(mockOrderStateDao.getStatesOfOneOrder(1)).thenReturn(orderStatesExpected);
    }

    @Test
    public void should_return_states_of_one_order() {
        int orderId = 1;
        List<OrderState> orderStates = getOrderStates.execute(orderId);

        assertEquals(orderStatesExpected,orderStates);
    }

    @Test
    public void should_call_get_states_of_order_from_order_state_dao_at_least_once() {
        int orderId = 1;
        List<OrderState> orderStates = getOrderStates.execute(orderId);

        Mockito.verify(mockOrderStateDao, Mockito.atLeastOnce()).getStatesOfOneOrder(1);
    }
}
