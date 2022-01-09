package de.htwberlin.webtech.web;

        import de.htwberlin.webtech.service.TeamService;
        import de.htwberlin.webtech.web.api.Team;
        import org.hamcrest.Matchers;
        import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.http.MediaType;
        import org.springframework.test.web.servlet.MockMvc;

        import java.util.Collections;
        import java.util.List;
        import java.util.Optional;

        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.ArgumentMatchers.anyLong;
        import static org.mockito.ArgumentMatchers.contains;
        import static org.mockito.Mockito.doReturn;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamRestController.class)
class TeamRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Test
    @DisplayName("should return found teams from team service")
    void should_return_found_teams_from_team_service() throws Exception {
        // given
        var teams = List.of(
                new Team(1, "Super Team", "Pokémon Rot", "Normal", Collections.emptyList()),
                new Team(2, "Legendäre Pokémon", "Pokémon GO", "Stahl", Collections.emptyList())
        );
        doReturn(teams).when(teamService).findAll();

        // when
        mockMvc.perform(get("/api/v1/teams"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Super Team"))
                .andExpect(jsonPath("$[0].game").value("Pokémon Rot"))
                .andExpect(jsonPath("$[0].type").value("Normal"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Legendäre Pokémon"))
                .andExpect(jsonPath("$[1].game").value("Pokémon GO"))
                .andExpect(jsonPath("$[1].type").value("Stahl"));
    }

    @Test
    @DisplayName("should return 404 if team is not found")
    void should_return_404_if_team_is_not_found() throws Exception {
        // given
        doReturn(null).when(teamService).findById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/teams/234"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status and Location header when creating a team")
    void should_return_201_http_status_and_location_header_when_creating_a_team() throws Exception {
        // given
        String teamToCreateAsJson = "{\"name\": \"Super Team\", \"game\":\"Pokémon Diamant\", \"type\":\"Normal\"}";
        var team = new Team(234, null, null, null, null);
        doReturn(team).when(teamService).create(any());

        // when
        mockMvc.perform(
                post("/api/v1/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamToCreateAsJson)
        )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/api/v1/teams/" + team.getId()))));
//            .andExpect(header().string("Location", Matchers.containsString(Long.toString(team.getId()))));

    }

    @Test
    @DisplayName("should validate create team request with too short name")
    void should_validate_create_team_request_with_too_short_name() throws Exception {
        // given
        String teamToCreateAsJson = "{\"name\": \"x\", \"game\": \"Pokémon Diamant\", \"type\":\"Normal\"}";

        // when
        mockMvc.perform(
                post("/api/v1/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamToCreateAsJson)
        )
                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should validate create team request with empty game field")
    void should_validate_create_team_request_with_empty_game_field() throws Exception {
        // given
        String teamToCreateAsJson = "{\"name\": \"Super Team\", \"game\": \"\", \"type\":\"Normal\"}";

        // when
        mockMvc.perform(
                post("/api/v1/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamToCreateAsJson)
        )
                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should validate create team request with invalid type")
    void should_validate_create_team_request_with_invalid_type() throws Exception {
        // given
        String teamToCreateAsJson = "{\"name\": \"Super Team\", \"game\": \"Pokémon Diamant\", \"type\":\"Licht\"}";

        // when
        mockMvc.perform(
                post("/api/v1/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamToCreateAsJson)
        )
                // then
                .andExpect(status().isBadRequest());
    }
}
