package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.Type;
import de.htwberlin.webtech.persistence.TeamEntity;
import de.htwberlin.webtech.persistence.PokemonEntity;
import de.htwberlin.webtech.web.api.Team;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TeamTransformer {
    public Team transformEntity(TeamEntity teamEntity) {
        var type = teamEntity.getType() != null ? teamEntity.getType().name() : Type.Unbekannt.name();
        var pokemonIds = teamEntity.getPokemons().stream().map(PokemonEntity::getId).collect(Collectors.toList());
        return new Team(
                teamEntity.getId(),
                teamEntity.getName(),
                teamEntity.getGame(),
                type,
                pokemonIds);
    }
}
