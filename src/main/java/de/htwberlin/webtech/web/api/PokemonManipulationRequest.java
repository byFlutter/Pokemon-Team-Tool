package de.htwberlin.webtech.web.api;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PokemonManipulationRequest {
    @Size(min = 3, message = "Bitte gib einen Namen mit mindestens drei Buchstaben an.")
    private String name;

    @NotBlank(message = "Bitte gib eine Region an.")
    private String region;

    @Pattern(
            regexp = "Normal|Feuer|Wasser|Pflanze|Elektro|Eis|Kampf|Gift|Boden|Flug|Psycho|Kaefer|Gestein|Geist|Drache|Unlicht|Stahl|Fee|Unbekannt",
            message = "Bitte gib den Typ des Pokémon an."
    )
    private String type;

    @Range(min = 1, max = 100, message = "Bitte gib ein Level zwischen 1-100 an.")
    private int level;

    private boolean evolved;

    private Long teamId;


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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
