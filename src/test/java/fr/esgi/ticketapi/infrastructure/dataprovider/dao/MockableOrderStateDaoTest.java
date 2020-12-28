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

    OrderState orderStateKept;
    OrderState orderStateAlreadyKept;
    fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState newOrderStateKept;
    OrderState orderStateRefund;

    @Mock
    OrderStateRepository mockOrderStateRepository;

    @BeforeEach
    public void setup() {
        mockableOrderStateDao = new MockableOrderStateDao(mockOrderStateRepository);
        orderStateRefund = new OrderState(1, 1, State.REFUND, LocalDate.now().minusDays(5));
        orderStateKept = new OrderState(2, 1, State.KEEP, LocalDate.now());
        newOrderStateKept = new fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState(2, 1, State.KEEP, LocalDate.now());
        orderStateAlreadyKept = new OrderState(3, 2, State.KEEP, LocalDate.now().minusDays(5));
    }

    @Test
    public void changeStateToKeep_should_do_nothing_if_already_kept() {
        OrderState result = mockableOrderStateDao.changeOrderStateToKeep(orderStateAlreadyKept.getOrderId());
        assertEquals(orderStateAlreadyKept, result);
    }

    @Test
    public void changeStateToKeep_should_change_state_if_refund() {
        Mockito.when(mockOrderStateRepository.save(Mockito.any(fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState.class))).thenReturn(newOrderStateKept);
        OrderState result = mockableOrderStateDao.changeOrderStateToKeep(orderStateRefund.getOrderId());
        assertEquals(orderStateKept, result);
    }

}
