package fr.esgi.ticketapi.infrastructure.dataprovider.repository;

import fr.esgi.ticketapi.infrastructure.dataprovider.model.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStateRepository extends JpaRepository<OrderState, Long> {
}
