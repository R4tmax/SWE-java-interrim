package cz.vse.java.kadm09.jfx.gatekeepermk2.knight;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;

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

    public String presentKnightStatusExploration() {
        String toPresent = "Current health: " + this.currentHealth + "\n";


        if (this.currentMana > 0) toPresent += "Current mana: " + this.currentMana ;
        else toPresent += "You are out of mana!";
        toPresent += "\n";

        toPresent += "Current mana: " + this.currentMana + "\n";
        toPresent += "Armor value: " + this.armor + "\n";
        toPresent += "Damage: " + this.damage + "\n";
        toPresent += "Gold held: " + this.goldHeld + "\n";


        return toPresent;
    }

    public String presentCommandListExploration() {
        return """ 
                Following commands are available to you at the moment:
                => ATTACK - Deal direct damage to your enemy, if you are lucky, you can deal critical damage for twice the usual amount.
                => SHOW INVENTORY - Prints the contents of your inventory. (this won't cost you your turn)
                => USE ITEM - allows you to use items held in your inventory
                => SPELL LIST - prints available spells and basic info about them (this won't cost you your turn)
                => CAST - Allows you the cast spells, expects name of the spell as input after prompt
                
                Running away from monsters is not an option, good luck!
                """;
    }

    public void moveKnight (String direction, TheKnight player, Map gameMap) {
        int tmpHorizontal = player.position.horizontal;
        int tmpVertical = player.position.vertical;

        direction = direction.replaceAll("\\s","");
            switch (direction.toLowerCase()) {
                case "north" -> player.position.horizontal -= 1;
                case "west" -> player.position.vertical -= 1;
                case "east" -> player.position.vertical += 1;
                case "south" -> player.position.horizontal += 1;
                default -> System.out.println("Unknown direction");
            }


    }

    private void validateMove(int tmpHorizontal, int tmpVertical) {
    }
}
