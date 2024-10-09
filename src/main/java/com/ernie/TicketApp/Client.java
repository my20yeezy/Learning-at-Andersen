package com.ernie.TicketApp;

public class Client extends User {

    public void getTicket(Ticket ticket) {
        System.out.println("Client got ticket " + ticket.getId());
    }
}
