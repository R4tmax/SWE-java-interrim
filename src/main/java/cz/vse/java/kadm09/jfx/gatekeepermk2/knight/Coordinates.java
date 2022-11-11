package cz.vse.java.kadm09.jfx.gatekeepermk2.knight;

public class Coordinates {


    protected int horizontal;
    protected int vertical;

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
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
