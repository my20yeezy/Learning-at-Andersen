package com.ernie.TicketApp;

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
    }
}
