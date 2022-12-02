package cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

/**
 * @author Martin Kadlec
 * @version Last refactor on 3.12.2022
 *
 * <p>
 *     Super class for the game enemies.
 *     Holds the overloaded constructors.
 * </p>
 *
 * @see TheBrute
 * @see TheMatriarch
 * @see TheHag
 */
public abstract class Monster implements HostileActions{
    protected String name;
    protected int health;
    protected int damage;

    protected int goldDrop;

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getGoldDrop() {
        return goldDrop;
    }


    /**
     * Simplified supertype constructor for the
     * Monster class. This constructor does not
     * assign value to goldDrop parameter as it is used in cases
     * where no gold drop is expected (i.e. final fight).
     * Take note that attribute 'isDead' is by
     * design always instantiated as false, and hence is not
     * set by the constructor.
     *
     * @param name String representing the name of the monster
     * @param health Integer value representing functional health of the monster
     * @param damage Integer value representing base dealt by the monster (before modifiers are applied)
     *
     * @see TheMatriarch
     */
    public Monster(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    /**
     * Standard constructor for the Monster superclass.
     * Constructor overloads the simplified constructor for use
     * in cases where it is desirable or expected to give
     * bounty to the player upon successful resolution of the combat.
     * Take note that isDead attribute is by design always
     * instantiated as 'false' and hence is not invoked by the constructor.
     *
     * @param name String representing the name of the monster
     * @param health Integer value representing functional health of the monster
     * @param damage Integer value representing base damage dealt by the monster (before modifiers are applied)
     * @param goldDrop Integer value representing the bounty player will be given after defeating the monster.
     *
     * @see TheBrute
     * @see TheHag
     */
    public Monster(String name, int health, int damage, int goldDrop) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.goldDrop = goldDrop;
    }

}
