package de.htwberlin.webtech.web;

import de.htwberlin.webtech.service.PokemonService;
import de.htwberlin.webtech.web.api.Pokemon;
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

@WebMvcTest(PokemonRestController.class)
class PokemonRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService pokemonService;

    @Test
    @DisplayName("should return all found pokemon from pokemon service")
    void should_return_all_found_pokemon_from_pokemon_service() throws Exception {
        // given
        var pokemons = List.of(
                new Pokemon(1, "Glurak", "Kanto", "Feuer", true, 100,
                        new Team(1, "Super Team", "Pokémon Rot", "Normal", Collections.emptyList())),
                new Pokemon(2, "Bisasam", "Hoenn", "Pflanze", false, 1,
                        new Team(2, "Legendäre Pokémon", "Pokémon GO", "Stahl", Collections.emptyList()))
        );
        doReturn(pokemons).when(pokemonService).findAll();

        // when
        mockMvc.perform(get("/api/v1/allPokemon"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Glurak"))
                .andExpect(jsonPath("$[0].region").value("Kanto"))
                .andExpect(jsonPath("$[0].type").value("Feuer"))
                .andExpect(jsonPath("$[0].evolved").value(true))
                .andExpect(jsonPath("$[0].level").value(100))
                .andExpect(jsonPath("$[0].team.id").value(1))
                .andExpect(jsonPath("$[0].team.name").value("Super Team"))
                .andExpect(jsonPath("$[0].team.game").value("Pokémon Rot"))
                .andExpect(jsonPath("$[0].team.type").value("Normal"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Bisasam"))
                .andExpect(jsonPath("$[1].region").value("Hoenn"))
                .andExpect(jsonPath("$[1].type").value("Pflanze"))
                .andExpect(jsonPath("$[1].team.id").value(2))
                .andExpect(jsonPath("$[1].team.name").value("Legendäre Pokémon"))
                .andExpect(jsonPath("$[1].team.game").value("Pokémon GO"))
                .andExpect(jsonPath("$[1].team.type").value("Stahl"));;
    }

    @Test
    @DisplayName("should return 404 if pokemon is not found")
    void should_return_404_if_pokemon_is_not_found() throws Exception {
        // given
        doReturn(null).when(pokemonService).findById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/allPokemon/234"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status and Location header when creating a pokemon")
    void should_return_201_http_status_and_location_header_when_creating_a_pokemon() throws Exception {
        // given
        String pokemonToCreateAsJson = "{\"name\": \"Glurak\", \"region\":\"Kanto\", \"type\":\"Feuer\"," +
                "\"evolved\": true, \"level\":\"100\", \"team.name\":\"Super Team\"," +
                "\"team.game\":\"Pokémon Diamant\", \"team.type\":\"Feuer\", \"team.pokemonIds\":\"1\"}";
        var pokemon = new Pokemon(234, null, null, null, false, 0, null);
        doReturn(pokemon).when(pokemonService).create(any());

        // when
        mockMvc.perform(
                post("/api/v1/allPokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pokemonToCreateAsJson)
        )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/api/v1/allPokemon/" + pokemon.getId()))));
//            .andExpect(header().string("Location", Matchers.containsString(Long.toString(team.getId()))));

    }

    @Test
    @DisplayName("should validate create pokemon request with too short name")
    void should_validate_create_pokemon_request_with_too_short_name() throws Exception {
        // given
        String pokemonToCreateAsJson = "{\"name\": \"x\", \"region\":\"Kanto\", \"type\":\"Feuer\"," +
                "\"evolved\": true, \"level\":\"100\", \"team.name\":\"Super Team\"," +
                "\"team.game\":\"Pokémon Diamant\", \"team.type\":\"Feuer\", \"team.pokemonIds\":\"1\"}";

        // when
        mockMvc.perform(
                post("/api/v1/allPokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pokemonToCreateAsJson)
        )
                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should validate create team request with empty region field")
    void should_validate_create_team_request_with_empty_region_field() throws Exception {
        // given
        String pokemonToCreateAsJson = "{\"name\": \"Glurak\", \"region\":\"\", \"type\":\"Feuer\"," +
                "\"evolved\": true, \"level\":\"100\", \"team.name\":\"Super Team\"," +
                "\"team.game\":\"Pokémon Diamant\", \"team.type\":\"Feuer\", \"team.pokemonIds\":\"1\"}";

        // when
        mockMvc.perform(
                post("/api/v1/allPokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pokemonToCreateAsJson)
        )
                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should validate create pokemon request with invalid type")
    void should_validate_create_pokemon_request_with_invalid_type() throws Exception {
        // given
        String pokemonToCreateAsJson = "{\"name\": \"Glurak\", \"region\":\"Kanto\", \"type\":\"Licht\"," +
                "\"evolved\": true, \"level\":\"100\", \"team.name\":\"Super Team\"," +
                "\"team.game\":\"Pokémon Diamant\", \"team.type\":\"Feuer\", \"team.pokemonIds\":\"1\"}";

        // when
        mockMvc.perform(
                post("/api/v1/allPokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pokemonToCreateAsJson)
        )
                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should validate create pokemon request with out of range level")
    void should_validate_create_pokemon_request_with_out_of_range_level() throws Exception {
        // given
        String pokemonToCreateAsJson = "{\"name\": \"Glurak\", \"region\":\"Kanto\", \"type\":\"Feuer\"," +
                "\"evolved\": true, \"level\":\"999\", \"team.name\":\"Super Team\"," +
                "\"team.game\":\"Pokémon Diamant\", \"team.type\":\"Feuer\", \"team.pokemonIds\":\"1\"}";

        // when
        mockMvc.perform(
                post("/api/v1/allPokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pokemonToCreateAsJson)
        )
                // then
                .andExpect(status().isBadRequest());
    }
}


