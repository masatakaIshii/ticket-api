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

}
