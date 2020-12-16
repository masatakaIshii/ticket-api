package fr.esgi.ticketapi.usecase.orders;

import fr.esgi.ticketapi.core.dao.OrderDao;
import fr.esgi.ticketapi.core.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetOrdersTest {

    GetOrders getOrders;
    List<Order> resultTest = new ArrayList<>();

    @Mock
    OrderDao mockOrderDao;

    @BeforeEach
    public void setUp() {
        getOrders = new GetOrders(mockOrderDao);
        resultTest = new ArrayList<>();
        resultTest.add(new Order(1, 15, 1));
        resultTest.add(new Order(2, 88, 7777));
    }

    @Test
    public void should_call_order_dao() {
        List<Order> allOrders = getOrders.execute();
        Mockito.verify(mockOrderDao, Mockito.atLeastOnce()).getOrders();
    }

    @Test
    public void should_return_all_orders() {
        Mockito.when(mockOrderDao.getOrders()).thenReturn(resultTest);
        List<Order> allOrders = getOrders.execute();
        assertEquals(resultTest, allOrders);
    }
}
