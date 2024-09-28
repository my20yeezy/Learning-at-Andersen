package com.ernie.learn.Lesson2;

import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private String concertHall;
    private int eventCode;
    private long timeUnixTimeStamp;
    private boolean isPromo;
    private char stadiumSector;
    private double maxBackpackWeightInKg;
    private double price;
    private LocalDateTime creationDateTime;

    public Ticket(String id, String concertHall, int eventCode, long timeUnixTimeStamp, boolean isPromo, char stadiumSector, double maxBackpackWeightInKg) {
        if (id.length() <= 4) {
            this.id = id;
        }
        if (concertHall.length() <= 10) {
            this.concertHall = concertHall;
        }
        if (eventCode / 1000 < 1) {
            this.eventCode = eventCode;
        }
        this.timeUnixTimeStamp = timeUnixTimeStamp;
        this.isPromo = isPromo;
        if (stadiumSector == 'A' || stadiumSector == 'B' || stadiumSector == 'C') {
            this.stadiumSector = stadiumSector;
        }
        this.maxBackpackWeightInKg = maxBackpackWeightInKg;
        if (isPromo) {
            price = 10;
        } else {
            price = 20;
        }
        creationDateTime = LocalDateTime.now();
    }

    public Ticket() {
        creationDateTime = LocalDateTime.now();
    }

    public Ticket(String concertHall, int eventCode, long timeUnixTimeStamp) {
        if (concertHall.length() <= 10) {
            this.concertHall = concertHall;
        }
        if (eventCode / 1000 < 1) {
            this.eventCode = eventCode;
        }
        this.timeUnixTimeStamp = timeUnixTimeStamp;
        price = 20;
        creationDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", timeUnixTimeStamp=" + timeUnixTimeStamp +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeightInKg=" + maxBackpackWeightInKg +
                ", price=" + price +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
