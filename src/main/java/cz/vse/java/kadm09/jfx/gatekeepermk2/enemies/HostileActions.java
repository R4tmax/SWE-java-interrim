package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

public interface HostileActions {
    /**
     * Initial declaration for the initialMessage
     * method of the interface
     */
    String initialMessage();

    /**
     *
     * Initial declaration for the attackPattern
     * method of the interface
     *
     * @param damageValue Integer value representing calculated
     *                   base damage modifiers passed by the combat Class
     */
    void attackPattern(int damageValue);



}
