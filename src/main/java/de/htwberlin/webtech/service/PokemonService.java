package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.*;
import de.htwberlin.webtech.web.api.Pokemon;
import de.htwberlin.webtech.web.api.PokemonManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final TeamRepository teamRepository;
    private final TeamTransformer teamTransformer;

    public PokemonService(PokemonRepository pokemonRepository, TeamRepository teamRepository, TeamTransformer teamTransformer) {
        this.pokemonRepository = pokemonRepository;
        this.teamRepository = teamRepository;
        this.teamTransformer = teamTransformer;
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
        var type = Type.valueOf(request.getType());
        var team = teamRepository.findById(request.getTeam()).orElseThrow();
        var pokemonEntity = new PokemonEntity(request.getName(), request.getRegion(), request.isEvolved(), type, request.getLevel(), team);
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
        pokemonEntity.setType(Type.valueOf(request.getType()));
        pokemonEntity.setLevel(request.getLevel());
        // following line add by me
        pokemonEntity.setTeam(pokemonEntity.getTeam());
        // pokemonEntity.setTeam(teamRepository.findById(request.getTeamId()));
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
        var type = pokemonEntity.getType() != null ? pokemonEntity.getType().name() : Type.Unbekannt.name();
        return new Pokemon(
                pokemonEntity.getId(),
                pokemonEntity.getName(),
                pokemonEntity.getRegion(),
                type,
                pokemonEntity.getEvolved(),
                pokemonEntity.getLevel(),
                teamTransformer.transformEntity(pokemonEntity.getTeam()));
    }
}
