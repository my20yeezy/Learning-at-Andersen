package com.ernie.TicketApp.service;

import com.ernie.HW6CustomStorages.MyArrayList;
import com.ernie.HW6CustomStorages.MyHashSet;
import com.ernie.TicketApp.constraint.NullCheckUtility;
import com.ernie.TicketApp.model.*;
import com.ernie.TicketApp.repository.TicketDAO;
import com.ernie.TicketApp.repository.UserDAO;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class TicketService extends Entity {
    public static void main(String[] args) {

        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 5, 20, 0, 0);
        Long timeInUnixTimeStamp = dateTime.toEpochSecond(ZoneOffset.UTC);

        Ticket ticketFull = new Ticket(
                "MainHall",
                777,
                dateTime,
                true,
                'A',
                1
        );

        ticketFull.setTicketType(TicketType.DAY);

        Client client1 = new Client("Yerniyaz");
//        client1.printRole();
//        client1.print();
//        client1.getTicket(ticketLimited);

        UserDAO userDAO = new UserDAO();
//        userDAO.saveUser(client1);

        Admin admin = new Admin("Admin");
//        admin.checkTicket(ticketFull);

//        userDAO.deleteUserById(UUID.fromString("d967d60c-167b-43b0-ba13-d08f0c59a721"));
//        userDAO.saveUser(admin);

        User userFromDB = userDAO.fetchById(UUID.fromString("df868a97-df51-4639-a57f-4f84ed5086e3"));
        System.out.println(userFromDB);


        TicketDAO ticketDAO = new TicketDAO();
//        ticketDAO.saveTicket(ticketFull, userFromDB);

        Ticket ticketFromDB = ticketDAO.fetchById(UUID.fromString("34227935-f280-41e9-bba9-bbcdc69f9dba"));
        ticketFromDB.print();
        ticketDAO.updateTicketType(ticketFromDB, TicketType.WEEK);
        Ticket updatedTicketFromDB = ticketDAO.fetchById(ticketFromDB.getId());
        updatedTicketFromDB.print();

        Ticket ticket1 = new Ticket();
        ticket1.setId(UUID.fromString("34227935-f280-41e9-bba9-bbcdc69f9dba"));
        userDAO.deleteUserByTicket(ticket1);

//        ticketEmpty.setId(null);
//        System.out.println(ticketEmpty.getId());
//        NullCheckUtility.validateNotNull(ticketEmpty);
//
//        ticketFull.shared("+7707777232424");
//        ticketFull.shared("+7707777232424", "some_email@mail.com");

//        System.out.println("Testing custom ArrayList");
//        MyArrayList myArrayList = new MyArrayList();
//        myArrayList.put("Element 1");
//        myArrayList.put("Element 2");
//        myArrayList.put("Element 3");
//        myArrayList.put("Element 4");
//        myArrayList.put("Element 5");
//        myArrayList.put("Element 6");
//        myArrayList.put("Element 7");
//        myArrayList.put("Element 8");
//        myArrayList.put("Element 9");
//        myArrayList.put("Element 10");
//        myArrayList.put("Element 11");
//        myArrayList.printAll();
//        System.out.println(myArrayList.size() + " " + myArrayList.capacity() + "\n");
//        System.out.println(myArrayList.getByIndex(4) + "\n");
//        myArrayList.deleteByIndex(4);
//        myArrayList.printAll();
//        System.out.println(myArrayList.size() + " " + myArrayList.capacity() + "\n");
//        myArrayList.print();
//
//        System.out.println("Testing custom HashSet");
//        MyHashSet myHashSet = new MyHashSet();
//        myHashSet.put("Element 1");
//        myHashSet.put("Element 2");
//        myHashSet.put("Element 3");
//        myHashSet.put("Element 3");
//        myHashSet.put("Element 3");
//        myHashSet.print();
//        System.out.println(myHashSet.contains("Element 4"));
//        System.out.println(myHashSet.contains("Element 2"));
//        myHashSet.delete("Element 1");
//        myHashSet.print();
//        System.out.println(myHashSet.size());

    }
}
