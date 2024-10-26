package com.ernie.TicketApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "users_info")
public class User extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private LocalDateTime creationDateTime;

    public User(String name) {
        this.name = name;
        creationDateTime = LocalDateTime.now();
    }

    public User() {
        creationDateTime = LocalDateTime.now();
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
