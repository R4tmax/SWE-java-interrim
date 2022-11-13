package cz.vse.java.kadm09.jfx.gatekeepermk2.items;

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
