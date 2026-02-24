package com.example.pokemonapi.repository;

import com.example.pokemonapi.model.Pokemon;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    // This native SQL is executed by PostgreSQL directly.
    // Equivalent SQL sent to DB:
    // SELECT id, name, type, level FROM pokemon WHERE id = ?
    @Query(value = "SELECT id, name, type, level FROM pokemon WHERE id = :id", nativeQuery = true)
    Optional<Pokemon> findByIdNative(@Param("id") Long id);
}
