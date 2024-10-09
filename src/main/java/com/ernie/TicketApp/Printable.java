package com.ernie.TicketApp;

public interface Printable {
    default void print() {
        System.out.println(this);
    }
}
