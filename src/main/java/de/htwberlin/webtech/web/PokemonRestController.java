package de.htwberlin.webtech.web;

import de.htwberlin.webtech.web.api.Pokemon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PokemonRestController {

    private List<Pokemon> allPokemon;

    public PokemonRestController() {
        allPokemon = new ArrayList<>();
        allPokemon.add(new Pokemon(1,"Glumanda", "Kanto", false));
        allPokemon.add(new Pokemon(286,"Kapilz", "Hoenn", true));
    }

    @GetMapping(path = "/api/v1/allPokemon")
    public ResponseEntity<List<Pokemon>> fetchAllPokemon() {
        return ResponseEntity.ok(allPokemon);
    }

}
