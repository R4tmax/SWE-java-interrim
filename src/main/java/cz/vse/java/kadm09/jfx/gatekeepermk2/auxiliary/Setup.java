package cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.Coordinates;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

public class Setup {

    public static void initGame () {
        Map.fillMap();
        TheKnight player = new TheKnight(200,50,2,15,250,new Coordinates(4,2),false);
        Map.printPosition(player);

    }

}
