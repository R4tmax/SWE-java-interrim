package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;

public class TheBrute extends Monster implements HostileActions {


    public TheBrute(String name, int health, int damage, int goldDrop) {
        super(name, health, damage, goldDrop);
    }

    @Override
    public String initialMessage() {

        return """          
                  Hideous and terrible creature stands in front of you, it is at least 3 meters tall!
                  It has terrible musculature, and it's skin is so white, you can almost see the sinew and
                  blood vessels underneath.
                  You get the feeling like you are in for a long haul.
               """;
    }

    @Override
    public String attackPattern(Game game) {
        int damageDealt = this.damage;
        game.getPlayer().setCurrentHealth(game.getPlayer().getCurrentHealth()- damageDealt);
        return "You took a massive hit from " + this.name + "\n"
        + "the creature is not bothered by your armor at all!";
    }
}
