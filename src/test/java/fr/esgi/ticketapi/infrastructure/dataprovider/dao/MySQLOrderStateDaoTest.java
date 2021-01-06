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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MySQLOrderStateDaoTest {

    MySQLOrderStateDao mySQLOrderStateDao;

    OrderState orderStateAlreadyKept;
    fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState newOrderStateRefund;
    OrderState orderStateKeptForRefundTest;
    OrderState orderStateRefundForRefundTest;

    @Mock
    OrderStateRepository mockOrderStateRepository;

    @BeforeEach
    public void setup() {
        mySQLOrderStateDao = new MySQLOrderStateDao(mockOrderStateRepository);

        orderStateAlreadyKept = new OrderState(3, 2, State.KEEP, LocalDate.now().minusDays(5));
        orderStateKeptForRefundTest = new OrderState(6, 3, State.KEEP, LocalDate.now().minusDays(5));
        orderStateRefundForRefundTest = new OrderState(7, 3, State.REFUND, LocalDate.now());
        newOrderStateRefund = new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(7, 3, State.REFUND, LocalDate.now());
    }

    @Test
    public void changeState_should_do_nothing_if_already_same_state() {
        List<fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState> orderStates = new ArrayList<>();
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(1, 3, State.KEEP, LocalDate.now().minusDays(3)));
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(2, 1, State.REFUND, LocalDate.now().minusDays(3)));
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(3, 2, State.KEEP, LocalDate.now().minusDays(5)));

        Mockito.when(mockOrderStateRepository.getAll()).thenReturn(orderStates);

        OrderState result = mySQLOrderStateDao.changeOrderState(orderStateAlreadyKept.getOrderId(), State.KEEP);
        assertEquals(orderStateAlreadyKept, result);
    }

    @Test
    public void changeState_should_change_state_if_different_state() {
        Mockito.when(mockOrderStateRepository.save(Mockito.any(fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState.class))).thenReturn(newOrderStateRefund);
        OrderState result = mySQLOrderStateDao.changeOrderState(orderStateKeptForRefundTest.getOrderId(), State.REFUND);
        assertEquals(orderStateRefundForRefundTest, result);
    }

    @Test
    public void changeState_should_insert_state_if_not_registered_before() {
        List<fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState> orderStates = new ArrayList<>();
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(1, 3, State.KEEP, LocalDate.now().minusDays(3)));
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(2, 1, State.REFUND, LocalDate.now().minusDays(3)));
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(3, 2, State.REFUND, LocalDate.now().minusDays(3)));

        Mockito.when(mockOrderStateRepository.getAll()).thenReturn(orderStates);

        fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState newOrderState = new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(4, 4, State.REFUND, LocalDate.now());

        OrderState expected = new OrderState(4, 4, State.REFUND, LocalDate.now());

        Mockito.when(mockOrderStateRepository.save(Mockito.any(fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState.class))).thenReturn(newOrderState);
        OrderState result = mySQLOrderStateDao.changeOrderState(orderStateKeptForRefundTest.getOrderId(), State.REFUND);
        assertEquals(expected, result);
    }

    @Test
    public void getCurrentStateOrderById_should_call_repo_get_all_once() {
        mySQLOrderStateDao.getCurrentStateOfOrderById(1);
        Mockito.verify(mockOrderStateRepository, Mockito.times(1)).getAll();
    }

    @Test
    public void getCurrentStateOrderById_should_return_one_if_exists_in_list() {
        List<fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState> orderStates = new ArrayList<>();
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(1, 3, State.KEEP, LocalDate.now().minusDays(3)));
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(2, 1, State.REFUND, LocalDate.now().minusDays(3)));
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(3, 2, State.REFUND, LocalDate.now().minusDays(3)));

        Mockito.when(mockOrderStateRepository.getAll()).thenReturn(orderStates);

        OrderState expected = new OrderState(3, 2, State.REFUND, LocalDate.now().minusDays(3));

        assertEquals(expected, mySQLOrderStateDao.getCurrentStateOfOrderById(expected.getOrderId()));
    }

    @Test
    public void getCurrentStateOrderById_should_return_null_if_not_exists_in_list() {
        List<fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState> orderStates = new ArrayList<>();
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(1, 3, State.KEEP, LocalDate.now().minusDays(3)));
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(2, 1, State.REFUND, LocalDate.now().minusDays(3)));
        orderStates.add(new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(3, 2, State.REFUND, LocalDate.now().minusDays(3)));

        Mockito.when(mockOrderStateRepository.getAll()).thenReturn(orderStates);

        assertNull(mySQLOrderStateDao.getCurrentStateOfOrderById(4));
    }

    @Test
    public void getCurrentStateOrders_should_call_repository_to_get_all_ordersStates() {
        mySQLOrderStateDao.getCurrentStateOrders();
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

        var result = mySQLOrderStateDao.getCurrentStateOrders();

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

        var result = mySQLOrderStateDao.getCurrentStateOrders();

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

        var result = mySQLOrderStateDao.getCurrentStateOrders();

        assertEquals(result.size(), 3);
        assertEquals(expectedList.get(0).getId(), result.get(0).getId());
        assertEquals(expectedList.get(1).getId(), result.get(1).getId());
        assertEquals(expectedList.get(2).getId(), result.get(2).getId());
    }
}
