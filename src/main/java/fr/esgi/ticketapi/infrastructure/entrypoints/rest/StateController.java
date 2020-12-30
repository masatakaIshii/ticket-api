package fr.esgi.ticketapi.infrastructure.entrypoints.rest;


import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.usecase.orderState.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    private ChangeOrderStateToKeep changeOrderStateToKeep;
    private ChangeOrderStateToRefund changeOrderStateToRefund;
    private DeleteOrdersStates deleteOrdersStates;
    private GetCurrentStateOrders getCurrentStateOrders;
    private GetOrdersStates getOrdersStates;
    private GetOrderStates getOrderStates;

    public StateController(ChangeOrderStateToKeep changeOrderStateToKeep, ChangeOrderStateToRefund changeOrderStateToRefund,
                           DeleteOrdersStates deleteOrdersStates, GetCurrentStateOrders getCurrentStateOrders,
                           GetOrdersStates getOrdersStates, GetOrderStates getOrderStates) {

        this.changeOrderStateToKeep = changeOrderStateToKeep;
        this.changeOrderStateToRefund = changeOrderStateToRefund;
        this.deleteOrdersStates = deleteOrdersStates;
        this.getCurrentStateOrders = getCurrentStateOrders;
        this.getOrdersStates = getOrdersStates;
        this.getOrderStates = getOrderStates;
    }

    @GetMapping("")
    public List<OrderState> getStatesOfOrders() {
        return this.getOrdersStates.execute();
    }

    @GetMapping("/{id}")
    public List<OrderState> getStatesOfOneOrder(@PathVariable(name = "id") Integer orderId) {
        return this.getOrderStates.execute(orderId);
    }

    @GetMapping("/current/{id}")
    public List<OrderState> getCurrentStateOfOrder(@PathVariable(name = "id") Integer orderId) {
        return null;
    }

    @GetMapping("/current")
    public List<OrderState> getCurrentStateOfOrders() {
        return this.getCurrentStateOrders.execute();
    }

    @PostMapping("/keep/{id}")
    public OrderState changeOrderStateToKeep(@PathVariable(name = "id") Integer orderId) {
        return changeOrderStateToKeep.execute(orderId);
    }

    @PostMapping("/refund/{id}")
    public OrderState changeOrderStateToRefund(@PathVariable(name = "id") Integer orderId) {
        return changeOrderStateToRefund.execute(orderId);
    }

    @DeleteMapping("")
    public void deleteOrdersStates() {
        deleteOrdersStates.execute();
    }

}
