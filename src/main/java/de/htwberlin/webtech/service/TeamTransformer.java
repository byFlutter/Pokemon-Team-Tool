package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.Type;
import de.htwberlin.webtech.persistence.TeamEntity;
import de.htwberlin.webtech.web.api.Team;
import org.springframework.stereotype.Service;

@Service
public class TeamTransformer {
    public Team transformEntity(TeamEntity teamEntity) {
        var type = teamEntity.getType() != null ? teamEntity.getType().name() : Type.Unbekannt.name();
        var pokemonId = teamEntity.getPokemon() != null ? teamEntity.getPokemon().getId() : null;
        return new Team(
                teamEntity.getId(),
                teamEntity.getName(),
                teamEntity.getGame(),
                type,
                pokemonId);
    }
}
