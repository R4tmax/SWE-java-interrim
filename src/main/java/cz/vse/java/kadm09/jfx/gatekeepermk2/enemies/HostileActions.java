package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

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
