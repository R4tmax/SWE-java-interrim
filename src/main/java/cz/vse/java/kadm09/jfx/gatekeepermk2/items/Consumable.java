package cz.vse.java.kadm09.jfx.gatekeepermk2.items;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

/**
 * @author Martin Kadlec
 * @version Last refactor 4.12.2022.
 *
 * <p>
 *     One of the Item subtypes.
 *     Represents the consumables which the player needs to use actively
 * </p>
 *
 * @see TheKnight
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map
 * @see Item
 * @see PickupHandling
 */
public class Consumable extends Item implements PickupHandling {
    protected ConsumableType consumableType;

    /**
     * Standard constructor for the Item superclass
     * Take note that effective value is interpreted in slightly different
     * meanings depending on the subclass
     *
     * @param name           String representing the Name of the item
     * @param effectiveValue Integer value with numerical value/potency of the item effect (depends on further context)
     */
    public Consumable(String name, int effectiveValue,ConsumableType consumableType) {
        super(name, effectiveValue);
        this.consumableType = consumableType;
    }

    public ConsumableType getConsumableType() {
        return consumableType;
    }

    /**
     * Simple on pickup message.
     * Take note that player is by design given limited info
     * about the items.
     * Although GUI provides strong indicies via the icons.
     * @return Simple message for the player that he has picked up something
     */
    @Override
    public String pickUpMessage() {
        return "You have picked up " + this.name + "!";
    }

    /**
     * Method invoked upon the exploration loot command.
     * Attempts to pickup the existing item a nullifies it afterwards.
     * Method will skip this step if inventory capacity is exceeded.
     *
     * @param game Game instance from which items are taken and where player exists
     * @return String descriptor of the transpired effect
     */
    @Override
    public String pickUpEffect(Game game) {

        if (game.getPlayer().inventory.size() < TheKnight.INVENTORY_CAP) {
            game.getPlayer().inventory.add(this);
            String remember = this.pickUpMessage();
            game.getGameMap().getCurrentPosition(game.getPlayer().getPosition().getHorizontal(),game.getPlayer().getPosition().getVertical()).setRoomLoot(null);
            return remember;
        } else return "Inventory full";

    }

    /**
     * Instantiated version of the original Item handling from the CLI version.
     * Takes the info about the consumable type from the original call and invokes the switch statement.
     * Adds the effective value of the item to the corresponding field.
     * Take note that check functions are invoked for health and mana fills to prevent
     * exceeding expected maximal values.
     *
     * @param game instance of the game from which effect has been triggered.
     * @param type type of the effect to be invoked
     * @param value Integer value representing the change which should transpire.
     * @return string message informing the player of the change that has transpired
     */
    public String executeConsumableEffect (Game game, ConsumableType type , int value) {
        switch (type) {
            case HEALTH_FILL -> {
                game.getPlayer().setCurrentHealth(game.getPlayer().getCurrentHealth() + value);
                game.getPlayer().preventOverheal();
                return "You have been healed for: " + value;
            }
            case MANA_FILL -> {
                game.getPlayer().setCurrentMana(game.getPlayer().getCurrentMana() + value);
                game.getPlayer().preventOvercast();
                return "Your mana pool has been replenished by: " + value;
            }
            case DAMAGE_BOOST -> {
                game.getPlayer().setDamage(game.getPlayer().getDamage() + value);
                return "Your damage has been increased by: " + value;
            }
            case ARMOR_BOOST -> {
                game.getPlayer().setArmor(game.getPlayer().getArmor() + value);
                return "Your armor level has been increased by " + value;
            }
        }
        return "";
    }

    /**
     * Simplified toString call, used by the ListView in the GUI.
     * @return Name of the consumable
     */
    @Override
    public String toString() {
        return super.name;
    }

}
