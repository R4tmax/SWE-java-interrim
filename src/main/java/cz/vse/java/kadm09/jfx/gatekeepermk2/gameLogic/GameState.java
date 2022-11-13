package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

public enum GameState {


    /**
     * Default game state, player is standing on a map
     * and "not doing anything.
     *
     * @see Commands
     */
    EXPLORATION,

    /**
     * Game expects directional input for the move command
     * @see cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight#moveKnight(String, TheKnight, Map)
     */
    MOVEMENT

}
