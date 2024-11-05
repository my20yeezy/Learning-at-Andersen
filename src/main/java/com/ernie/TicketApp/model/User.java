package com.ernie.TicketApp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User extends Entity {

    private String name;
    private LocalDateTime creationDateTime;
    private List<Ticket> tickets;

    public User(String name) {
        this.name = name;
        id = UUID.randomUUID();
        creationDateTime = LocalDateTime.now();
        tickets = new ArrayList<>();
    }

    public void printRole() {
        System.out.println(this.getClass().getSimpleName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", creationDateTime=" + creationDateTime +
                ", id=" + id +
                '}';
    }
}
