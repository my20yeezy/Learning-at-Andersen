package com.ernie.TicketApp.controller;

import com.ernie.TicketApp.config.ThisIsMyFirstConditionalBean;
import com.ernie.TicketApp.model.Ticket;
import com.ernie.TicketApp.service.TicketService;
import com.ernie.TicketApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;

    @Autowired(required = false)
    private ThisIsMyFirstConditionalBean thisIsMyFirstConditionalBean;

    public TicketController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{ticketId}")
    public Ticket getTicketById(@PathVariable("ticketId") UUID ticketId) {
        return ticketService.getTicketById(ticketId);
    }

    @PostMapping
    public ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket) {
        Ticket savedTicket = ticketService.saveTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
    }

    @GetMapping("/test")
    public String testCustomConfig() {
        if (thisIsMyFirstConditionalBean != null) {
            return "Bean is created!";
        } else {
            return "Bean is NOT created!";
        }
    }

}
