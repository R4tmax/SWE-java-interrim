package cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.Coordinates;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

public class Setup {

    public static void initGame () {
        Map gameMap = new Map();
        gameMap.fillMap();
        TheKnight player = new TheKnight(200,50,2,15,250,new Coordinates(4,2),false);
        //System.out.println(gameMap.printPosition(player));

    }

    public static Map createMap(){
        Map gameMap = new Map();
        gameMap.fillMap();
        return gameMap;
    }

    public static TheKnight createKnight(){
        TheKnight player = new TheKnight(200,50,2,15,250,new Coordinates(4,2),false);
        return player;
    }

}
