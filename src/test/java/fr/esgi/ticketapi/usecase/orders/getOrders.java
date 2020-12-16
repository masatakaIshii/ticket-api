package fr.esgi.ticketapi.usecase.orders;

import fr.esgi.ticketapi.core.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import fr.esgi.ticketapi.usecase.orders.GetOrders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class getOrders {

    GetOrders getOrders = new GetOrders();
    List<Order> resultTest = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        resultTest = new ArrayList<>();
        resultTest.add(new Order(1, 15, 1));
        resultTest.add(new Order(2, 88, 7777));
    }

    @Test
    public void should_return_all_orders() {
        List<Order> allOrders = getOrders.execute();
        assertEquals(resultTest, allOrders);
    }
}
