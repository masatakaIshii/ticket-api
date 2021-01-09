package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderDao;
import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.Order;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.core.entity.UserOrderState;
import fr.esgi.ticketapi.infrastructure.dataprovider.dao.MySQLOrderStateDao;
import fr.esgi.ticketapi.infrastructure.dataprovider.repository.OrderStateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetCurrentOrdersStatesByUserIdsTest {
    GetCurrentOrdersStatesByUserIds getCurrentOrdersStatesByUserIds;

    GetCurrentOrdersStatesByUserIds getCurrentOrdersStatesByUserIdsWithMockedRepo;


    @Mock
    OrderStateDao mockOrderStateDao;

    OrderStateDao realOrderStateDao;

    @Mock
    OrderDao mockOrderDao;

    @Mock
    OrderStateRepository mockOrderStateRepository;

    List<Integer> userIds;

    List<Order> mockOrders;

    List<fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState> mockOrderStates;

    @BeforeEach
    public void setup() {
        realOrderStateDao = new MySQLOrderStateDao(mockOrderStateRepository);

        getCurrentOrdersStatesByUserIds = new GetCurrentOrdersStatesByUserIds(mockOrderStateDao, mockOrderDao);
        getCurrentOrdersStatesByUserIdsWithMockedRepo = new GetCurrentOrdersStatesByUserIds(realOrderStateDao, mockOrderDao);

        userIds = new ArrayList<>();

        mockOrders = new ArrayList<>();
        mockOrders.add(new Order(1,2,123));
        mockOrders.add(new Order(2,3,123));

        Mockito.when(mockOrderDao.getOrdersByUserId(123)).thenReturn(mockOrders);

        List<Integer> orderIds = new ArrayList<>();
        orderIds.add(1);
        orderIds.add(2);


        mockOrderStates = new ArrayList<>();
        mockOrderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(1,1,1, LocalDate.now().minusDays(2)));
        mockOrderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(2,1,1, LocalDate.now().minusDays(1)));
        mockOrderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(3,2,1, LocalDate.now()));


        Mockito.when(mockOrderStateRepository.getAllByOrderIds(orderIds)).thenReturn(mockOrderStates);

    }

    @Test
    public void should_call_orderDao_to_get_user_orders() {
        userIds.add(123);
        getCurrentOrdersStatesByUserIds.execute(userIds);
        Mockito.verify(mockOrderDao, Mockito.times(userIds.size())).getOrdersByUserId(123);
    }

    @Test
    public void should_call_orderStateDao_to_get_user_orders_states() {
        userIds.add(123);
        getCurrentOrdersStatesByUserIds.execute(userIds);

        Mockito.verify(mockOrderStateDao, Mockito.times(userIds.size())).getCurrentStatesOfOrderIds(mockOrders.stream().map(Order::getId).collect(Collectors.toList()));
    }

    @Test
    public void should_return_last_order_state_of_each_user_orders() {
        userIds.add(123);
        List<UserOrderState> result = getCurrentOrdersStatesByUserIdsWithMockedRepo.execute(userIds);
        List<UserOrderState> expected = new ArrayList<>();
        List<OrderState> userOrdersExpected = new ArrayList<>();
        userOrdersExpected.add(new OrderState(2,1,1, LocalDate.now().minusDays(1)));
        userOrdersExpected.add(new OrderState(3,2,1, LocalDate.now()));
        expected.add(new UserOrderState(123,userOrdersExpected));

        assertEquals(expected, result);
    }
}
