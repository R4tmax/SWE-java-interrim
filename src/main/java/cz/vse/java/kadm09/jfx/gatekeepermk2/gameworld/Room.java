package cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld;

public class Room {
    protected String name;
    protected String description;

    protected boolean locked;

    public Room(String name, String description, Boolean locked) {
        this.name = name;
        this.description = description;
        this.locked = locked;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLocked() {
        return locked;
    }
}


