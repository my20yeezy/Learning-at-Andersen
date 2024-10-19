package com.ernie.TicketApp.service;

import com.ernie.HW6CustomStorages.MyArrayList;
import com.ernie.HW6CustomStorages.MyHashSet;
import com.ernie.TicketApp.constraint.NullCheckUtility;
import com.ernie.TicketApp.model.Admin;
import com.ernie.TicketApp.model.Client;
import com.ernie.TicketApp.model.Entity;
import com.ernie.TicketApp.model.Ticket;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

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

        Ticket ticketLimited = new Ticket(
                "SecondHall",
                888,
                dateTime
        );

        Ticket ticketEmpty = new Ticket();

        System.out.println(ticketFull);
        System.out.println(ticketLimited);
        System.out.println(ticketEmpty);

        Client client = new Client();
        client.printRole();
        client.print();
        client.getTicket(ticketLimited);

        Admin admin = new Admin();
        admin.checkTicket(ticketFull);

        ticketEmpty.setId(null);
        System.out.println(ticketEmpty.getId());
        NullCheckUtility.validateNotNull(ticketEmpty);

        ticketFull.shared("+7707777232424");
        ticketFull.shared("+7707777232424", "some_email@mail.com");

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

        System.out.println(myHashSet.size());
    }
}
