package com.ernie.TicketApp;

public class Admin extends User {

    public void checkTicket(Ticket ticket) {
        System.out.println("Admin checked ticket " + ticket.getId());
    }
}
