package com.ernie.app.ticket.service.impl;

import com.ernie.app.ticket.model.Ticket;
import com.ernie.app.ticket.model.User;
import com.ernie.app.ticket.repository.TicketRepository;
import com.ernie.app.ticket.repository.UserRepository;
import com.ernie.app.ticket.service.TextToStringLoader;
import com.ernie.app.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    @Value("${user_update_and_ticket_creation.enabled}")
    public boolean isUserUpdateAndTicketCreationEnabled;

    private final TextToStringLoader loader;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Override
    public Ticket saveTicket(Ticket ticket) {
        if (ticket == null) {
            throw new NullPointerException("Ticket is null");
        }
        if (isUserUpdateAndTicketCreationEnabled) {
            return ticketRepository.save(ticket);
        } else throw new IllegalArgumentException("Operation is disabled.");
    }

    @Override
    public Ticket getTicketById(UUID id) {
        if (id == null) {
            throw new NullPointerException("Ticket ID is null");
        }
        return ticketRepository.getReferenceById(id);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void updateTicket(Ticket ticket) {
        if (ticket == null) {
            throw new NullPointerException("Ticket is null");
        }
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        if (ticket == null) {
            throw new NullPointerException("Ticket is null");
        }
        ticketRepository.delete(ticket);
    }

    @Override
    public void deleteTicketById(UUID id) {
        if (id == null) {
            throw new NullPointerException("Ticket ID is null");
        }
        ticketRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void setTicketToUser(Ticket ticket, User user) {
        ticket.setUser(user);
        user.getTickets().add(ticket);
        ticketRepository.save(ticket);
        userRepository.save(user);
    }

    @Override
    public Ticket getTicketByUser(User user) {
        if (user == null) {
            throw new NullPointerException("User is null");
        }
        return ticketRepository.findByUser(user);
    }
}