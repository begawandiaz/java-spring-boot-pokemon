package com.example.pokemonapi.controller;

import com.example.pokemonapi.model.Pokemon;
import com.example.pokemonapi.service.PokemonService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Marks this class as a REST controller:
// - return values are written directly as JSON in HTTP responses.
@RestController
// Base URL for all endpoints in this controller.
@RequestMapping("/api/pokemon")
public class PokemonController {

    // "final" means this dependency is set once via constructor injection.
    private final PokemonService pokemonService;

    // Spring injects PokemonService automatically because it is a @Service bean.
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    // GET /api/pokemon
    // Returns all Pokemon as JSON array.
    @GetMapping
    public List<Pokemon> getAllPokemon() {
        return pokemonService.getAllPokemon();
    }

    // GET /api/pokemon/{id}
    // @PathVariable reads the {id} part from the URL.
    @GetMapping("/{id}")
    public Pokemon getPokemonById(@PathVariable Long id) {
        return pokemonService.getPokemonById(id);
    }

    // PUT /api/pokemon/{id}
    // @RequestBody maps incoming JSON body into a Pokemon Java object.
    // Example JSON: {"name":"Pikachu","type":"Electric","level":10}
    @PutMapping("/{id}")
    public Pokemon updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return pokemonService.updatePokemon(id, pokemon);
    }

    // DELETE /api/pokemon/{id}
    // Returns HTTP 204 No Content when deletion succeeds.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.noContent().build();
    }
}
