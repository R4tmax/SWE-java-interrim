package cz.vse.java.kadm09.jfx.gatekeepermk2.items;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

/**
 * @author Martin Kadlec
 * @version Last refactor on 4.12.2022
 *
 * <p>
 *     Interface providing basic template structure for
 *     Item and its inheritors.
 * </p>
 *
 * @see Item
 */
public interface PickupHandling {
    /**
     * Predefined void method intended for
     * printing relevant info to the user,
     *
     * @return String to present to the player
     */
    String pickUpMessage();

    /**
     * Predefined void method intended for
     * handling factual 'picking up'
     * of items in the game world.
     *
     * @return String to present to the player
     */
    String pickUpEffect(Game game);
}
