package com.ernie.TicketApp.model;

public interface Printable {
    default void print() {
        System.out.println(this);
    }
}
