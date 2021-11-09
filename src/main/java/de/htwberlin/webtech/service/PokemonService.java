package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.PokemonEntity;
import de.htwberlin.webtech.persistence.PokemonRepository;
import de.htwberlin.webtech.web.api.Pokemon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }
    public List<Pokemon> findAll() {
        List<PokemonEntity> allPokemon = pokemonRepository.findAll();
        return allPokemon.stream()
                .map(pokemonEntity -> new Pokemon(
                        pokemonEntity.getId(),
                        pokemonEntity.getName(),
                        pokemonEntity.getRegion(),
                        pokemonEntity.getEvolved()
                ))
                        .collect(Collectors.toList());
    }
}
