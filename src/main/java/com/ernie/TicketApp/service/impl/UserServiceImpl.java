package com.ernie.TicketApp.service.impl;

import com.ernie.TicketApp.model.Ticket;
import com.ernie.TicketApp.model.TicketType;
import com.ernie.TicketApp.model.User;
import com.ernie.TicketApp.repository.TicketRepository;
import com.ernie.TicketApp.repository.UserRepository;
import com.ernie.TicketApp.service.TicketService;
import com.ernie.TicketApp.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Value("${user_update_and_ticket_creation.enabled}")
    public boolean isUserUpdateAndTicketCreationEnabled;

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final TicketService ticketService;

    public UserServiceImpl(UserRepository userRepository, TicketRepository ticketRepository, TicketService ticketService) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
    }

    @Override
    public User saveUser(User user) {
        System.out.println("User " + user + " saved to DB.");
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID id) {
        System.out.println("Got User " + userRepository.getReferenceById(id) + " from DB.");
        return userRepository.getReferenceById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        if (isUserUpdateAndTicketCreationEnabled) {
            userRepository.save(user);
        } else throw new IllegalArgumentException("Operation is disabled.");
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserAndAddTicket(User user) {
        if (isUserUpdateAndTicketCreationEnabled) {
            User userFromDB = userRepository.getReferenceById(user.getId());
            Ticket newTicket = new Ticket();
            ticketService.setTicketToUser(newTicket, userFromDB);
            System.out.println("User " + user + " was updated and got a new ticket.");
        } else {
            throw new IllegalArgumentException("Operation is disabled");
        }
    }
}