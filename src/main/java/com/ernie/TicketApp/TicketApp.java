package com.ernie.TicketApp;

import com.ernie.HW6CustomStorages.MyArrayList;
import com.ernie.HW6CustomStorages.MyHashSet;
import com.ernie.TicketApp.model.*;
import com.ernie.TicketApp.repository.TicketDAO;
import com.ernie.TicketApp.repository.UserDAO;
import com.ernie.TicketApp.service.TextToStringLoader;
import com.ernie.TicketApp.service.TicketService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class TicketApp {
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
        System.out.println("\n============================= HW 10 (Spring Core) ====================================");
        System.out.println("Updated using Spring Core DI and IoC. \n");

        org.springframework.context.ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class);

        UserDAO userDAO = context.getBean(UserDAO.class);
        TicketDAO ticketDAO = context.getBean(TicketDAO.class);

        System.out.println("Established DAO for Users and Tickets. \n");

        userDAO.saveUser(client1);
        userDAO.saveUser(admin);

        List<User> allUsersFromDB = userDAO.getAllUsers();
        System.out.println(allUsersFromDB);

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

        userDAO.saveUser(admin);

        Ticket ticket = new Ticket();
        ticket.setTicketType(TicketType.WEEK);
        ticketDAO.saveTicket(ticket, admin);

        System.out.println("\n============================= HW 11 (Spring Core 2) ====================================");
        System.out.println("Testing @Transactional. \n");

        TicketService service = context.getBean(TicketService.class);

        User user2 = new User("Ernie");
        userDAO.saveUser(user2);
        if (!service.isEnabled) {
            userDAO.updateUserAddTicket(allUsersFromDB.get(1).getId());
        } else {
            throw new IllegalArgumentException("Operation disabled");
        }

        System.out.println("\nTesting Resource injection from a file in resources directory. \n");

        TextToStringLoader loader = context.getBean(TextToStringLoader.class);
        List<String> tickets;
        try {
            tickets = loader.loadTextFileAsStrings();
            for (String s: tickets) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
