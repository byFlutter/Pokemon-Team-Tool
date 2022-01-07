package de.htwberlin.webtech.persistence;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "teams")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "game", nullable = false)
    private String game;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<PokemonEntity> pokemons = new ArrayList<>();

    public TeamEntity(String name, String game, Type type) {
        this.name = name;
        this.game = game;
        this.type = type;
    }

    protected TeamEntity() {
    }

    public Long getId() {
        return id;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<PokemonEntity> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<PokemonEntity> pokemons) {
        this.pokemons = pokemons;
    }
}
