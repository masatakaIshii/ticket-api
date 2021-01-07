package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import fr.esgi.ticketapi.core.entity.OrderState;
import fr.esgi.ticketapi.core.entity.State;
import fr.esgi.ticketapi.usecase.emails.SendEmail;
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
class AddOrderStatesTest {

    AddOrderStates addOrderStates;

    @Mock
    OrderStateDao mockOrderStateDao;

    @Mock
    SendEmail mockSendEmail;

    @BeforeEach
    public void setup() {
        addOrderStates = new AddOrderStates(mockOrderStateDao, mockSendEmail);
    }

    @Test
    void should_call_order_state_dao_once() {
        addOrderStates.execute(new ArrayList<>());
        Mockito.verify(mockOrderStateDao, Mockito.atLeastOnce()).addOrderStates(Mockito.anyList());
    }

    @Test
    void should_call_send_email_after_added() {
        addOrderStates.execute(new ArrayList<>());
        Mockito.verify(mockSendEmail, Mockito.atLeastOnce()).execute(Mockito.anyString());
    }

    @Test
    void should_returns_what_dao_returns() {
        List<OrderState> orderStatesExpected = new ArrayList<>();
        orderStatesExpected.add(new OrderState(1, 1, State.KEEP, LocalDate.now()));
        orderStatesExpected.add(new OrderState(2, 2, State.REFUND, LocalDate.now()));
        Mockito.when(mockOrderStateDao.addOrderStates(orderStatesExpected)).thenReturn("j");
        String result = addOrderStates.execute(orderStatesExpected);
        assertEquals("j", result);
    }

}
