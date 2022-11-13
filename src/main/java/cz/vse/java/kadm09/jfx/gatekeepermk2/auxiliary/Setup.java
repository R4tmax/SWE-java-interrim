package cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.GameState;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.Coordinates;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

import java.util.Date;

public class Setup {

    private static final Date todayIs = new Date();

    public static GameState initGameState () {
        return GameState.EXPLORATION;
    }

    public static Map createMap(){
        Map gameMap = new Map();
        gameMap.fillMap();
        return gameMap;
    }

    public static TheKnight createKnight(){
        return new TheKnight(200,50,2,15,250,new Coordinates(4,2),false);
    }

    public static String introMessage () {
        return """
                Welcome to my adventure!
                 Made by Martin Kadlec
                 ©2022 @CoE In Prague
                 This version expands on my previous work from subject 4IT101.
                 It has been rewritten to better incorporate OOP paradigms and
                 incorporates mandatory tasks from 4IT115"""
                + "\n"
                + "\n"
                + "Today is " + todayIs + "\n"
                + "\n"
                + """
                  You are Knight of prestigious monster slaying order,
                  devoted to the King of the realm.
                  You had arrived couple of days ago, and the scouts were hard at work.
                  The threat was detected up north, now it is up to you.
                  Slay the scourge, reclaim the area!
                 """
                + "\n"
                +"""
                  Use the HELP command if you get stuck!
                  Good luck!
                 """
                .indent(1);
    }

}
