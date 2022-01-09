package de.htwberlin.webtech.web.api;

public class Pokemon {

    private long id;
    private String name;
    private String region;
    private String type;
    private boolean evolved;
    private int level;
    private Team team;

    public Pokemon(long id, String name, String region, String type, boolean evolved, int level, Team team) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.type = type;
        this.evolved = evolved;
        this.level = level;
        this.team = team;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isEvolved() {
        return evolved;
    }

    public void setEvolved(boolean evolved) {
        this.evolved = evolved;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
