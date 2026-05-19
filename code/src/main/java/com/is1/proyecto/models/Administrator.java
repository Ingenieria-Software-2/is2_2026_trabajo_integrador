package com.is1.proyecto.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.javalite.activejdbc.annotations.BelongsTo;

@Table("administrators")
@BelongsTo(parent = Person.class, foreignKeyName = "person_id")
public class Administrator extends Model {

    // -------------------------
    // Relationship
    // -------------------------

    public Person getPerson() {
        return this.parent(Person.class);
    }
}