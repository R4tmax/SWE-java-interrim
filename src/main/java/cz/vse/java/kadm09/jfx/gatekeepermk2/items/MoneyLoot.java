package cz.vse.java.kadm09.jfx.gatekeepermk2.items;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

/**
 * @author Martin Kadlec
 * @version Last refactor on 4.12.2022
 *
 * <p>
 *     One of the item subtypes.
 *     Upon picking up its effective value is
 *     simply added to the player gold pool.
 * </p>
 *
 * @see Item
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map
 */
public class MoneyLoot extends Item implements PickupHandling{


    /**
     * Standard constructor for the Item superclass
     * Take note that effective value is interpreted in slightly different
     * meanings depending on the subclass
     *
     * @param name           String representing the Name of the item
     * @param effectiveValue Integer value with numerical value/potency of the item effect (depends on further context)
     */
    public MoneyLoot(String name, int effectiveValue) {
        super(name, effectiveValue);
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
     return "You found: " + this.name + ". This will make you a richer man." + "\n" +
            "Value of the find is: " + this.effectiveValue + " gold pieces.";
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
        game.getPlayer().setGoldHeld(game.getPlayer().getGoldHeld()+ this.effectiveValue);
        String remember = this.pickUpMessage();
        game.getGameMap().getCurrentPosition(game.getPlayer().getPosition().getHorizontal(),game.getPlayer().getPosition().getVertical()).setRoomLoot(null);
        return remember + "\n" + "You now have: " + game.getPlayer().getGoldHeld() + " gold pieces!";
    }
}
