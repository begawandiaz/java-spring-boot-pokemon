package com.example.pokemonapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 Beginner guide for this class:

 1) This class is a "model" (also called an "entity") for one Pokemon record.
    Think of it as one row in a database table.

 2) Spring Boot + JPA read these annotations to know:
    - which table to use
    - which column maps to which Java field
    - which field is the primary key

 3) When data comes from PostgreSQL, JPA creates Pokemon objects from table rows.
    When data is saved, JPA converts Pokemon objects back into SQL INSERT/UPDATE.

 4) Getters and setters are important because:
    - Jackson (JSON library) uses them for API request/response mapping
    - JPA uses them/fields to read and write values
*/
@Entity
@Table(name = "pokemon")
public class Pokemon {
    // @Id marks the primary key column in SQL (unique id for each row).
    @Id
    // IDENTITY means PostgreSQL can auto-generate id values when inserting new rows.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Maps this field to the "id" column in the pokemon table.
    @Column(name = "id")
    private Long id;

    // "name" column, and nullable = false means the value cannot be NULL in DB.
    @Column(name = "name", nullable = false)
    private String name;

    // "type" column, also required (NOT NULL).
    @Column(name = "type", nullable = false)
    private String type;

    // "level" column, also required (NOT NULL).
    @Column(name = "level", nullable = false)
    private Integer level;

    // Required by JPA: a no-args constructor so framework can create objects.
    public Pokemon() {
    }

    // Convenience constructor for creating full objects in code.
    public Pokemon(Long id, String name, String type, Integer level) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
    }

    // Getter for id (used when returning JSON and by framework internals).
    public Long getId() {
        return id;
    }

    // Setter for id (usually set by DB on insert, but still useful for mapping/tests).
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for name.
    public String getName() {
        return name;
    }

    // Setter for name.
    public void setName(String name) {
        this.name = name;
    }

    // Getter for type.
    public String getType() {
        return type;
    }

    // Setter for type.
    public void setType(String type) {
        this.type = type;
    }

    // Getter for level.
    public Integer getLevel() {
        return level;
    }

    // Setter for level.
    public void setLevel(Integer level) {
        this.level = level;
    }
}
