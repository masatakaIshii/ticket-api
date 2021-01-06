package fr.esgi.ticketapi.infrastructure.dataprovider.dao;

import fr.esgi.ticketapi.core.dao.OrderDao;
import fr.esgi.ticketapi.core.entity.Order;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class MockableOrderDao implements OrderDao {

    private static final String MOCKABLE_URL = "https://demo2009247.mockable.io/";
    private static final String FIRST_USER_PARAM = "user/123";
    private static final String SECOND_USER_PARAM = "user/456";

    private RestTemplate restTemplate;

    public MockableOrderDao(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<Order> getOrders() {
        var firstUserOrders = fetchListOrdersByUrl(MOCKABLE_URL + FIRST_USER_PARAM + "/order");
        var secondUserOrders = fetchListOrdersByUrl(MOCKABLE_URL + SECOND_USER_PARAM + "/order");

        var result = new ArrayList<Order>(firstUserOrders.size() + secondUserOrders.size());
        result.addAll(firstUserOrders);
        result.addAll(secondUserOrders);

        return result;
    }

    private List<Order> fetchListOrdersByUrl(String url) {
        var result = restTemplate.getForEntity(url, Order[].class);
        var body = result.getBody();
        return Arrays.asList(Objects.requireNonNull(body));
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return null;
    }

    @Override
    public Order getOrderByOrderIdAndUserId(Long userId, Long orderId) {
        return null;
    }

}
