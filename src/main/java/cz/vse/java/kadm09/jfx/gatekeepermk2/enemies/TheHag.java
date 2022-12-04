package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

/**
 * @author Martin Kadlec
 * @version Last refactor on 4.12.2022
 *
 * <p>Sub class of the Monster abstraction
 * One of the minor enemies in the game.
 * See individual methods for further reference</p>
 *
 * @see Monster
 * @see HostileActions
 */
public class TheHag extends Monster implements HostileActions {

    /**
     * Standard constructor variant for the Monster class
     *
     * @param name String - Name of the creature
     * @param health Integer - health value of the creature
     * @param damage Integer - Basic damage of the creature before modifiers
     * @param goldDrop Integer - Amount of gold player will receive after defeating the monster
     */
    public TheHag(String name, int health, int damage, int goldDrop) {
        super(name, health, damage, goldDrop);
    }

    /**
     * @return String with a initial message to portray to the player.
     * Currently discontinued.
     */
    @Override
    public String initialMessage() {
        return """
                    The feeling you had around here. The dense air and shivers?
                    It is so much stronger here, you almost feel dizzy.
                    Suddenly, you notice movement in nearby reeds.
                    A small, but very much so disgusting womanlike creature stands in front of you.
                    Your instinct tells you, that if you want to cast a spell, you should do it NOW.
                """;
    }

    /**
     * The hag deals its damage twice, first to the health pool and then to the mana pool.
     * As such, hag interferes with the player ability to cast spells, but can be virtually
     * one shot with the most powerful spell in the game. Player can protect himself from the hag attacks
     * by investing in armour.
     *
     * @param game Game instance currently holding the player and the monster
     * @return String with combat resolution output
     *
     * @see cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight#accountForArmor(int)
     */
    @Override
    public String attackPattern(Game game) {
        int damageDealt = this.damage - game.getPlayer().getArmor();
        damageDealt = game.getPlayer().accountForArmor(damageDealt);
        game.getPlayer().setCurrentHealth(game.getPlayer().getCurrentHealth()- damageDealt);
        game.getPlayer().setCurrentMana(game.getPlayer().getCurrentMana() - damageDealt);
        return "You were hit by " + this.name + "for " + damageDealt + " points of damage " + "\n" +
                "Hag deals the same damage to your mana as well!";
    }
}
