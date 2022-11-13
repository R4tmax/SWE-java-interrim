package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

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
    public void attackPattern(int damageValue) {

    }
}
