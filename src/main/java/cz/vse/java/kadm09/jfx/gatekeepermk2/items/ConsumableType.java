package cz.vse.java.kadm09.jfx.gatekeepermk2.items;

/**
 * @author Martin Kadlec
 * @version Last refactored on 11.06.2022.
 *
 * <p>
 *     ENUM used to further differentiate effective value
 *     use case interpretation. See Consumable class for more info.
 * </p>
 *
 * @see Consumable
 */
public enum ConsumableType {
    /**
     * HEALTH_FILL items will add the effective value
     * to the TheKnight health pool.
     * Take note that further steps are called in this
     * use case to prevent undesirable states.
     *
     * @see cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight
     */
    HEALTH_FILL,
    /**
     * MANA_FILL items will add the effective value
     * to the TheKnight mana pool.
     * Take note that further steps are called in this
     * use case to prevent undesirable states.
     *
     * @see cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight
     */
    MANA_FILL,
    /**
     * ARMOR_BOOST items will add the effective value
     * to the TheKnight armor stat permanently.
     *
     * @see cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight
     */
    ARMOR_BOOST,
    /**
     * DAMAGE_BOOST items will add the effective value
     * to the TheKnight damage stat permanently.
     *
     * @see cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight
     */
    DAMAGE_BOOST

}
