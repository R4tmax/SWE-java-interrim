package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

/**
 * @author Martin Kadlec
 * @version Last Refactor on 3.12.2022
 *
 * <p>
 *      Subtype of the Monster class
 *      represents on of the two basic enemies player can encounter.
 * </p>
 * @see Monster
 * @see HostileActions
 */
public class TheBrute extends Monster implements HostileActions {


    /**
     * Standard constructor variant for the Monster class
     *
     * @param name String - Name of the creature
     * @param health Integer - health value of the creature
     * @param damage Integer - Basic damage of the creature before modifiers
     * @param goldDrop Integer - Amount of gold player will receive after defeating the monster
     */
    public TheBrute(String name, int health, int damage, int goldDrop) {
        super(name, health, damage, goldDrop);
    }

    /**
     * @return String with a initial message to portray to the player.
     * Currently discontinued.
     */
    @Override
    public String initialMessage() {

        return """          
                  Hideous and terrible creature stands in front of you, it is at least 3 meters tall!
                  It has terrible musculature, and it's skin is so white, you can almost see the sinew and
                  blood vessels underneath.
                  You get the feeling like you are in for a long haul.
               """;
    }

    /**
     * In the case of the Brute, simply apply its damage to the player,
     * as the brute ignores armor.
     * Take not that in the original version Brute attack pattern was different.
     *
     * @param game Game instance which called the method and holds the player data.
     * @return String description of how much damage has been dealt to the player and how the monster bahves.
     */
    @Override
    public String attackPattern(Game game) {
        int damageDealt = this.damage;
        game.getPlayer().setCurrentHealth(game.getPlayer().getCurrentHealth()- damageDealt);
        return "You took a massive hit from " + this.name +  "for " + damageDealt + "\n"
        + "the creature is not bothered by your armor at all!";
    }
}
