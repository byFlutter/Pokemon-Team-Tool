package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.PokemonEntity;
import de.htwberlin.webtech.persistence.PokemonRepository;
import de.htwberlin.webtech.web.api.Pokemon;
import de.htwberlin.webtech.web.api.PokemonManipulationRequest;
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

    public Pokemon findById(Long id) {
        var pokemonEntity = pokemonRepository.findById(id);
        return pokemonEntity.map(this::transformEntity).orElse(null);
    }

    public Pokemon create(PokemonManipulationRequest request) {
        var pokemonEntity = new PokemonEntity(request.getName(), request.getRegion(), request.isEvolved());
        pokemonEntity = pokemonRepository.save(pokemonEntity);
        return transformEntity(pokemonEntity);

    }

    public Pokemon update(Long id, PokemonManipulationRequest request) {
        var pokemonEntityOptional = pokemonRepository.findById(id);
        if (pokemonEntityOptional.isEmpty()) {
            return null;
        }
        var pokemonEntity = pokemonEntityOptional.get();
        pokemonEntity.setName(request.getName());
        pokemonEntity.setRegion(request.getRegion());
        pokemonEntity.setEvolved(request.isEvolved());
        pokemonEntity = pokemonRepository.save(pokemonEntity);

        return transformEntity(pokemonEntity);
    }

    public boolean deleteById(Long id) {
        if (!pokemonRepository.existsById(id)) {
            return false;
        }

        pokemonRepository.deleteById(id);
        return true;
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
