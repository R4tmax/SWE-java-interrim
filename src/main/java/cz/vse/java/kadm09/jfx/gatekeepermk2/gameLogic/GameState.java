package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

/**
 * @author Martin Kadlec
 * @version Last Refactor on 4.12.2022
 *
 * <p>
 *     ENUM holding all of the possible gamestates
 *     , used to filter available commands for the player.
 * </p>
 */
public enum GameState {


    /**
     * Default game state, player is standing on a map
     * and "not doing anything special".
     * Game reverts to this state if no better alternative is present
     * and initiative flag of the game instance is true.
     *
     * @see Commands
     */
    EXPLORATION,
    /**
     * Game expects directional input for the move command
     * @see TheKnight#moveKnight(String, Game);
     */
    MOVEMENT,
    /**
     * Player is currently engaged in combat and hence cannot move.
     * Take note that due to the MVC compliance there is no longer
     * a dedicated combat method and all of the calls and validations
     * need to be handled externally
     */
    COMBAT,
    /**
     * Game is expecting the name of the item to be used as an input.
     */
    INVENTORY,
    /**
     * Player is currently visiting the market and is given a choice
     * of merchants
     */
    MARKET,
    /**
     * Player is shopping for armor upgrades
     */
    ARMORSMITH,
    /**
     * Player is shopping for weapon upgrades
     */
    HUNTSMAN,
    /**
     * Player is currently being given a choice of whether he wants
     * to rest or not.
     */
    INN,
    /**
     * Player is currently expected to provide name of the spell as input.
     */
    SPELLCAST,
    /**
     * Player has successfully defeated the final boss
     * and the game stops all input unless specified otherwise.
     */
    ENDGAME,
    /**
     * Player has died and no further actions can be taken until the game is reset.
     */
    DEATH




}
