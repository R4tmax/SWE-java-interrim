package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary.Setup;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.GameState.ENDGAME;
import static cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.GameState.EXPLORATION;

/**
 * @author Martin Kadlec
 * @version Last refactor on 4.12.2022
 *
 * <p> Interconnecting class for the various game components and info.
 * Please check required classes for further reference.
 * Special explanation for some of the fields:
 *     INITIATIVE - boolean flag turned false when in combat, ensures that game returns
 *     to correct gamestate when switching between menus
 * </p>
 *
 * @see GameState
 * @see TheKnight
 * @see Map
 * @see Setup
 */
public class Game implements ObservedElement{

    public boolean initiative = true;
    protected Enum <GameState> gameState;
    protected TheKnight player;
    protected Map gameMap;

    private int huntsmanIterator = 1;
    private int armorsmithIterator = 1;
    private final int upgradePrice = 125;
    private int dialogueIterator = 0;
    protected final ArrayList<String> dialogueScout  = new ArrayList<>();
    protected boolean endgame = false;

    public Set<Observer> listOfObservers = new HashSet<>();


    /**
     * Standard game constructor.
     * Calls auxiliary setup methods to created individual instances.
     * @see Setup#initializeDialogues(Game)
     */
    public Game() {
        this.gameState = Setup.initGameState();
        this.player = Setup.createKnight();
        this.gameMap = Setup.createMap();
        Setup.initializeDialogues(this);
    }

    public TheKnight getPlayer() {
        return player;
    }

    public void setGameState(Enum<GameState> gameState) {
        this.gameState = gameState;
        notifyObserver();
    }

    public Map getGameMap() {
        return gameMap;
    }

    public ArrayList<String> getDialogueScout() {
        return dialogueScout;
    }

    /**
     * Called by the Controller methods between user inputs to prevent
     * game reaching undesirable states. Validates player health and mana values.
     * Resets the initiative flag if player is no longer in combat.
     * <p>
     * If hostile health has been depleted, nullifies the instances and triggers the kill postconditions.
     * (Gold drop, gamemap modifications)
     * </p>
     * @return String with current position description upon successful method completion
     *
     * @see Game#updateDescriptors()
     */
    public String checkGameStatus () {

        if (this.getPlayer().getCurrentHealth() <= 0 ) this.getPlayer().setDead(true);
        if (this.getPlayer().getCurrentMana() <= 0) this.getPlayer().setCurrentMana(0);
        if (this.getGameMap().getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).getRoomEnemy() == null)
            this.initiative = true;

        if (this.getGameMap().getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).getRoomEnemy() != null
        && this.getGameMap().getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).getRoomEnemy().getHealth() <= 0 ) {
            this.player.setGoldHeld(this.getPlayer().getGoldHeld() + this.gameMap.getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).getRoomEnemy().getGoldDrop());
            this.getGameMap().getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).setRoomEnemy(null);
            this.updateDescriptors();
            this.gameState = EXPLORATION;
            this.initiative = true;
            return this.gameMap.getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).getDescription();
        }

        return "";
    }

    /**
     * Called when enemy health has been reduced to or below zero.
     * Runs IF checks for which enemies are still alive and updates room descriptions as necessary.
     * Unlocks the boss room if needed.
     * Sets the endgame condition if final boss has been killed.
     *
     */
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

        if (this.gameMap.getCurrentPosition(2,0).getRoomEnemy() == null && this.gameMap.getCurrentPosition(2,4).getRoomEnemy() == null)
            this.gameMap.getCurrentPosition(0,2).setLocked(false);


        if (this.gameMap.getCurrentPosition(0,2).getRoomEnemy() == null) {
            this.endgame = true;
            this.setGameState(ENDGAME);
        }

    }

    /**
     * Iterates through the dialogue array everytime player
     * interacts with the spawn room. Check is performed after
     * each call to reset iterator back to 0. Dialogue with the scout
     * therefore can be repeated indefinitely.
     *
     * @return String with game hints for the player
     */
    public String callScout () {
        String toReturn = dialogueScout.get(dialogueIterator);
        dialogueIterator++;
        if (dialogueIterator>=dialogueScout.size()) dialogueIterator = 0;
        return toReturn;
    }

    /**
     * Called via the command list when the player agrees to rest.
     * Unlike the marketplace, rest price remains constant no matter the amount
     * of rests.
     * Resting resets health and mana of the player to the max values, it also
     * increases damage a bit.
     *
     * @return String with a description of the interaction resolution
     */
    public String rest() {

        int restPrice = 300;
        if(this.getPlayer().getGoldHeld() < restPrice) return "You do not have enough money!";

        this.player.setGoldHeld(this.player.getGoldHeld()- restPrice);
        this.player.setCurrentHealth(TheKnight.MAX_HEALTH);
        this.player.setCurrentMana(TheKnight.MAX_MANA);
        this.player.setDamage(this.player.getDamage() + 2);

        return "You are feeling well rested and prepared to fight!";
    }

    /**
     * Called by the command list when player agrees to trade with the huntsman.
     * Runs a simple calculation via the respective iterator to determine the expected price.
     * Declines the trade if the player does not have sufficient funds.
     *
     * @return String with a description of the event and updated damage number
     */
    public String improveWeapons() {
        int calcPrice = huntsmanIterator * upgradePrice;
        if(this.getPlayer().getGoldHeld() < calcPrice) return "You do not have enough money! Huntsman currently needs: " + calcPrice;

        this.player.setGoldHeld(this.player.getGoldHeld() - calcPrice);
        huntsmanIterator++;
        this.player.setDamage(this.player.getDamage() + 5);
        return "Your weapons have been upgraded, you currently deal: " + this.player.getDamage() + "Damage!";
    }

    public String improveArmor() {
        int calcPrice = armorsmithIterator * upgradePrice;
        if(this.getPlayer().getGoldHeld() < calcPrice) return "You do not have enough money! Armorsmith currently needs: " + calcPrice;

        this.player.setGoldHeld(this.player.getGoldHeld() - calcPrice);
        armorsmithIterator++;
        this.player.setArmor(this.player.getArmor() + 2);
        return "Your armor has been refitted, you now have: " + this.player.getArmor() + "Armor!";
    }

    public String strikeEnemy() {
         this.getGameMap().getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).getRoomEnemy().setHealth(this.getGameMap().getCurrentPosition(this.player.getPosition().getHorizontal(),this.player.getPosition().getVertical()).getRoomEnemy().getHealth() - this.player.getDamage());
         return "You hit your enemy for: " + this.player.getDamage();
    }

    @Override
    public void registerObserver(Observer observer) {
        listOfObservers.add(observer);
    }

    private void notifyObserver() {
        for(Observer observer : listOfObservers) {
            observer.updateStatus();
        }
    }
}
