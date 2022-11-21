package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary.Setup;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

public class Game {

    protected Enum <GameState> gameState;
    protected TheKnight player;
    protected Map gameMap;

    protected int turnCounter = 0;


    public Game() {
        this.gameState = Setup.initGameState();
        this.player = Setup.createKnight();
        this.gameMap = Setup.createMap();
    }

    public TheKnight getPlayer() {
        return player;
    }

    public Map getGameMap() {
        return gameMap;
    }


}
