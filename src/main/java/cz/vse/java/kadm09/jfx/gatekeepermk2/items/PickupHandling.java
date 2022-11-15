package cz.vse.java.kadm09.jfx.gatekeepermk2.items;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

public interface PickupHandling {
    /**
     * Predefined void method intended for
     * printing relevant info to the user,
     *
     * @return
     */
    String pickUpMessage();

    /**
     * Predefined void method intended for
     * handling factual 'picking up'
     * of items in the game world.
     *
     * @return
     */
    String pickUpEffect(Game game);
}
