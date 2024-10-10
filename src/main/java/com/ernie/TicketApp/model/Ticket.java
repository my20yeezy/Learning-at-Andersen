package com.ernie.TicketApp.model;

import com.ernie.TicketApp.constraint.NullableWarning;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Ticket extends Entity {

    private String concertHall;
    private int eventCode;
    private LocalDateTime time;
    private boolean isPromo;
    private char stadiumSector;
    private double maxBackpackWeightInKg;
    private double price;
    private LocalDateTime creationDateTime;

    public Ticket(String concertHall, int eventCode, LocalDateTime time, boolean isPromo, char stadiumSector, double maxBackpackWeightInKg) {

        if (concertHall.length() <= 10) {
            this.concertHall = concertHall;
        }
        if (eventCode / 1000 < 1) {
            this.eventCode = eventCode;
        }
        this.time = time;
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

    public Ticket(String concertHall, int eventCode, LocalDateTime time) {
        if (concertHall.length() <= 10) {
            this.concertHall = concertHall;
        }
        if (eventCode / 1000 < 1) {
            this.eventCode = eventCode;
        }
        this.time = time;
        price = 20;
        creationDateTime = LocalDateTime.now();
    }

    public void shared(String phone) {
        System.out.println("Ticket " + id + " was shared to phone number " + phone);
    }

    public void shared(String phone, String email) {
        System.out.println("Ticket " + id + " was shared to phone number " + phone + " and to email " + email);
    }



    public String getConcertHall() {
        return concertHall;
    }

    public int getEventCode() {
        return eventCode;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public char getStadiumSector() {
        return stadiumSector;
    }

    public double getMaxBackpackWeightInKg() {
        return maxBackpackWeightInKg;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setStadiumSector(char stadiumSector) {
        this.stadiumSector = stadiumSector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return eventCode == ticket.eventCode && isPromo == ticket.isPromo && stadiumSector == ticket.stadiumSector && Double.compare(maxBackpackWeightInKg, ticket.maxBackpackWeightInKg) == 0 && Double.compare(price, ticket.price) == 0 && Objects.equals(id, ticket.id) && Objects.equals(concertHall, ticket.concertHall) && Objects.equals(time, ticket.time) && Objects.equals(creationDateTime, ticket.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, concertHall, eventCode, time, isPromo, stadiumSector, maxBackpackWeightInKg, price, creationDateTime);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", timeUnixTimeStamp=" + time +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeightInKg=" + maxBackpackWeightInKg +
                ", price=" + price +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
