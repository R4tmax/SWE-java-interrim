package cz.vse.java.kadm09.jfx.gatekeepermk2.knight;

import java.util.ArrayList;

public class TheKnight {
    public final int MAX_HEALTH = 200;
    protected int currentHealth = 200;
    public final int MAX_MANA = 50;
    protected int currentMana = 50;
    protected int armor = 2;
    protected int damage = 15;
    protected int goldHeld = 250;
    //public static final ArrayList<Consumable> inventory = new ArrayList<>();
    public final int INVENTORY_CAP = 5;
    protected Coordinates position = new Coordinates(4,2);
    protected boolean isDead = false;

    public TheKnight() {
    }



}
