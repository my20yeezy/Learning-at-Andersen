package com.ernie.app.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ticket_info")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "is_promo")
    private boolean isPromo;

    @Column(name = "price")
    private double price;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type")
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public Ticket(LocalDateTime time, boolean isPromo, TicketType ticketType) {
        this.time = time;
        this.isPromo = isPromo;
        if (isPromo) {
            price = 10;
        } else {
            price = 20;
        }
        this.ticketType = ticketType;
        creationDateTime = LocalDateTime.now();
    }

    public Ticket() {
        creationDateTime = LocalDateTime.now();
    }

    public void shared(String phone) {
        System.out.println("Ticket " + id + " was shared to phone number " + phone);
    }

    public void shared(String phone, String email) {
        System.out.println("Ticket " + id + " was shared to phone number " + phone + " and to email " + email);
    }


    public LocalDateTime getTime() {
        return time;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPromo(boolean promo) {
        isPromo = promo;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return isPromo == ticket.isPromo && Double.compare(price, ticket.price) == 0 && Objects.equals(id, ticket.id) && Objects.equals(time, ticket.time) && Objects.equals(creationDateTime, ticket.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, isPromo, price, creationDateTime);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                ", time=" + time +
                ", isPromo=" + isPromo +
                ", price=" + price +
                ", creationDateTime=" + creationDateTime +
                ", ticketType=" + ticketType +
                ", id=" + id +
                '}';
    }
}
