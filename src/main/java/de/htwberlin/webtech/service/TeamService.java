package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.Type;
import de.htwberlin.webtech.persistence.TeamEntity;
import de.htwberlin.webtech.persistence.TeamRepository;
import de.htwberlin.webtech.web.api.Team;
import de.htwberlin.webtech.web.api.TeamManipulationRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamTransformer teamTransformer;

    public TeamService(TeamRepository teamRepository, TeamTransformer teamTransformer) {
        this.teamRepository = teamRepository;
        this.teamTransformer = teamTransformer;
    }

    public List<Team> findAll() {
        List<TeamEntity> teams = teamRepository.findAll();
        return teams.stream()
                .map(teamTransformer::transformEntity)
                .collect(Collectors.toList());
    }

    public Team findById(Long id) {
        var teamEntity = teamRepository.findById(id);
        return teamEntity.map(teamTransformer::transformEntity).orElse(null);
    }

    public Team create(TeamManipulationRequest request) {
        var type = Type.valueOf(request.getType());
        var teamEntity = new TeamEntity(request.getName(), request.getGame(), type);
        teamEntity = teamRepository.save(teamEntity);
        return teamTransformer.transformEntity(teamEntity);
    }

    public Team update(Long id, TeamManipulationRequest request) {
        var teamEntityOptional = teamRepository.findById(id);
        if (teamEntityOptional.isEmpty()) {
            return null;
        }
        var teamEntity = teamEntityOptional.get();
        teamEntity.setName(request.getName());
        teamEntity.setGame(request.getGame());
        teamEntity.setType(Type.valueOf(request.getType()));
        teamEntity = teamRepository.save(teamEntity);
        return teamTransformer.transformEntity(teamEntity);
    }

    public boolean deleteById(Long id) {
        if (!teamRepository.existsById(id)) {
            return false;
        }
        teamRepository.deleteById(id);
        return true;
    }
}
