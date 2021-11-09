package de.htwberlin.webtech.web;

import de.htwberlin.webtech.service.PokemonService;
import de.htwberlin.webtech.web.api.Pokemon;
import de.htwberlin.webtech.web.api.PokemonCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping(path = "/api/v1/allPokemon")
    public ResponseEntity<Void> createPokemon(@RequestBody PokemonCreateRequest request) throws URISyntaxException {
        var pokemon = pokemonService.create(request);
        URI uri = new URI("/api/v1/allPokemon/" + pokemon.getId());
        return ResponseEntity.created(uri).build();
    }
}
