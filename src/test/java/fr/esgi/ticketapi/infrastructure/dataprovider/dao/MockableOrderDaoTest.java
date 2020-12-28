package fr.esgi.ticketapi.infrastructure.dataprovider.dao;

import fr.esgi.ticketapi.core.entity.Order;
import fr.esgi.ticketapi.infrastructure.dataprovider.api.ApiOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MockableOrderDaoTest {

    MockableOrderDao mockableOrderDao;

    @Mock
    ApiOrder mockApiOrder;

    @BeforeEach
    public void setUp() {
        mockableOrderDao = new MockableOrderDao(mockApiOrder);
    }

    @Test
    public void getOrders_shouldCallApiToGetOrders() {
        mockableOrderDao.getOrders();
        Mockito.verify(mockApiOrder, Mockito.times(1)).getOrders();
    }

    @Test
    public void getOrders_shouldReturnListOrders() {
        var orders = new ArrayList<Order>();

        orders.add(new Order(9, 8, 7));
        orders.add(new Order(4, 5, 6));
        Mockito.when(mockApiOrder.getOrders()).thenReturn(orders);
        var result = mockableOrderDao.getOrders();
        assertTrue(result.containsAll(orders) && orders.containsAll(result));
    }


}
