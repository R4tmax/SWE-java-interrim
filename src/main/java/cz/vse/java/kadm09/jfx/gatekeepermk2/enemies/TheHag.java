package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

public class TheHag extends Monster implements HostileActions {
    public TheHag(String name, int health, int damage, int goldDrop) {
        super(name, health, damage, goldDrop);
    }

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
