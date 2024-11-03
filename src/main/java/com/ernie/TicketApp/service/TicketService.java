package com.ernie.TicketApp.service;

import com.ernie.TicketApp.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TicketService extends Entity {

    @Value("${user_ticket_access.enabled}")
    public boolean isEnabled;

    private final TextToStringLoader loader;

    @Autowired
    public TicketService(TextToStringLoader loader) {
        this.loader = loader;
    }

}
