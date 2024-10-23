package com.ernie.TicketApp.model;

public class Client extends User {

    public Client(String name) {
        super(name);
    }

    public void getTicket(Ticket ticket) {
        System.out.println("Client got ticket " + ticket.getId());
    }
}
