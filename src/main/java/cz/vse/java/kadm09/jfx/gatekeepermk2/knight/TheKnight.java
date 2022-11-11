package cz.vse.java.kadm09.jfx.gatekeepermk2.knight;

import java.util.ArrayList;

public class TheKnight {
    public final int MAX_HEALTH = 200;
    protected int currentHealth;
    public final int MAX_MANA = 50;
    protected int currentMana;
    protected int armor;
    protected int damage;
    protected int goldHeld;
    //public static final ArrayList<Consumable> inventory = new ArrayList<>();
    public static final int INVENTORY_CAP = 5;
    protected Coordinates position;
    protected boolean isDead = false;


    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    public int getGoldHeld() {
        return goldHeld;
    }

    public boolean isDead() {
        return isDead;
    }

    public TheKnight(int currentHealth, int currentMana, int armor, int damage, int goldHeld, Coordinates position, boolean isDead) {
        this.currentHealth = currentHealth;
        this.currentMana = currentMana;
        this.armor = armor;
        this.damage = damage;
        this.goldHeld = goldHeld;
        this.position = position;
        this.isDead = isDead;
    }

    public Coordinates getPosition() {
        return position;
    }
}
