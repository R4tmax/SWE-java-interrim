package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

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
                    Your instinct tells you, if you feel the need to cast a spell, you should do it NOW.
                """;
    }

    @Override
    public void attackPattern(int damageValue) {

    }
}
