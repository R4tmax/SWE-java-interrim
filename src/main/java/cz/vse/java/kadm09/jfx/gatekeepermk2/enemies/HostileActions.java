package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

/**
 * @author Martin Kadlec
 * @version Last refactor on 3.12.2022
 *
 * <p>
 *     Interface implemented by the Monster class
 *     providing template for the individual
 *     monster types to follow.
 *     Take note that during the rewriting of the game logic
 *     original initial message has been discontinued in favor of
 *     changes to the room descriptor logic.
 * </p>
 *
 * @see Monster
 */
public interface HostileActions {
    /**
     * Initial declaration for the initialMessage
     * method of the interface
     */
    String initialMessage();

    /**
     * Initial declaration for the attackPattern
     * method of the interface
     *
     * @return
     */
    String attackPattern(Game game);



}
