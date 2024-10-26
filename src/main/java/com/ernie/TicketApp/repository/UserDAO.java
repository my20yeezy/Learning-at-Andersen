package com.ernie.TicketApp.repository;

import com.ernie.TicketApp.config.SessionFactoryProvider;
import com.ernie.TicketApp.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void saveUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
        System.out.println("User with ID " + user.getId() + " was added to DB.");
    }

    public User getUserById(long id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        transaction.commit();
        session.close();
        System.out.println("User with ID " + id + " was fetched from DB.");
        return user;
    }

    public void deleteUserById(long userId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, userId);
        if (user != null) {
            session.remove(user);
        }
        transaction.commit();
        session.close();
        System.out.println("User with ID " + userId + " was deleted from DB.");
    }

    public void deleteUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(user);
        transaction.commit();
        session.close();
        System.out.println("User with ID " + user.getId() + " was deleted from DB.");
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("FROM User", User.class);
        allUsers = query.getResultList();
        transaction.commit();
        session.close();
        System.out.println("Got " + allUsers.size() + " Users from DB.");
        return allUsers;
    }

}