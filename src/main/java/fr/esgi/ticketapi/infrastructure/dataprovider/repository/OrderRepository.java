package fr.esgi.ticketapi.infrastructure.dataprovider.repository;

import fr.esgi.ticketapi.infrastructure.dataprovider.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
