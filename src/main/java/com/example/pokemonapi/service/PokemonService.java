package com.example.pokemonapi.service;

import com.example.pokemonapi.exception.PokemonNotFoundException;
import com.example.pokemonapi.model.Pokemon;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private final Map<Long, Pokemon> pokemonStore = new ConcurrentHashMap<>();

    @PostConstruct
    public void seedData() {
        pokemonStore.put(1L, new Pokemon(1L, "Bulbasaur", "Grass/Poison", 5));
        pokemonStore.put(2L, new Pokemon(2L, "Charmander", "Fire", 5));
        pokemonStore.put(3L, new Pokemon(3L, "Squirtle", "Water", 5));
    }

    public List<Pokemon> getAllPokemon() {
        return new ArrayList<>(pokemonStore.values());
    }

    public Pokemon getPokemonById(Long id) {
        Pokemon pokemon = pokemonStore.get(id);
        if (pokemon == null) {
            throw new PokemonNotFoundException(id);
        }
        return pokemon;
    }

    public Pokemon updatePokemon(Long id, Pokemon updateRequest) {
        Pokemon existingPokemon = getPokemonById(id);

        if (updateRequest.getName() != null) {
            existingPokemon.setName(updateRequest.getName());
        }
        if (updateRequest.getType() != null) {
            existingPokemon.setType(updateRequest.getType());
        }
        if (updateRequest.getLevel() != null) {
            existingPokemon.setLevel(updateRequest.getLevel());
        }

        pokemonStore.put(id, existingPokemon);
        return existingPokemon;
    }

    public void deletePokemon(Long id) {
        if (pokemonStore.remove(id) == null) {
            throw new PokemonNotFoundException(id);
        }
    }
}
