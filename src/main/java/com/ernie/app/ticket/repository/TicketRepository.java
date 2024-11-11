package com.ernie.app.ticket.repository;

import com.ernie.app.ticket.model.Ticket;
import com.ernie.app.ticket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    Ticket findByUser(User user);

}
