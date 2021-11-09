package de.htwberlin.webtech.web;

import de.htwberlin.webtech.service.PokemonService;
import de.htwberlin.webtech.web.api.Pokemon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PokemonRestController {

   private final PokemonService pokemonService;

    public PokemonRestController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping(path = "/api/v1/allPokemon")
    public ResponseEntity<List<Pokemon>> fetchAllPokemon() {
        return ResponseEntity.ok(pokemonService.findAll());
    }

}
