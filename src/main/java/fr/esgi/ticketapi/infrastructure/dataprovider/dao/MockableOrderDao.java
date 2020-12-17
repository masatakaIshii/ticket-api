package fr.esgi.ticketapi.infrastructure.dataprovider.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.ticketapi.core.dao.OrderDao;
import fr.esgi.ticketapi.core.entity.Order;
import fr.esgi.ticketapi.infrastructure.dataprovider.api.ApiOrder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service("MockableOrderDao")
public class MockableOrderDao implements OrderDao {
    ApiOrder apiOrder;

    public MockableOrderDao(ApiOrder apiOrder) {
        this.apiOrder = apiOrder;
    }

    @Override
    public List<Order> getOrders() {
        return apiOrder.getOrders();
    }
}
