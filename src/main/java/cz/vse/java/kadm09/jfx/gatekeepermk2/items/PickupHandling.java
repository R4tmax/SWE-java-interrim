package cz.vse.java.kadm09.jfx.gatekeepermk2.items;

public interface PickupHandling {
    /**
     * Predefined void method intended for
     * printing relevant info to the user,
     *
     * @return
     */
    String pickUpMessage();

    /**
     * Predefined void method intended for
     * handling factual 'picking up'
     * of items in the game world.
     */
    void pickUpEffect();
}
