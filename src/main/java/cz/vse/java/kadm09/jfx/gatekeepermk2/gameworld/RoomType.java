package cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld;

/**
 * @author Martin Kadlec
 * @version Last Refactor on 4.12.2022
 *
 * <p>
 *     ENUM for the roomTypes of the individual rooms.
 *     Serves as an branching attribute for the interaction command.
 *     Take note that in the original CLI version this used a separate method
 *     Currently this call is made directly by the command class from the command list.
 * </p>
 *
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Commands
 * @see Room
 */
public enum RoomType {
    /**
     * RECON represents 'stock'
     * interaction option and has no gameplay effect.
     */
    RECON,
    /**
     * TALKABLE represents Scout positioning in the world.
     * Scout acts as a tutorial or a guide for a player,
     * giving basic info and tips for the game.
     */
    TALKABLE,
    /**
     * TRADEABLE represents the market position in the world.
     * Players can upgrade their gear at the market.
     */
    TRADABLE,
    /**
     * HOSTILE is used for the rooms containing enemies, interacting here prints
     * flavor text for the player.
     */
    HOSTILE,
    /**
     * REST_AREA represents the Tavern position in the world.
     * Player can refill their resource pools here, and gain
     * minor damage benefit, for a cost.
     */
    REST_AREA

}
