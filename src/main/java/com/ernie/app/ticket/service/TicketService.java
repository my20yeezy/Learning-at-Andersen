package com.ernie.app.ticket.service;

import com.ernie.app.ticket.model.Ticket;
import com.ernie.app.ticket.model.User;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    Ticket saveTicket(Ticket ticket);

    Ticket getTicketById(UUID id);

    List<Ticket> getAllTickets();

    void updateTicket(Ticket ticket);

    void deleteTicket(Ticket ticket);

    void deleteTicketById(UUID id);

    void setTicketToUser(Ticket ticket, User user);

    Ticket getTicketByUser(User user);

}
