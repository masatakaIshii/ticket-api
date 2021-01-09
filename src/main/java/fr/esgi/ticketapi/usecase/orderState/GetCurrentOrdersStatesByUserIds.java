package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderDao;
import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.Order;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.core.entity.UserOrderState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetCurrentOrdersStatesByUserIds {
    private OrderStateDao orderStateDao;
    private OrderDao orderDao;

    public GetCurrentOrdersStatesByUserIds(OrderStateDao orderStateDao, OrderDao mockableOrderDao) {
        this.orderStateDao = orderStateDao;
        this.orderDao = mockableOrderDao;
    }
    public List<UserOrderState> execute(List<Integer> userIds) {
        List<UserOrderState> userOrderStates = new ArrayList<>();
        userIds.forEach(userId -> {
            List<Order> orders = orderDao.getOrdersByUserId(userId);
            if (orders != null) {
                List<Integer> orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());
                List<OrderState> statesOfOrderIds = orderStateDao.getCurrentStatesOfOrderIds(orderIds);
                userOrderStates.add(new UserOrderState(userId, statesOfOrderIds));
            }
        });
        return userOrderStates;
    }

}
