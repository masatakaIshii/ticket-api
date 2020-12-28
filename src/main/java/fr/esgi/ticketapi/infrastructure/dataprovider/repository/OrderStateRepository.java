package fr.esgi.ticketapi.infrastructure.dataprovider.repository;

import fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OrderStateRepository extends JpaRepository<OrderState, Long> {

    @Query(value = "select o from OrderState o where o.orderId = :orderId order by o.date")
    List<OrderState> getAllByOrderId(Integer orderId);

    @Query(value = "select o from OrderState o order by o.orderId,o.date")
    List<OrderState> getAll();
}
