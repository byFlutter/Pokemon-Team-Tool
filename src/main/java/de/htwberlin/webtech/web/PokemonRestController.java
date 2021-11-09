package de.htwberlin.webtech.web;

import de.htwberlin.webtech.service.PokemonService;
import de.htwberlin.webtech.web.api.Pokemon;
import de.htwberlin.webtech.web.api.PokemonCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/api/v1/allPokemon/{id}")
    public ResponseEntity<Pokemon> fetchPokemonById(@PathVariable Long id) {
        var pokemon = pokemonService.findById(id);
        return pokemon != null? ResponseEntity.ok(pokemon) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/allPokemon")
    public ResponseEntity<Void> createPokemon(@RequestBody PokemonCreateRequest request) throws URISyntaxException {
        var pokemon = pokemonService.create(request);
        URI uri = new URI("/api/v1/allPokemon/" + pokemon.getId());
        return ResponseEntity.created(uri).build();
    }
}
