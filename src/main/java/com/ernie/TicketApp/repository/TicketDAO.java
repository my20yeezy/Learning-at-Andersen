package com.ernie.TicketApp.repository;

import com.ernie.TicketApp.config.SessionFactoryProvider;
import com.ernie.TicketApp.model.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public void saveTicket(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();
        System.out.println("Ticket with ID " + ticket.getId() + " was added to DB.");
    }

    public Ticket getTicketById(long ticketId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, ticketId);
        transaction.commit();
        session.close();
        System.out.println("Ticket with ID " + ticket + " was fetched from DB.");
        return ticket;
    }

    public void updateTicket(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(ticket);
        transaction.commit();
        session.close();
        System.out.println("Ticket with ID " + ticket.getId() + " was updated in DB.");
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> allTickets = new ArrayList<>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Ticket> query = session.createQuery("FROM Ticket", Ticket.class);
        allTickets = query.getResultList();
        transaction.commit();
        session.close();
        System.out.println("Got " + allTickets.size() + " Tickets from DB.");
        return allTickets;
    }

}