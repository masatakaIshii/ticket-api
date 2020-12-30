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
public class GetCurrentStateOfOrdersTest {

    GetCurrentStateOrders getCurrentStateOrders;

    @Mock
    OrderStateDao mockOrderStateDao;

    @BeforeEach
    public void setup() {
        getCurrentStateOrders = new GetCurrentStateOrders(mockOrderStateDao);
    }

    @Test
    public void should_call_orderStateDao_to_get_current_state_orders() {
        getCurrentStateOrders.execute();
        Mockito.verify(mockOrderStateDao, Mockito.times(1)).getCurrentStateOrders();
    }

    @Test
    public void should_return_list_of_order_states() {
        List<OrderState> orderStatesExpected = new ArrayList<>();
        orderStatesExpected.add(new OrderState(1, 1, 1, LocalDate.now()));
        orderStatesExpected.add(new OrderState(2, 2, 2, LocalDate.now()));
        orderStatesExpected.add(new OrderState(3, 3, 3, LocalDate.now()));
        Mockito.when(mockOrderStateDao.getCurrentStateOrders()).thenReturn(orderStatesExpected);

        var result = getCurrentStateOrders.execute();

        assertEquals(orderStatesExpected, result);
    }
}