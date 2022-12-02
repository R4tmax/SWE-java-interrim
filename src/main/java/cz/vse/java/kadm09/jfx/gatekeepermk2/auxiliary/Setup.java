package cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.GameState;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.Coordinates;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

import java.util.Date;

/**
 * @author Martin Kadlec
 * @version Last refactor on 3.12.2022
 *
 * <p>
 *     Auxiliary class responsible for handling the universal
 *     load game operations.
 * <p>
 *     Take note that GUI version relies on specific calls
 *     to the Loader class and GUI Controller initialize methods
 * </p>
 *
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Main
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.GUIController
 * @see Game
 */
public class Setup {

    private static final Date todayIs = new Date();

    public static GameState initGameState () {
        return GameState.EXPLORATION;
    }

    /**
     * Simple constructor extender of the Map class
     * @see Map
     * @return gameMap - Instance of the Map class with "default" game state.
     */
    public static Map createMap(){
        Map gameMap = new Map();
        gameMap.fillMap();
        return gameMap;
    }

    /**
     * Redirected call to the Knight Constructor
     * @return TheKnight - returns the instance of the player character with the default stat values.
     */
    public static TheKnight createKnight(){
        return new TheKnight(TheKnight.MAX_HEALTH,TheKnight.MAX_MANA,4,30,500,new Coordinates(4,2),false);
    }

    /**
     * @return Returns a String block to be printed upon game launch.
     */
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


    /**
     * Fills the dialogue field of the Game param with the individual text lines
     * which can be called by the player upon interacting with the spawn room.
     * @param game Game instance which is currently being constructed
     */
    public static void initializeDialogues(Game game) {
        game.getDialogueScout().add("Hail, Knight of the King! *He hits his chest-plate with his right arm*");
        game.getDialogueScout().add("""
                       We are in the southernmost part of the area.
                       All of the monsters should be up north.
                       """);
        game.getDialogueScout().add("""
                        Monsters will be fast, you better be ready to fight to the end, once you find them.
                        (You cannot escape combat, once it begins.)
                        """);
        game.getDialogueScout().add("""
                        Monster attacks disrupted a lot of trade to the west.
                        You might find useful items in that direction.
                        """);
        game.getDialogueScout().add("""
                        Whatever was there, was big.
                        It had to take out well guarded convoys.
                        Be sure to not engage, whatever it is, until you gathered some strength!
                        """);
        game.getDialogueScout().add("""
                        If you are uncertain, head east first.
                        Something feels wrong with that area, but
                        no attacks were reported there.
                        """);
        game.getDialogueScout().add("""
                        Be sure the familiarize yourself with your spell list.
                        You don't want to make a mistake in the heat of the moment.
                        If you cast the wrong spell at the wrong time, the tables might turn
                        very dramatically!
                        """);
        game.getDialogueScout().add("""
                        You could always just avoid combat and survey the area first, you might find resources to salvage.
                        There are some merchants due east, which might help you with that.
                        """);
        game.getDialogueScout().add("One last thing. Ecclesiastic chaplains prepared this blessed concoction for your quest, good luck!");
    }

}
