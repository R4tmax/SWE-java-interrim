package cz.vse.java.kadm09.jfx.gatekeepermk2.knight;

import cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary.TextHandler;
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
 * @version Last refactor on 4.12.2022
 *
 * <p>
 *     Main class of the game.
 *     Represents the player character of the game
 *     through which the entire experience plays out.
 *     Take note that this class, together with the Map Class, has been entirely
 *     reworked from the original CLI version used in 4IT101.
 * </p>
 *
 * @see Coordinates
 * @see Map
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Main
 * @see Game
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

    public ArrayList<Consumable> getInventory() {
        return inventory;
    }

    /**
     * Standard Knight constructor.
     *
     * @param currentHealth Health of the player, game ends if this reaches 0, cannot exceed MAXHEALTH
     * @param currentMana Mana of the player, used as a resource to cast spells, cannot exceed MAXMANA
     * @param armor Armor value of the player, reduces incoming damage by a flat amount
     * @param damage Damage of the player, is applied to monsters when striking
     * @param goldHeld Gold pool of the player, used to buy upgrades and rest.
     * @param position Coordinates of the player, interacted with through Map class
     * @param isDead Boolean, set to true if health drops below (or equals) 0
     */
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

    /**
     * @return Returns all of the stats of the player
     */
    public String presentKnightStatusExploration() {
        String toPresent = "Current health: " + this.currentHealth + "\n";


        if (this.currentMana > 0) toPresent += "Current mana: " + this.currentMana ;
        else toPresent += "You are out of mana!";
        toPresent += "\n";
        toPresent += "Armor value: " + this.armor + "\n";
        toPresent += "Damage: " + this.damage + "\n";
        toPresent += "Gold held: " + this.goldHeld + "\n";


        return toPresent;
    }

    /**
     * @return Possible commands for the exploration gamestate
     */
    public String presentCommandListExploration() {
        return  """
                Following commands are available to you at the moment:
                => MOVE - Allows you to change rooms, expects direction in terms of: NORTH, WEST,EAST,SOUTH after prompt
                => LOOK AROUND - Prints you the description of your surroundings.
                => STATUS - Prints stats of the Knight, including gold carried.
                => INTERACT - Allows you to trigger special effects in certain rooms, use this to talk to people.
                => LOOT - Allows you to grab certain items from the world.
                => SHOW INVENTORY - Prints the contents of your inventory.
                => USE ITEM - allows you to use items held in your inventory
                => SPELL LIST - prints available spells and basic info about them
                => CAST - Allows you the cast spells, expects name of the spell as input after prompt
                => QUIT GAME - ends the game, take not that this wont work in combat.
                """;
    }

    /**
     * @return Possible commands for the command gamestate
     */
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

    /**
     * Auxiliary function preventing damage values
     * of Monster subtypes going negative, which would
     * lead to healing the player instead of damaging him.
     * See respective implementations of attackPattern in
     * respective subtypes
     *
     * @param damageValue Integer value representing the damage calculated after compared to Knight armor value
     * @return Integer, either unchanged damageValue if after armor calculation number is still greater than one,
     *          1 if number is less than 1, which would result erroneous operations with health/mana values of TheKnight class.
     *
     * @see cz.vse.java.kadm09.jfx.gatekeepermk2.enemies.Monster
     */
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

    /**
     * Primary movement method for the player.
     * Depending on the string held within the direction String
     * creates a temporary change to the player position, which is in turn
     * validated via auxiliary method.
     *
     * @param direction Desired Direction from the list of NORTH,SOUTH,WEST,EAST
     * @param game Instance of the game holding the player and the map
     * @return String of the result from the move validation
     */
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

    /**
     * Called after each correct call to the moveKnight method.
     * <p>
     * First, checks if the room to be accessed is unlocked, otherwise
     * resets the player position.
     * <p>
     * Else,
     * Calls a try-catch block with the current player position, if
     * out of bounds exception is met, resets the position to the last known
     * correct position.
     *
     * @param tmpHorizontal Saved last known X correct coordinate value
     * @param tmpVertical Saved last known Y correct coordinate value
     * @param player Instance of the player to be moved
     * @param gameMap Instance of the Map where the player is moving
     * @return String descriptor of the transpired effect
     * @throws ArrayIndexOutOfBoundsException Expected to be triggerred when leaving the game area
     */
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

    /**
     * Checks whether current postion has any items, if so,
     * attempts to pick them up.
     * External call can decide not to pickup item if it is
     * of consumable type and inventory currently has five items in it.
     *
     * @param game instance of the game holding all the required data
     * @return String description of the resultant effects
     */
    public String attemptPickup (Game game) {
        if (game.getGameMap().getCurrentPosition(game.getPlayer().position.horizontal, game.getPlayer().position.vertical).getRoomLoot() == null) {
            return "There is nothing to pickup!";
        }
        else {
            String remember = game.getGameMap().getCurrentPosition(game.getPlayer().position.horizontal, game.getPlayer().position.vertical).getRoomLoot().pickUpEffect(game);
            notifyObserver();
            return remember;
        }
    }

    /**
     * Compares inventory contents against expected
     * name of the item.
     * If name is found, trigger the effect, otherwise
     * just informs the player of name discrepancy.
     *
     * @param input Name of the item given by the player
     * @param game instance of the game holding all the required data
     * @return String description of the resultant effects
     */
    public String useItem (Game game,String input) {

        String toUse = TextHandler.simplifyInput(input);

        for (Consumable consumable : this.inventory) {
            if (toUse.equals(TextHandler.simplifyInput(consumable.getName()))) {
                String remember = consumable.executeConsumableEffect(game,consumable.getConsumableType(), consumable.getEffectiveValue());
                this.inventory.remove(consumable);
                notifyObserver();
                return remember;
            }
        }
        return "No such item was found";

    }

    /**
     * @return Names of the items currently held
     */
    public String presentInventoryContent () {
        StringBuilder toPresent = new StringBuilder("Currently held: \n");
        for (Consumable item : this.inventory ) {
            toPresent.append(item.getName());
            toPresent.append("\n");
        }

        return toPresent.toString();
    }

    /**
     * @return List of spells player can use
     */
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

    /**
     * Basic offensive spell of the player
     * Very cost effective but deals limited damage to stronger creatures.
     *
     * @param game Game instance holding the required data
     * @return String description of the transpired spell effects, differs on circumstances
     */
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


    /**
     * Simple player heal.
     * @return String description of the transpired spell effects, differs on circumstances
     */
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
     * Take note that from technical standpoint this
     * is considered to be offensive spell, and as such cannot be used
     * outside of combat.
     * @param game Game instance holding all of the data
     * @return  String description of the transpired spell effects, differs on circumstances
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
     * Deals massive damage for massive mana cost.
     * @param game Game instance holding all of the data
     * @return  String description of the transpired spell effects, differs on circumstances
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
     * Take note that trying to cast this in
     * 'hostile' rooms will cause miscast.
     * @param game Game instance holding all of the data
     * @return  String description of the transpired spell effects, differs on circumstances
     * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.RoomType
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
     * Take note that trying to cast this in
     * 'hostile' rooms will break cause miscast.
     * @param game Game instance holding all of the data
     * @return  String description of the transpired spell effects, differs on circumstances
     * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.RoomType
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


    /**
     * Signs in knight as observed element.
     * @param observer Class which will observe this class
     */
    @Override
    public void registerObserver(Observer observer) {
        listOfObservers.add(observer);
    }

    /**
     * Triggers the update method for all of the observers.
     * Used to update GUI stat fields.
     */
    private void notifyObserver() {
        for(Observer observer : listOfObservers) {
            observer.updateStatus();
        }
    }


}
