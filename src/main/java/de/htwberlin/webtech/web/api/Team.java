package de.htwberlin.webtech.web.api;

import java.util.List;

public class Team {

    private long id;
    private String name;
    private String game;
    private String type;
    private List<Long> pokemonIds;
    // private Pokemon pokemon;

    public Team(long id, String name, String game, String type, List<Long> pokemonIds) {
        this.id = id;
        this.name = name;
        this.game = game;
        this.type = type;
        this.pokemonIds = pokemonIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Long> getPokemonIds() {
        return pokemonIds;
    }

    public void setPokemonIds(List<Long> pokemonIds) {
        this.pokemonIds = pokemonIds;
    }
}
