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
    MOVEMENT,
    COMBAT,
    /**
     * Game is expecting the name of the item to be used as an input.
     */
    INVENTORY, //TODO: , potentially problematic during combat
    /**
     * Player is shoping for armor upgrades
     */
    ARMORSMITH,
    /**
     * Player is shopping for weapon upgrades
     */
    HUNTSMAN,
    SPELLCAST, //todo: potentially problematic in relation to combat, might need to rework string processing logic for this command
    ENDGAME




}
