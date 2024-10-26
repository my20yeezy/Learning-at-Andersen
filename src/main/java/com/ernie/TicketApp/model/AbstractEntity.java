package com.ernie.TicketApp.model;

import com.ernie.TicketApp.constraint.NullableWarning;
import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Printable {

    @NullableWarning
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
