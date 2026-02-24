package com.example.pokemonapi.service;

import com.example.pokemonapi.exception.PokemonNotFoundException;
import com.example.pokemonapi.model.Pokemon;
import com.example.pokemonapi.repository.PokemonRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getAllPokemon() {
        return new ArrayList<>(pokemonRepository.findAll());
    }

    public Pokemon getPokemonById(Long id) {
        // SQL explanation:
        // This service method calls `findByIdNative(id)` from the repository.
        // That repository method executes this SQL on PostgreSQL:
        // SELECT id, name, type, level FROM pokemon WHERE id = :id
        // If no row matches, Optional is empty and we throw 404 via PokemonNotFoundException.
        return pokemonRepository.findByIdNative(id)
                .orElseThrow(() -> new PokemonNotFoundException(id));
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

        return pokemonRepository.save(existingPokemon);
    }

    public void deletePokemon(Long id) {
        if (!pokemonRepository.existsById(id)) {
            throw new PokemonNotFoundException(id);
        }
        pokemonRepository.deleteById(id);
    }
}
