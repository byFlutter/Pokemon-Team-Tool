package de.htwberlin.webtech.web.api;

public class PokemonManipulationRequest {
    private String name;
    private String region;
    private boolean evolved;

    public PokemonManipulationRequest(String name, String region, boolean evolved) {
        this.name = name;
        this.region = region;
        this.evolved = evolved;
    }

    public PokemonManipulationRequest() {}

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
