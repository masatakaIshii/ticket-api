package fr.esgi.ticketapi.usecase.orderState;

import fr.esgi.ticketapi.core.dao.OrderStateDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeleteOrdersStatesTest {

    DeleteOrdersStates deleteOrdersStates;


    @Mock
    OrderStateDao mockOrderStateDao;

    @BeforeEach
    public void setUp() {
        deleteOrdersStates = new DeleteOrdersStates(mockOrderStateDao);
    }

    @Test
    void should_call_order_state_dao_once() {
        deleteOrdersStates.execute();
        Mockito.verify(mockOrderStateDao, Mockito.atLeastOnce()).deleteOrdersStates();
    }


}
