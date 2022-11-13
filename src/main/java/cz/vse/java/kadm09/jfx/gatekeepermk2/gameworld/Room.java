package cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld;

import cz.vse.java.kadm09.jfx.gatekeepermk2.enemies.Monster;
import cz.vse.java.kadm09.jfx.gatekeepermk2.items.Item;

public class Room {
    protected String name;
    protected String description;

    protected boolean isLocked;
    protected Item roomLoot;
    protected Monster roomEnemy;
    protected RoomType roomBehavior;

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


