package com.ernie.TicketApp;

import java.util.UUID;

public abstract class Entity implements Printable {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
