package de.htwberlin.webtech.web;

import de.htwberlin.webtech.service.PokemonService;
import de.htwberlin.webtech.service.TeamService;
import de.htwberlin.webtech.web.api.Pokemon;
import de.htwberlin.webtech.web.api.PokemonManipulationRequest;
import de.htwberlin.webtech.web.api.Team;
import de.htwberlin.webtech.web.api.TeamManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Validated
public class TeamRestController {
    private final TeamService teamService;

    public TeamRestController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(path = "/api/v1/teams")
    public ResponseEntity<List<Team>> fetchTeams() {
        return ResponseEntity.ok(teamService.findAll());
    }

    @GetMapping(path = "/api/v1/teams/{id}")
    public ResponseEntity<Team> fetchTeamById(@PathVariable Long id) {
        var team = teamService.findById(id);
        return team != null? ResponseEntity.ok(team) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/teams")
    public ResponseEntity<Void> createTeam(@Valid @RequestBody TeamManipulationRequest request) throws URISyntaxException {
        var team = teamService.create(request);
        URI uri = new URI("/api/v1/teams/" + team.getId());
        return ResponseEntity
                .created(uri)
                .header("Access-Control-Expose-Headers", "Location")
                .build();
    }

    @PutMapping(path = "/api/v1/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody TeamManipulationRequest request) {
        var team = teamService.update(id, request);
        return team != null? ResponseEntity.ok(team) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/teams/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable long id) {
        boolean successful = teamService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
