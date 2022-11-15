package cz.vse.java.kadm09.jfx.gatekeepermk2.items;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

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

    @Override
    public String pickUpMessage() {
     return "You found: " + this.name + ". This will make you a richer man." + "\n" +
            "Value of the find is: " + this.effectiveValue + " gold pieces.";
    }

    @Override
    public String pickUpEffect(Game game) {
        game.getPlayer().setGoldHeld(game.getPlayer().getGoldHeld()+ this.effectiveValue);
        String remember = this.pickUpMessage();
        game.getGameMap().getCurrentPosition(game.getPlayer().getPosition().getHorizontal(),game.getPlayer().getPosition().getVertical()).setRoomLoot(null);
        return remember + "\n" + "You now have: " + game.getPlayer().getGoldHeld() + " gold pieces!";
    }
}
