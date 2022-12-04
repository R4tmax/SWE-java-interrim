package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

/**
 * @author Martin Kadec
 * @version Last refactor on 4.12.2022
 *
 * <p>Subclass of the monster supertype representing the final boss
 * of the game. Starts in a locked room on the other side of the gamemap as
 * opposed to the player, other two monsters need to be defeated first in order
 * to fight the matriarch and end the game. </p>
 *
 * @see Monster
 * @see HostileActions
 * @see TheHag
 * @see TheBrute
 * @see Game#checkGameStatus()
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map
 */
public class TheMatriarch extends Monster implements HostileActions {


    /**
     * Overloaded constructor variant for the Monster class
     * Ignores the golddrop parameter becuase it is not necessary
     * for the flow of the game
     *
     * @param name Name of the creature
     * @param health health value of the creature
     * @param damage Basic damage of the creature before modifers
     */
    public TheMatriarch(String name, int health, int damage) {
        super(name, health, damage);
    }

    /**
     * @return String with a initial message to portray to the player.
     * Currently discontinued.
     */
    @Override
    public String initialMessage() {
        return null;
    }

    @Override
    public String attackPattern(Game game) {
        int damageDealt = this.damage - game.getPlayer().getArmor();
        damageDealt = game.getPlayer().accountForArmor(damageDealt);
        game.getPlayer().setCurrentHealth(game.getPlayer().getCurrentHealth()- damageDealt);
        game.getPlayer().setCurrentHealth(game.getPlayer().getCurrentHealth()- damageDealt);
        return "You were slashed two times in row by " + this.name + "\n" +
                "She is a fierce creature! The total damage received was: " + damageDealt;
    }
}
