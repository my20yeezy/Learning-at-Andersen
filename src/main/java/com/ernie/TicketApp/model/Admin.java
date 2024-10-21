package com.ernie.TicketApp.model;

public class Admin extends User {

    public Admin(String name) {
        super(name);
    }

    public void checkTicket(Ticket ticket) {
        System.out.println("Admin checked ticket " + ticket.getId());
    }
}
