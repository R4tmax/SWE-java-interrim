package cz.vse.java.kadm09.jfx.gatekeepermk2.items;

/**
 * @author Martin Kadlec
 * @version Last refactor on 4.12.2022
 *
 * <p>
 *     Item superclass of the game, holds the basic and common data
 *     of the items.
 *     Take note that MoneyLoot uses the fields of the superclass as they are.
 *     While consumables rely on overriding and adding the class.
 * </p>
 *
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map
 * @see MoneyLoot
 * @see Consumable
 */
public abstract class Item implements PickupHandling{
    protected String name;
    protected int effectiveValue;

    /**
     * Standard constructor for the Item superclass
     * Take note that effective value is interpreted in slightly different
     * meanings depending on the subclass
     *
     * @param name String representing the Name of the item
     * @param effectiveValue Integer value with numerical value/potency of the item effect (depends on further context)
     */
    public Item(String name, int effectiveValue) {
        this.name = name;
        this.effectiveValue = effectiveValue;
    }

    public String getName() {
        return name;
    }

    public int getEffectiveValue() {
        return effectiveValue;
    }
}
