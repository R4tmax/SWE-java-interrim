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

    public void checkGameStatus () {

        if (this.getPlayer().getCurrentHealth() <= 0 ) this.getPlayer().setDead(true);
        if (this.getPlayer().getCurrentMana() <= 0) this.getPlayer().setCurrentMana(0);

        if (this.getGameMap().getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).getRoomEnemy() != null
        && this.getGameMap().getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).getRoomEnemy().getHealth() <= 0 ) {
            this.player.setGoldHeld(this.getPlayer().getGoldHeld() + this.gameMap.getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).getRoomEnemy().getGoldDrop());
            this.getGameMap().getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).setRoomEnemy(null);
            this.updateDescriptors();
        }
    }
    private void updateDescriptors () {

        if (this.gameMap.getCurrentPosition(2,0).getRoomEnemy() == null) {
            this.gameMap.getCurrentPosition(2,1).setDescription("""
                It feels, like someone lives nearby. You notice that a lot of the bushes and small trees
                in the area have damaged bark and branches, as if something regularly smashed through here.
                As you head west, the damage gets more and more pronounced.
                You already figured that out, of course, and put an end to it.
                Dead gem merchant is still lying dead in one of the bushes,
                You wonder, if you already checked his pockets.
                """);

            this.gameMap.getCurrentPosition(1,0).setDescription("""
                You walk amongst the thinly space trees and admire the view to the west.
                You cannot quite get there, as a narrow cliff block your way. But you see distant cities
                and castles on the horizon. Places where you come from and for which you fight.
                Memory of bloody encounter runs across your cortex, as you look south.
                Up north, hills rise up, while eastern view is blocked by the trees.
                Blue herbs are growing here.
                """);

            this.gameMap.getCurrentPosition(3,0).setDescription("""
                Same as along the Kings Road, which lies to your south now.
                You rest easy looking north, knowing you slain the beast.
                However desolate area might look.
                You can still make out village buildings to the east.
                """);

            this.gameMap.getCurrentPosition(2,0).setDescription("""
                Phew, the monstrosity is dead.
                You have little idea what that thing was. But you are glad you made it out.
                The creature, even dead, looks menacing and out of this world. It still could be, for all you know.
                The Kings Road is safe now, for the time being.
                You get your bearings. Road is due South, and forests lie north-east.
                You notice that the way west would take you over a cliff, the drop is too high to brave.
                """);
        }


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
