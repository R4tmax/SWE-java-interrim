package cz.vse.java.kadm09.jfx.gatekeepermk2.items;

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
     return null;
    }

    @Override
    public void pickUpEffect() {

    }
}
