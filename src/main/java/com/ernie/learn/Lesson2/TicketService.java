package com.ernie.learn.Lesson2;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

public class TicketService {
    public static void main(String[] args) {

        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 5, 20, 0, 0);
        Long timeInUnixTimeStamp = dateTime.toEpochSecond(ZoneOffset.UTC);

        Ticket ticketFull = new Ticket(
                "111",
                "MainHall",
                777,
                timeInUnixTimeStamp,
                true,
                'A',
                1
        );

        Ticket ticketLimited = new Ticket(
                "SecondHall",
                888,
                timeInUnixTimeStamp
        );

        Ticket ticketEmpty = new Ticket();

        System.out.println(ticketFull);
        System.out.println(ticketLimited);
        System.out.println(ticketEmpty);
    }
}
