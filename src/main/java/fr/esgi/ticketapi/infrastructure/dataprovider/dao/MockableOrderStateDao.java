package fr.esgi.ticketapi.infrastructure.dataprovider.dao;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.core.entity.State;
import fr.esgi.ticketapi.infrastructure.dataprovider.repository.OrderStateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MockableOrderStateDao implements OrderStateDao {

    private final OrderStateRepository orderStateRepository;

    public MockableOrderStateDao(OrderStateRepository orderStateRepository) {
        this.orderStateRepository = orderStateRepository;
    }


    @Override
    public OrderState changeOrderStateToKeep(Integer orderId) {
        //TODO change when get current will be done
        //OrderState lastOrderState = this.getCurrentOrderState(orderId);
        OrderState currentOrderState = new OrderState(3, orderId, State.REFUND, LocalDate.now().minusDays(5));

        if (currentOrderState.getStateId() == State.KEEP) {
            return currentOrderState;
        }

        fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState newOrderState =
                this.orderStateRepository.save(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(
                        currentOrderState.getOrderId(),
                        State.KEEP
                ));

        return new OrderState(newOrderState.getId(), newOrderState.getOrderId(), newOrderState.getStateId(), newOrderState.getDate());
    }

    //TODO
    @Override
    public OrderState changeOrderStateToRefund(Integer orderId) {
        return null;
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
}
