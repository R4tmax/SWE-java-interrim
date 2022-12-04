package cz.vse.java.kadm09.jfx.gatekeepermk2.items;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

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

    @Override
    public String pickUpMessage() {
        return "You have picked up " + this.name + "!";
    }

    @Override
    public String pickUpEffect(Game game) {

        if (game.getPlayer().inventory.size() < TheKnight.INVENTORY_CAP) {
            game.getPlayer().inventory.add(this);
            String remember = this.pickUpMessage();
            game.getGameMap().getCurrentPosition(game.getPlayer().getPosition().getHorizontal(),game.getPlayer().getPosition().getVertical()).setRoomLoot(null);
            return remember;
        } else return "Inventory full";

    }



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

    @Override
    public String toString() {
        return super.name;
    }

}
