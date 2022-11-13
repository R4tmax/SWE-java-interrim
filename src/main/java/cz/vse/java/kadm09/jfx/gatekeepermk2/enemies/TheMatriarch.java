package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

public class TheMatriarch extends Monster implements HostileActions {


    public TheMatriarch(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public String initialMessage() {
        return null;
    }

    @Override
    public void attackPattern(int damageValue) {

    }
}
