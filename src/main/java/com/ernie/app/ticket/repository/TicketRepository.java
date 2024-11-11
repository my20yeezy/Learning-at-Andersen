package com.ernie.app.ticket.repository;

import com.ernie.app.ticket.model.Ticket;
import com.ernie.app.ticket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    Ticket findByUser(User user);

    @Query(value = "SELECT * FROM ticket_info t WHERE t.user.id = :userId", nativeQuery = true)
    List<Ticket> findTicketsByUserId(@Param("userId") UUID userId);

}
