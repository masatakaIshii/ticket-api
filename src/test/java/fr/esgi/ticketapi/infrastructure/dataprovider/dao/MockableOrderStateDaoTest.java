package fr.esgi.ticketapi.infrastructure.dataprovider.dao;

import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.core.entity.State;
import fr.esgi.ticketapi.infrastructure.dataprovider.repository.OrderStateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MockableOrderStateDaoTest {

    MockableOrderStateDao mockableOrderStateDao;

    OrderState orderStateAlreadyKept;
    fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState newOrderStateRefund;
    OrderState orderStateKeptForRefundTest;
    OrderState orderStateRefundForRefundTest;

    @Mock
    OrderStateRepository mockOrderStateRepository;

    @BeforeEach
    public void setup() {
        mockableOrderStateDao = new MockableOrderStateDao(mockOrderStateRepository);

        orderStateAlreadyKept = new OrderState(3, 2, State.KEEP, LocalDate.now().minusDays(5));
        orderStateKeptForRefundTest = new OrderState(6, 3, State.KEEP, LocalDate.now().minusDays(5));
        orderStateRefundForRefundTest = new OrderState(7, 3, State.REFUND, LocalDate.now());
        newOrderStateRefund = new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(7, 3, State.REFUND, LocalDate.now());
    }

    @Test
    public void changeState_should_do_nothing_if_already_same_state() {
        OrderState result = mockableOrderStateDao.changeOrderState(orderStateAlreadyKept.getOrderId(), State.KEEP);
        assertEquals(orderStateAlreadyKept, result);
    }

    @Test
    public void changeState_should_change_state_if_different_state() {
        Mockito.when(mockOrderStateRepository.save(Mockito.any(fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState.class))).thenReturn(newOrderStateRefund);
        OrderState result = mockableOrderStateDao.changeOrderState(orderStateKeptForRefundTest.getOrderId(), State.REFUND);
        assertEquals(orderStateRefundForRefundTest, result);
    }

    @Test
    public void getCurrentStateOrders_should_call_repository_to_get_all_ordersStates() {
        mockableOrderStateDao.getCurrentStateOrders();
        Mockito.verify(mockOrderStateRepository, Mockito.times(1)).getAll();
    }

    @Test
    public void getCurrentStateOrders_should_return_list_of_OrderState_core_model() {
        List<fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState> orderStateList = new ArrayList<>();
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(1, 1, 1, LocalDate.now()));
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(2, 2, 2, LocalDate.now()));
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(3, 3, 1, LocalDate.now()));
        List<fr.esgi.ticketapi.core.entity.OrderState> expectedList = new ArrayList<>();
        expectedList.add(new fr.esgi.ticketapi.core.entity.OrderState(1, 1, 1, LocalDate.now()));
        expectedList.add(new fr.esgi.ticketapi.core.entity.OrderState(2, 2, 2, LocalDate.now()));
        expectedList.add(new fr.esgi.ticketapi.core.entity.OrderState(3, 3, 1, LocalDate.now()));
        Mockito.when(mockOrderStateRepository.getAll()).thenReturn(orderStateList);

        var result = mockableOrderStateDao.getCurrentStateOrders();

        assertEquals(expectedList, result);
    }

    @Test
    public void getCurrentStateOrders_when_list_ordersState_contain_only_same_order_should_return_recent_date_one() {
        List<fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState> orderStateList = new ArrayList<>();
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(1, 1, 1, LocalDate.now().minusDays(5)));
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(2, 1, 2, LocalDate.now().minusDays(1)));
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(3, 1, 1, LocalDate.now()));
        List<fr.esgi.ticketapi.core.entity.OrderState> expectedList = new ArrayList<>();
        expectedList.add(new fr.esgi.ticketapi.core.entity.OrderState(3, 1, 1, LocalDate.now()));
        Mockito.when(mockOrderStateRepository.getAll()).thenReturn(orderStateList);

        var result = mockableOrderStateDao.getCurrentStateOrders();

        assertEquals(result.size(), 1);
        assertEquals(expectedList.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void getCurrentStateOrders_when_list_ordersState_contain_few_same_orders_return_recent_for_each() {
        List<fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState> orderStateList = new ArrayList<>();
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(1, 1, 1, LocalDate.now().minusDays(5)));
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(2, 1, 2, LocalDate.now().minusDays(1)));
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(3, 1, 1, LocalDate.now()));
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(4, 2, 1, LocalDate.now().minusDays(5)));
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(5, 3, 2, LocalDate.now().minusDays(1)));
        orderStateList.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(6, 3, 2, LocalDate.now()));
        List<fr.esgi.ticketapi.core.entity.OrderState> expectedList = new ArrayList<>();
        expectedList.add(new fr.esgi.ticketapi.core.entity.OrderState(3, 1, 1, LocalDate.now()));
        expectedList.add(new fr.esgi.ticketapi.core.entity.OrderState(4, 2, 1, LocalDate.now().minusDays(1)));
        expectedList.add(new fr.esgi.ticketapi.core.entity.OrderState(6, 3, 2, LocalDate.now()));
        Mockito.when(mockOrderStateRepository.getAll()).thenReturn(orderStateList);

        var result = mockableOrderStateDao.getCurrentStateOrders();

        assertEquals(result.size(), 3);
        assertEquals(expectedList.get(0).getId(), result.get(0).getId());
        assertEquals(expectedList.get(1).getId(), result.get(1).getId());
        assertEquals(expectedList.get(2).getId(), result.get(2).getId());
    }
}
