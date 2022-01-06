package de.htwberlin.webtech.persistence;

import javax.persistence.*;

@Entity(name = "all_pokemon")
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "is_evolved")
    private Boolean evolved;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Column(name = "level")
    private Integer level;

    public PokemonEntity(String name, String region, Boolean evolved, Type type, Integer level) {
        this.name = name;
        this.region = region;
        this.evolved = evolved;
        this.type = type;
        this.level = level;
    }

    protected PokemonEntity() {
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Boolean getEvolved() {
        return evolved;
    }

    public void setEvolved(Boolean evolved) {
        this.evolved = evolved;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
