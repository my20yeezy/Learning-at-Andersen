package com.ernie.TicketApp.service;

import com.ernie.HW6CustomStorages.MyArrayList;
import com.ernie.HW6CustomStorages.MyHashSet;
import com.ernie.TicketApp.model.*;
import com.ernie.TicketApp.repository.TicketDAO;
import com.ernie.TicketApp.repository.UserDAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TicketService extends Entity {
    public static void main(String[] args) {

        System.out.println("============================= HW 2 (Classes, Variables) ====================================");

        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 5, 20, 0, 0);

        Ticket ticketFull = new Ticket(
                "MainHall",
                777,
                dateTime,
                true,
                'A',
                1
        );
        ticketFull.print();

        System.out.println("\n============================= HW 4 (OOP) ====================================");
        Client client1 = new Client("Yerniyaz");
        client1.printRole();
        client1.print();
        client1.getTicket(ticketFull);

        Admin admin = new Admin("Admin");
        admin.checkTicket(ticketFull);

        ticketFull.shared("+7707777232424");
        ticketFull.shared("+7707777232424", "some_email@mail.com");

        System.out.println("\n============================= HW 6 (Data Structures) ====================================");
        System.out.println("Testing custom ArrayList");
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.put("Element 1");
        myArrayList.put("Element 2");
        myArrayList.put("Element 3");
        myArrayList.put("Element 4");
        myArrayList.put("Element 5");
        myArrayList.put("Element 6");
        myArrayList.put("Element 7");
        myArrayList.put("Element 8");
        myArrayList.put("Element 9");
        myArrayList.put("Element 10");
        myArrayList.put("Element 11");

        myArrayList.printAll();
        System.out.println(myArrayList.size() + " " + myArrayList.capacity() + "\n");
        System.out.println(myArrayList.getByIndex(4) + "\n");

        myArrayList.deleteByIndex(4);
        myArrayList.printAll();
        System.out.println(myArrayList.size() + " " + myArrayList.capacity() + "\n");

        myArrayList.print();

        System.out.println("Testing custom HashSet");
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.put("Element 1");
        myHashSet.put("Element 2");
        myHashSet.put("Element 3");
        myHashSet.put("Element 3");
        myHashSet.put("Element 3");

        myHashSet.print();

        System.out.println(myHashSet.contains("Element 4"));
        System.out.println(myHashSet.contains("Element 2"));

        myHashSet.delete("Element 1");

        myHashSet.print();

        ticketFull.setTicketType(TicketType.DAY);

        System.out.println("\n============================= HW 8 (JDBC) ====================================");

        UserDAO userDAO = new UserDAO();
        TicketDAO ticketDAO = new TicketDAO();
        System.out.printf("Established DAO for Users and Tickets.");

        userDAO.saveUser(client1);
        userDAO.saveUser(admin);

        List<User> allUsersFromDB = userDAO.getAllUsers();
        System.out.println(allUsersFromDB);

        System.out.println("Deleting first user, if any:");
        if (allUsersFromDB.size() > 0) {
            userDAO.deleteUserById(allUsersFromDB.get(0).getId());
        }

        User secondUserFromDB = null;
        if (allUsersFromDB.size() > 1) {
            secondUserFromDB = allUsersFromDB.get(1);
        }
        System.out.println("Second User from DB: " + secondUserFromDB);

        ticketDAO.saveTicket(ticketFull, secondUserFromDB);

        List<Ticket> allTicketsFromDB = ticketDAO.getAllTickets();
        System.out.println(allTicketsFromDB);

        Ticket firstTicketFromDB = null;
        if (allTicketsFromDB.size() > 0) {
            firstTicketFromDB = allTicketsFromDB.get(0);
        }
        System.out.println("First Ticket from DB: " + firstTicketFromDB);

        ticketDAO.updateTicketType(firstTicketFromDB, TicketType.WEEK);
        Ticket updatedTicketFromDB = ticketDAO.getTicketById(firstTicketFromDB.getId());
        updatedTicketFromDB.print();

        userDAO.deleteUserByTicket(firstTicketFromDB);

    }
}
