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
    private GetCurrentStateOfOrders getCurrentStateOfOrders;
    private GetOrdersStates getOrdersStates;
    private GetOrderStates getOrderStates;

    public StateController(ChangeOrderStateToKeep changeOrderStateToKeep, ChangeOrderStateToRefund changeOrderStateToRefund,
                           DeleteOrdersStates deleteOrdersStates, GetCurrentStateOfOrders getCurrentStateOfOrders,
                           GetOrdersStates getOrdersStates, GetOrderStates getOrderStates) {

        this.changeOrderStateToKeep = changeOrderStateToKeep;
        this.changeOrderStateToRefund = changeOrderStateToRefund;
        this.deleteOrdersStates = deleteOrdersStates;
        this.getCurrentStateOfOrders = getCurrentStateOfOrders;
        this.getOrdersStates = getOrdersStates;
        this.getOrderStates = getOrderStates;
    }

    @GetMapping("")
    public List<OrderState> getStatesOfOrders() {
        return null;
    }

    @GetMapping("/{id}")
    public List<OrderState> getStatesOfOneOrder(@PathVariable(name = "id") Integer orderId) {
        return null;
    }

    @GetMapping("/current/{id}")
    public List<OrderState> getCurrentStateOfOrder(@PathVariable(name = "id") Integer orderId) {
        return null;
    }

    @GetMapping("/current}")
    public List<OrderState> getCurrentStateOfOrders() {
        return null;
    }

    @PostMapping("/keep/{id}")
    public OrderState changeOrderStateToKeep(@PathVariable(name = "id") Integer orderId) {
        return null;
    }

    @PostMapping("/refund/{id}")
    public OrderState changeOrderStateToRefund(@PathVariable(name = "id") Integer orderId) {
        return null;
    }

    @DeleteMapping("")
    public void deleteOrdersStates(@PathVariable(name = "id") Integer orderId) {
    }

}
