package de.htwberlin.webtech.web.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TeamManipulationRequest {
    @Size(min = 3, message = "Bitte gib einen Namen mit mindestens drei Buchstaben an.")
    private String name;

    @NotBlank(message = "Bitte gib ein Spiel an, für das du das Team erstellen möchtest.")
    private String game;

    @Pattern(
            regexp = "Normal|Feuer|Wasser|Pflanze|Elektro|Eis|Kampf|Gift|Boden|Flug|Psycho|Kaefer|Gestein|Geist|Drache|Unlicht|Stahl|Fee|Unbekannt",
            message = "Bitte gib den vorherrschenden Typ des Teams an."
    )
    private String type;

    public TeamManipulationRequest() {}

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
