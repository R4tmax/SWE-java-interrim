package cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld;

import cz.vse.java.kadm09.jfx.gatekeepermk2.enemies.Monster;
import cz.vse.java.kadm09.jfx.gatekeepermk2.items.Item;

/**
 * @author Martin Kadlec
 * @version Last refactor on 4.12.2022
 *
 * <p>Intermediary class between coordinates and map
 * Classes , provides template for which data is held by individual rooms</p>
 *
 * @see Map
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.knight.Coordinates
 */
public class Room {
    protected String name;
    protected String description;

    protected boolean isLocked;
    protected Item roomLoot;
    protected Monster roomEnemy;
    protected RoomType roomBehavior;


    /**
     * Standard Room constructor.
     *
     * @param name Name of the room
     * @param description Description of the room to be presented to the player
     * @param isLocked Boolean, blocks player access to certain rooms
     * @param roomLoot Holds the Item class, player can pick these items up for usage or gold.
     * @param roomEnemy Hold the Monster class instance, hostile to the player.
     * @param roomBehavior Gives the imprint for the interact command to further direct the player
     */
    public Room(String name, String description, boolean isLocked, Item roomLoot, Monster roomEnemy, RoomType roomBehavior) {
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        this.roomLoot = roomLoot;
        this.roomEnemy = roomEnemy;
        this.roomBehavior = roomBehavior;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Item getRoomLoot() {
        return roomLoot;
    }

    public void setRoomLoot(Item roomLoot) {
        this.roomLoot = roomLoot;
    }

    public Monster getRoomEnemy() {
        return roomEnemy;
    }

    public void setRoomEnemy(Monster roomEnemy) {
        this.roomEnemy = roomEnemy;
    }

    public RoomType getRoomBehavior() {
        return roomBehavior;
    }
}


