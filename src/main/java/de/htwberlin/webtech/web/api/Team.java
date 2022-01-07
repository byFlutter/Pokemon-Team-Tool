package de.htwberlin.webtech.web.api;

public class Team {

    private long id;
    private String name;
    private String game;
    private String type;

    public Team(long id, String name, String game, String type) {
        this.id = id;
        this.name = name;
        this.game = game;
        this.type = type;
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
}
