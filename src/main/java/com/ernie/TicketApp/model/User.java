package com.ernie.TicketApp.model;

public abstract class User extends Entity {

    public void printRole() {
        System.out.println(this.getClass().getSimpleName());
    }
}
