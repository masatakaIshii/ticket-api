package fr.esgi.ticketapi.infrastructure.dataprovider.dao;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.infrastructure.dataprovider.repository.OrderStateRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class MySQLOrderStateDao implements OrderStateDao {

    private final OrderStateRepository orderStateRepository;

    public MySQLOrderStateDao(OrderStateRepository orderStateRepository) {
        this.orderStateRepository = orderStateRepository;
    }

    @Override
    public OrderState changeOrderState(Integer orderId, int stateId) {

        OrderState currentOrderState = this.getCurrentStateOfOrderById(orderId);

        if (currentOrderState != null && currentOrderState.getStateId() == stateId) {
            return currentOrderState;
        }

        fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState newOrderState =
                this.orderStateRepository.save(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(
                        orderId,
                        stateId
                ));

        return new OrderState(newOrderState.getId(), newOrderState.getOrderId(), newOrderState.getStateId(), newOrderState.getDate());
    }

    public OrderState getCurrentStateOfOrderById(Integer orderId) {
        List<OrderState> orderStates = this.getCurrentStateOrders();

        try {
            return orderStates
                    .stream()
                    .filter(orderState -> orderState.getOrderId().equals(orderId))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public void deleteOrdersStates() {
        this.orderStateRepository.deleteAll();
    }

    @Override
    public List<OrderState> getStatesOfOneOrder(Integer orderId) {
        List<fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState> ordersStates = this.orderStateRepository.getAllByOrderId(orderId);
        return ordersStates.stream().map(orderState -> new OrderState(orderState.getId(), orderState.getOrderId(), orderState.getStateId(), orderState.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderState> getStatesOfOrders() {
        List<fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState> ordersStates = this.orderStateRepository.getAll();
        return ordersStates.stream().map(orderState -> new OrderState(orderState.getId(), orderState.getOrderId(), orderState.getStateId(), orderState.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderState> getCurrentStateOrders() {
        return this.orderStateRepository.getAll().stream()
                .map(orderState -> new OrderState(orderState.getId(), orderState.getOrderId(), orderState.getStateId(), orderState.getDate()))
                .sorted((orderState1, orderState2) -> orderState2.getDate().compareTo(orderState1.getDate()))
                .filter(distinctByKey(OrderState::getOrderId))
                .sorted(Comparator.comparing(OrderState::getId))
                .collect(Collectors.toList());
    }

    @Override
    public String addOrderStates(List<OrderState> orderStates) {
        for (OrderState orderState : orderStates) {
            if (orderState.getStateId() == null) {
                return "Some tickets don't have any answer";
            }
        }

        List<fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState> mySQLOrderStates =
                orderStates
                        .stream()
                        .map(orderState ->
                                new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(
                                        orderState.getOrderId(), orderState.getStateId()
                                ))
                        .collect(Collectors.toList());

        this.orderStateRepository.saveAll(mySQLOrderStates);

        return "Your tickets have been registered";
    }

    private static <OrderState> Predicate<OrderState> distinctByKey(
            Function<? super OrderState, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
