package cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld;

public class Map {
    private static final Room [][] gameArea = new Room[5][5];

    public static Room getCurrentPosition(int x, int y) {
        return gameArea[x][y];
    }

}
