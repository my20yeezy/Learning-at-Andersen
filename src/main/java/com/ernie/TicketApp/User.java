package com.ernie.TicketApp;

public abstract class User extends Entity {

    public void printRole() {
        System.out.println(this.getClass().getSimpleName());
    }
}
