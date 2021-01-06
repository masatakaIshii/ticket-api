package fr.esgi.ticketapi.infrastructure.dataprovider.dao;

import fr.esgi.ticketapi.core.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MockableOrderDaoTest {
    MockableOrderDao mockableOrderDao;

    private final String MOCKABLE_URL_FIRST_USER_ORDER = "https://demo2009247.mockable.io/user/123/order";
    private final String MOCKABLE_URL_SECOND_USER_ORDER = "https://demo2009247.mockable.io/user/456/order";

    @Mock
    RestTemplateBuilder mockRestTemplateBuilder;
    @Mock
    RestTemplate mockRestTemplate;
    @Mock
    ResponseEntity<Order[]> mockResponseOrders;

    List<Order> firstOrders;
    List<Order> secondOrders;

    @BeforeEach
    public void setUp() {
        Mockito.when(mockRestTemplateBuilder.build()).thenReturn(mockRestTemplate);
        mockableOrderDao = new MockableOrderDao(mockRestTemplateBuilder);

        firstOrders = new ArrayList<>();
        firstOrders.add(new Order(1, 2, 3));
        secondOrders = new ArrayList<>();
        secondOrders.add(new Order(4, 5, 6));

        Mockito.when(mockRestTemplate.getForEntity(MOCKABLE_URL_FIRST_USER_ORDER, Order[].class))
                .thenReturn(mockResponseOrders);
        Mockito.when(mockRestTemplate.getForEntity(MOCKABLE_URL_SECOND_USER_ORDER, Order[].class))
                .thenReturn(mockResponseOrders);

        Mockito.when(mockResponseOrders.getBody())
                .thenReturn(firstOrders.toArray(Order[]::new))
                .thenReturn(secondOrders.toArray(Order[]::new));
    }

    @Test
    public void getOrders_shouldCallAtLeastOnceRestTemplate() {
        mockableOrderDao.getOrders();
        Mockito.verify(mockRestTemplate, Mockito.times(1)).getForEntity(
                MOCKABLE_URL_FIRST_USER_ORDER,
                Order[].class
        );
    }

    @Test
    public void getOrders_shouldCallSecondTimesRestTemplate() {
        mockableOrderDao.getOrders();
        Mockito.verify(mockRestTemplate, Mockito.times(1)).getForEntity(
                MOCKABLE_URL_SECOND_USER_ORDER,
                Order[].class
        );
    }

    @Test
    public void getOrders_shouldCall2TimesGetBody() {
        mockableOrderDao.getOrders();

        Mockito.verify(mockResponseOrders, Mockito.times(2)).getBody();
    }

    @Test
    public void getOrders_shouldGetListOrdersOf2Calls() {
        var allOrders = new ArrayList<Order>();
        allOrders.addAll(firstOrders);
        allOrders.addAll(secondOrders);

        var result = mockableOrderDao.getOrders();
        assertTrue(result.size() == allOrders.size() && result.containsAll(allOrders) && allOrders.containsAll(result));
    }

    @Test
    public void getOrdersByUserId_shouldCallRestTemplateToGetOrderListOfUserId() {
        int userId = 1;
        String url = "https://demo2009247.mockable.io/user/" + userId + "/order";
        Mockito.when(mockRestTemplate.getForEntity(url, Order[].class))
                .thenReturn(mockResponseOrders);
        mockableOrderDao.getOrdersByUserId(1);
        Mockito.verify(mockRestTemplate, Mockito.times(1))
                .getForEntity("https://demo2009247.mockable.io/user/1/order", Order[].class);
    }

    @Test
    public void getOrdersByUserId_shouldReturnOrderListOfOtherUserId() {
        int userId = 2;
        String url = "https://demo2009247.mockable.io/user/" + userId + "/order";
        var userOrders = new ArrayList<Order>();

        userOrders.add(new Order(2, 2, 2));
        userOrders.add(new Order(3, 3, 2));
        userOrders.add(new Order(4, 4, 2));
        Mockito.when(mockRestTemplate.getForEntity(url, Order[].class))
                .thenReturn(mockResponseOrders);
        Mockito.when(mockResponseOrders.getBody())
                .thenReturn(userOrders.toArray(Order[]::new));

        var result = mockableOrderDao.getOrdersByUserId(userId);
        assertEquals(userOrders.size(), result.size());
        assertTrue(result.containsAll(userOrders));
        assertTrue(userOrders.containsAll(result));
    }
}
