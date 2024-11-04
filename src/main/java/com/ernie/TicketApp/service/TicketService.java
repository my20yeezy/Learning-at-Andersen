package com.ernie.TicketApp.service;

import com.ernie.TicketApp.model.Entity;
import com.ernie.TicketApp.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService extends Entity {

    @Value("${user_ticket_access.enabled}")
    public boolean isUserTicketAccessEnabled;

    private final TextToStringLoader loader;
    private final UserDAO userDAO;

    @Autowired
    public TicketService(UserDAO userDAO, TextToStringLoader loader) {
        this.loader = loader;
        this.userDAO = userDAO;
    }

    public void updateUserAddTicket(UUID id) {
        if (isUserTicketAccessEnabled) {
            userDAO.updateUserAddTicket(id);
        } else {
            throw new IllegalArgumentException("Operation is disabled");
        }
    }

}
