package com.ernie.TicketApp.model;

import com.ernie.TicketApp.constraint.NullableWarning;

import java.util.UUID;

public abstract class Entity implements Printable {

    @NullableWarning
    public UUID id = UUID.randomUUID();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
