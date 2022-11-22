package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

public class TheMatriarch extends Monster implements HostileActions {


    public TheMatriarch(String name, int health, int damage) {
        super(name, health, damage);
    }

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
