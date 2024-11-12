package com.ernie.app.ticket;


public class TicketServiceTest {
    //Temporary moving old main method here, until HW13 is done
    //TODO proper tests with JUnit and Mockito
    public static void main(String[] args) {

//        System.out.println("============================= HW 2 (Classes, Variables) ====================================");
//
//        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 5, 20, 0, 0);
//
//        Ticket ticketFull = new Ticket(
//                dateTime,
//                true
//        );
//
//        System.out.println("\n============================= HW 4 (OOP) ====================================");
//        User client1 = new User("Yerniyaz");
//        client1.printRole();
//
//        User admin = new User("Admin");
//
//        ticketFull.shared("+7707777232424");
//        ticketFull.shared("+7707777232424", "some_email@mail.com");
//
//        System.out.println("\n============================= HW 6 (Data Structures) ====================================");
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
//
//        myArrayList.printAll();
//        System.out.println(myArrayList.size() + " " + myArrayList.capacity() + "\n");
//        System.out.println(myArrayList.getByIndex(4) + "\n");
//
//        myArrayList.deleteByIndex(4);
//        myArrayList.printAll();
//        System.out.println(myArrayList.size() + " " + myArrayList.capacity() + "\n");
//
//        myArrayList.print();
//
//        System.out.println("Testing custom HashSet");
//        MyHashSet myHashSet = new MyHashSet();
//        myHashSet.put("Element 1");
//        myHashSet.put("Element 2");
//        myHashSet.put("Element 3");
//        myHashSet.put("Element 3");
//        myHashSet.put("Element 3");
//
//        myHashSet.print();
//
//        System.out.println(myHashSet.contains("Element 4"));
//        System.out.println(myHashSet.contains("Element 2"));
//
//        myHashSet.delete("Element 1");
//
//        myHashSet.print();
//
//        ticketFull.setTicketType(TicketType.DAY);
//
//        System.out.println("\n============================= HW 8 (JDBC) ====================================");
//        System.out.println("\n============================= HW 10 (Spring Core) ====================================");
//        System.out.println("Updated using Spring Core DI and IoC. \n");
//
//        org.springframework.context.ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class);
//
//        UserDAO userDAO = context.getBean(UserDAO.class);
//        TicketDAO ticketDAO = context.getBean(TicketDAO.class);
//
//        System.out.println("Established DAO for Users and Tickets. \n");
//
//        userDAO.saveUser(client1);
//        userDAO.saveUser(admin);
//
//        List<User> allUsersFromDB = userDAO.getAllUsers();
//        System.out.println(allUsersFromDB);
//
//        List<Ticket> allTicketsFromDB = ticketDAO.getAllTickets();
//        System.out.println(allTicketsFromDB);
//
//        Ticket firstTicketFromDB = null;
//        if (allTicketsFromDB.size() > 0) {
//            firstTicketFromDB = allTicketsFromDB.get(0);
//        }
//        System.out.println("First Ticket from DB: " + firstTicketFromDB);
//
//        ticketDAO.updateTicketType(firstTicketFromDB, TicketType.WEEK);
//        Ticket updatedTicketFromDB = ticketDAO.getTicketById(firstTicketFromDB.getId());
//
//        userDAO.deleteUserByTicket(firstTicketFromDB);
//
//        userDAO.saveUser(admin);
//
//        Ticket ticket = new Ticket();
//        ticket.setTicketType(TicketType.WEEK);
//        ticketDAO.saveTicket(ticket, admin);
//
//        System.out.println("\n============================= HW 11 (Spring Core 2) ====================================");
//        System.out.println("Testing @Transactional. \n");
//
//        TicketService ticketService = context.getBean(TicketServiceImpl.class);
//
//        User user2 = new User("Ernie");
//        userDAO.saveUser(user2);
//
//        ticketService.updateUserAndAddTicket(allUsersFromDB.get(0).getId());
//
//        System.out.println("\nTesting Resource injection from a file in resources directory. \n");
//
//        TextToStringLoader loader = context.getBean(TextToStringLoader.class);
//        List<String> tickets;
//        try {
//            tickets = loader.loadTextFileAsStrings();
//            for (String s : tickets) {
//                System.out.println(s);
//            }
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
    }
}
