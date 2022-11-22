package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary.Setup;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

public class Game {

    public boolean initiative = true;
    protected Enum <GameState> gameState;
    protected TheKnight player;
    protected Map gameMap;


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

    public void updateDescriptors () {

        if (this.gameMap.getCurrentPosition(2,4).getRoomEnemy() == null) {
            this.gameMap.getCurrentPosition(2,3).setDescription("""
                You made your way to what seems to be some sort of a bog.
                Short colorful flowers are scattered everywhere, but soil gives way under your weight.
                After some deliberation you find a path through all of it. But you are not exactly comfortable.
                You already know, that a dead hag lies in the march to the east.
                Fortunately the bog gives way in all other directions.
                 """);

            this.gameMap.getCurrentPosition(1,4).setDescription("""
                This place looks almost surreal. You see a huge marsh opening to east and south.
                While mountain range looms to the north. Western direction is saddled by trees and bushes.
                Flies and mosquitoes constantly annoy you. Looking south fills your with dread,
                as memory of fight with The Hag returns to your visual cortex.
                 """);

            this.gameMap.getCurrentPosition(3,4).setDescription("""
                As southern fields disappear under the horizon, you start walking up north, with the river to your right.
                Even though the hag lies dead, the air is still heavy, weird.
                You notice now more clearly, that the path west is steadily declining.
                 """);

            this.gameMap.getCurrentPosition(2,4).setDescription("""
                Creature which you decided to dub 'The Hag' lies dead at your feet.
                It almost looks like the ground began to reclaim the body.
                Something tells you, that the corpse won't be here come next week.
                You cannot see anything from where you are standing, aside from the fact,
                that swamp is impassable due east, you need to head somewhere else.
                As you look around one last time, some herbs catch your attention.
                """);
        }

    }
}
