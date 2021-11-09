package de.htwberlin.webtech.web.api;

public class Pokemon {

    private long id;
    private String name;
    private String region;
    private boolean evolved;

    public Pokemon(long id, String name, String region, boolean evolved) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.evolved = evolved;
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
}
