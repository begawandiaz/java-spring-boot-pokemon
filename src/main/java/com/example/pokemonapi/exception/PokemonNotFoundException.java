package com.example.pokemonapi.exception;

public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(Long id) {
        super("Pokemon with id " + id + " not found");
    }
}
