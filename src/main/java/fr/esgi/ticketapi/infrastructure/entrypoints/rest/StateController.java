package fr.esgi.ticketapi.infrastructure.entrypoints.rest;


import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.core.entity.UserOrderState;
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
    private AddOrderStates addOrderStates;
    private GetOrdersStatsByUserIds getOrdersStatsByUserIds;
    private GetCurrentOrdersStatesByUserIds getCurrentOrdersStatesByUserIds;

    public StateController(ChangeOrderStateToKeep changeOrderStateToKeep, ChangeOrderStateToRefund changeOrderStateToRefund,
                           DeleteOrdersStates deleteOrdersStates, GetCurrentStateOrders getCurrentStateOrders,
                           GetOrdersStates getOrdersStates, GetOrderStates getOrderStates, AddOrderStates addOrderStates, GetOrdersStatsByUserIds getOrdersStatsByUserIds, GetCurrentOrdersStatesByUserIds getCurrentOrdersStatesByUserIds) {

        this.changeOrderStateToKeep = changeOrderStateToKeep;
        this.changeOrderStateToRefund = changeOrderStateToRefund;
        this.deleteOrdersStates = deleteOrdersStates;
        this.getCurrentStateOrders = getCurrentStateOrders;
        this.getOrdersStates = getOrdersStates;
        this.getOrderStates = getOrderStates;
        this.addOrderStates = addOrderStates;
        this.getOrdersStatsByUserIds = getOrdersStatsByUserIds;
        this.getCurrentOrdersStatesByUserIds = getCurrentOrdersStatesByUserIds;
    }

    @GetMapping("")
    public List<OrderState> getStatesOfOrders() {
        return this.getOrdersStates.execute();
    }

    @GetMapping("/{id}")
    public List<OrderState> getStatesOfOneOrder(@PathVariable(name = "id") Integer orderId) {
        return this.getOrderStates.execute(orderId);
    }

    @GetMapping("/users")
    public List<UserOrderState> getStatesOfOrderfromUsers(@RequestParam("userIds") List<Integer> userIds) {
        return this.getOrdersStatsByUserIds.execute(userIds);
    }

    @GetMapping("/current/{id}")
    public List<OrderState> getCurrentStateOfOrder(@PathVariable(name = "id") Integer orderId) {
        return null;
    }

    @GetMapping("/current/users")
    public List<UserOrderState> getCurrentStatesOfOrderfromUsers(@RequestParam("userIds") List<Integer> userIds) {
        return this.getCurrentOrdersStatesByUserIds.execute(userIds);
    }

    @GetMapping("/current")
    public List<OrderState> getCurrentStateOfOrders() {
        return this.getCurrentStateOrders.execute();
    }

    @PostMapping("/keep/{id}")
    public OrderState changeOrderStateToKeep(@PathVariable(name = "id") Integer orderId) {
        return changeOrderStateToKeep.execute(orderId);
    }

    @PostMapping("")
    public String addOrderStates(@RequestBody List<OrderState> orderStates) {
        return addOrderStates.execute(orderStates);
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
