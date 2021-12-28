package de.htwberlin.webtech.web.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PokemonManipulationRequest {
    @Size(min = 3, message = "Bitte gib einen Namen mit mindestens drei Buchstaben an.")
    private String name;

    @NotBlank(message = "Bitte gib eine Region an.")
    private String region;

    /* @Pattern(
            regexp = "MALE|FEMALE|DIVERSE|UNKOWN",
            message = "Please provide 'MALE', 'FEMALE', 'DIVERSE' or 'UNKNOWN' for gender"
    )
    private String gender; */

    private boolean evolved;

    /* public PokemonManipulationRequest(String name, String region, boolean evolved) {
        this.name = name;
        this.region = region;
        this.evolved = evolved;
    } */

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
