package com.example.pokemonapi.model;

public class Pokemon {
    private Long id;
    private String name;
    private String type;
    private Integer level;

    public Pokemon() {
    }

    public Pokemon(Long id, String name, String type, Integer level) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
