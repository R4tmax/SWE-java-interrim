package cz.vse.java.kadm09.jfx.gatekeepermk2.knight;

/**
 * @author Martin Kadlec
 * @version Last refactor on 4.12.2022
 *
 * <p>
 *     Intermediary class between Knight and Map
 *     Classes.
 *     Provides two integer values representing X and Y axis
 *     within the 2D array representing the gameworld.
 * </p>
 *
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map
 * @see TheKnight
 */
public class Coordinates {

    protected int horizontal;
    protected int vertical;

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    /**
     * Standard constructor for the class.
     * By design is called once on build time by TheKnight.
     *
     * @param horizontal Integer representing the X coordinate
     * @param vertical Integer representing the Y coordinate
     */
    public Coordinates(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

}
