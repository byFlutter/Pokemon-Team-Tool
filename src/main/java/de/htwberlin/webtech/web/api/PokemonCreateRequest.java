package de.htwberlin.webtech.web.api;

public class PokemonCreateRequest {
    private String name;
    private String region;
    private boolean evolved;

    public PokemonCreateRequest(String name, String region, boolean evolved) {
        this.name = name;
        this.region = region;
        this.evolved = evolved;
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
}
