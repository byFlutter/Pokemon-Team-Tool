package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.PokemonEntity;
import de.htwberlin.webtech.persistence.PokemonRepository;
import de.htwberlin.webtech.web.api.Pokemon;
import de.htwberlin.webtech.web.api.PokemonCreateRequest;
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
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Pokemon create(PokemonCreateRequest request) {
        var pokemonEntity = new PokemonEntity(request.getName(), request.getRegion(), request.isEvolved());
        pokemonEntity = pokemonRepository.save(pokemonEntity);
        return transformEntity(pokemonEntity);

    }

    private Pokemon transformEntity(PokemonEntity pokemonEntity) {
        return new Pokemon(
                pokemonEntity.getId(),
                pokemonEntity.getName(),
                pokemonEntity.getRegion(),
                pokemonEntity.getEvolved()
        );
    }
}
