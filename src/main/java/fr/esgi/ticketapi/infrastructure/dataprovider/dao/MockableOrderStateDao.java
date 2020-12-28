package fr.esgi.ticketapi.infrastructure.dataprovider.dao;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.infrastructure.dataprovider.repository.OrderStateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MockableOrderStateDao implements OrderStateDao {

    private final OrderStateRepository orderStateRepository;

    public MockableOrderStateDao(OrderStateRepository orderStateRepository) {
        this.orderStateRepository = orderStateRepository;
    }

    //TODO
    @Override
    public OrderState changeOrderStateToKeep(Integer orderId) {
        //Get current state
        //fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState currentOrderState = ...;       //insert s'il a un statut différent
        //Il faut ue enumeration KEEP_STATUT QUELQUE PART A MON AVIS
        /*if (currentOrderState.getStateId() == State.REFUND) {
            currentOrderState.setStateId(1);
            fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState newOrderState
                    = this.orderStateRepository.save(currentOrderState);
        }*/

        return null;
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
