package cz.vse.java.kadm09.jfx.gatekeepermk2.knight;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.ObservedElement;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Observer;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.RoomType;
import cz.vse.java.kadm09.jfx.gatekeepermk2.items.Consumable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Martin Kadlec
 * @version 2.0.0
 *
 * <p>
 *     Main class of the game.
 *     Represents the player character of the game
 *     through which the entire experience plays out.
 * </p>
 *
 * @see Coordinates
 * @see Map
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Main
 *
 */
public class TheKnight implements ObservedElement {
    public static final int MAX_HEALTH = 225;
    protected int currentHealth;
    public static final int MAX_MANA = 75;
    protected int currentMana;
    protected int armor;
    protected int damage;
    protected int goldHeld;
    public final ArrayList<Consumable> inventory = new ArrayList<>();
    public static final int INVENTORY_CAP = 5;
    protected Coordinates position;
    protected boolean isDead;

    public Set<Observer> listOfObservers = new HashSet<>();


    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        notifyObserver();
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
        notifyObserver();
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
        notifyObserver();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
        notifyObserver();
    }

    public int getGoldHeld() {
        return goldHeld;
    }

    public void setGoldHeld(int goldHeld) {
        this.goldHeld = goldHeld;
        notifyObserver();
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
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

    public String presentCommandListCombat() {
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


    /**
     * This function simply assures that currentHealth
     * can never exceed MAX_HEALTH, take note that
     * below zero checks are handled separately.
     */
    public void preventOverheal () {
        if (this.currentHealth > MAX_HEALTH) this.setCurrentHealth(MAX_HEALTH);
    }

    public int accountForArmor (int damageValue) {
        return Math.max(damageValue, 1);
    }


    /**
     * This function simply assures that currentMana
     * can never exceed MAX_MANA, take note that
     * below zero checks are handled separately.
     */
    public void preventOvercast() {
        if (this.currentMana > MAX_MANA) this.setCurrentMana(MAX_MANA);
    }

    public String moveKnight (String direction, Game game) {
        int tmpHorizontal = game.getPlayer().position.horizontal;
        int tmpVertical = game.getPlayer().position.vertical;
            switch (direction.toLowerCase()) {
                case "north" -> game.getPlayer().position.horizontal -= 1;
                case "west" -> game.getPlayer().position.vertical -= 1;
                case "east" -> game.getPlayer().position.vertical += 1;
                case "south" -> game.getPlayer().position.horizontal += 1;
                default -> System.out.println("Direction processing ERROR.");
            }

      return validateMove(tmpHorizontal,tmpVertical, game.getPlayer(), game.getGameMap());

    }

    private String validateMove(int tmpHorizontal, int tmpVertical, TheKnight player, Map gameMap) {
        try {
            if (gameMap.getCurrentPosition(player.getPosition().getHorizontal(),player.getPosition().getVertical()).isLocked()) {
                player.position.horizontal = tmpHorizontal;
                player.position.vertical = tmpVertical;
                return "You get a sinking feeling, like acid in your stomach!" + "\n" +
                        "Perhaps you should not be here yet? Explore elsewhere!";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            player.position.horizontal = tmpHorizontal;
            player.position.vertical = tmpVertical;
            return "You are leaving the game area, try a different direction!";
        } catch (Exception e){
            return "Unexpected exception!";
        }

        return "";
    }

    public String attemptPickup (Game game) {
        if (game.getGameMap().getCurrentPosition(game.getPlayer().position.horizontal, game.getPlayer().position.vertical).getRoomLoot() == null) {
            return "There is nothing to pickup!";
        }
        else return game.getGameMap().getCurrentPosition(game.getPlayer().position.horizontal, game.getPlayer().position.vertical).getRoomLoot().pickUpEffect(game);
    }

    public String useItem (Game game,String input) {

        String toUse = input.toLowerCase().replaceAll("\\s","");

        for (Consumable consumable : this.inventory) {
            if (toUse.equals(consumable.getName().toLowerCase().replaceAll("\\s",""))) {
                String remember = consumable.executeConsumableEffect(game,consumable.getConsumableType(), consumable.getEffectiveValue());
                this.inventory.remove(consumable);
                return remember;
            }
        }
        return "No such item was found";

    }

    public String presentInventoryContent () {
        StringBuilder toPresent = new StringBuilder("Currently held: \n");
        for (Consumable item : this.inventory ) {
            toPresent.append(item.getName());
            toPresent.append("\n");
        }

        return toPresent.toString();
    }

    public String presentSpelllist() {
        return """
                You have following spells at your disposal:
                => LIGHTNING TOUCH - Deals somewhat minor damage, but is very cheap to cast.
                => HEAL - heals you for a moderate amount for a modest mana cost
                => SMITE - both heals you and damages your opponent, slightly more expensive than the heal
                => LIGHTNING STRIKE - Deals enormous damage to your opponent, but drains your mana completely!!
                => PRAYER OF STRENGTH - Improves your damage for the rest of the game, but it is rather mana taxing. Requires concentration!
                => PRAYER OF RESOLVE - Improves your armor for the rest of the game, but it is rather mana taxing. Requires concentration!
                                
                Take heed, knight, some spells should not be attempted during combat!
                """;
    }

    public String lightningTouch(Game game) {
        int manaCost = 5;
        if (manaCost > this.currentMana) {
            return "Not enough mana to cast!";
        }

        if (game.getGameMap().getCurrentPosition(this.position.horizontal, this.position.vertical).getRoomEnemy() == null) {
            return "No target!";
        }


        this.setCurrentMana(this.getCurrentMana() - manaCost);
        game.getGameMap().getCurrentPosition(this.position.horizontal, this.position.vertical).getRoomEnemy().setHealth(game.getGameMap().getCurrentPosition(this.position.horizontal, this.position.vertical).getRoomEnemy().getHealth() - 100);
        return "Your enemy took a nice hit!";
    }

    public String heal() {
        int manaCost = 15;
        if (manaCost > this.currentMana) {
            return "Not enough mana to cast!";
        }

        this.setCurrentMana(this.getCurrentMana() - manaCost);
        this.setCurrentHealth(this.getCurrentHealth() +150);
        this.preventOverheal();
        return "You feel better!";
    }

    /**
     * Combines functionalities of lightning touch and heal.
     * With modified values.
     * <p>
     * <p>
     * Take note that from technical standpoint this
     * is considered to be offensive spell, and as such cannot be used
     * outside of combat.
     */
    public String holySmite(Game game) {
        int manaCost = 25;
        if (manaCost > this.currentMana) {
            return "Not enough mana to cast!";
        }

        if (game.getGameMap().getCurrentPosition(this.position.horizontal, this.position.vertical).getRoomEnemy() == null) {
            return "No target!";
        }

        this.setCurrentMana(this.getCurrentMana() - manaCost);
        this.setCurrentHealth(this.getCurrentHealth() + 50);
        this.preventOverheal();

        game.getGameMap().getCurrentPosition(this.position.horizontal, this.position.vertical).getRoomEnemy().setHealth(game.getGameMap().getCurrentPosition(this.position.horizontal, this.position.vertical).getRoomEnemy().getHealth() - 200);
        return "You feel slightly better and your enemy took a hit!";
    }

    /**
     * Supercharged version of lightning touch.
     *
     */
    public String lightningStrike(Game game) {
        int manaCost = 50;
        if (manaCost > this.currentMana) {
            return "Not enough mana to cast!";
        }

        if (game.getGameMap().getCurrentPosition(this.position.horizontal, this.position.vertical).getRoomEnemy() == null) {

            return "No target!";
        }


        this.setCurrentMana(this.getCurrentMana() - manaCost);
        game.getGameMap().getCurrentPosition(this.position.horizontal, this.position.vertical).getRoomEnemy().setHealth(game.getGameMap().getCurrentPosition(this.position.horizontal, this.position.vertical).getRoomEnemy().getHealth() - 600);
        return "Your enemy took a massive hit!";
    }

    /**
     * Permanently increases armor value of
     * The Knight upon use.
     * <p>
     * Take note that trying to cast this in
     * 'hostile' rooms will break cause miscast.
     */
    public String prayerOfResolve(Game game) {
        int manaCost = 35;
        if (manaCost > this.currentMana) {
            return "Not enough mana to cast!";
        }

        if (game.getGameMap().getCurrentPosition(this.position.horizontal, this.position.vertical).getRoomBehavior() == RoomType.HOSTILE) {
            this.setCurrentMana(this.getCurrentMana() - manaCost);
            return  "Concentration broken!" + " Cast failed!";
        }

        this.setCurrentMana(this.getCurrentMana() - manaCost);
        this.setArmor(this.getArmor()+2);
        return "You feel better suited to deal with the task at hand";
    }

    /**
     * Permanently increases damage value of
     * The Knight upon use.
     * <p>
     * Take note that trying to cast this in
     * 'hostile' rooms will break cause miscast.
     */
    public String prayerOfStrength(Game game) {
        int manaCost = 35;
        if (manaCost > this.currentMana) {
            return "Not enough mana to cast!";
        }

        if (game.getGameMap().getCurrentPosition(this.position.horizontal, this.position.vertical).getRoomBehavior() == RoomType.HOSTILE) {
            this.setCurrentMana(this.getCurrentMana() - manaCost);
            return  "Concentration broken!" + " Cast failed!";
        }

        this.setCurrentMana(this.getCurrentMana() - manaCost);
        this.setDamage(this.getDamage() + 5);
        return "You feel better suited to deal with the task at hand";
    }


    @Override
    public void registerObserver(Observer observer) {
        listOfObservers.add(observer);
    }

    private void notifyObserver() {
        for(Observer observer : listOfObservers) {
            observer.updateStatus();
        }
    }


}
