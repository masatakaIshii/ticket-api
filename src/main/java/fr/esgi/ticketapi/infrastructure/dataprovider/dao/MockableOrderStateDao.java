package fr.esgi.ticketapi.infrastructure.dataprovider.dao;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.core.entity.State;
import fr.esgi.ticketapi.infrastructure.dataprovider.repository.OrderStateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
