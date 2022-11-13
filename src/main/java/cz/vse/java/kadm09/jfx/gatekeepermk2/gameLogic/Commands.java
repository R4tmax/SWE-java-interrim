package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

import java.util.Scanner;
import static cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.GameState.*;


/**
 * @author Martin Kadlec
 * @version 1.0.0
 *
 * Public class which handles the command processing
 * for both CLI and GUI version of the game.
 */
public class Commands {

    public static String processInput (TheKnight player, Map gameMap, GameState gameState) {
        Scanner input = new Scanner(System.in);
        System.out.println(" > ");
        String enteredText = input.nextLine();
        return commandList(enteredText.toLowerCase(), player, gameMap, gameState);
    }

    public static String commandList (String input, TheKnight player,Map gameMap, GameState gameState ) {

        if (gameState == EXPLORATION) {
                switch (input) {
                    case "help" -> {
                        return player.presentCommandListExploration();
                    }
                    case "move" -> {
                        gameState=MOVEMENT;
                        return "In which direction do you want to move?";
                    }
                    case "lookaround" -> {
                        return gameMap.presentPosition(player);
                    }
                    case "status" -> {
                        return player.presentKnightStatusExploration();
                    }
                    case "testobserver" -> {
                        player.setCurrentHealth(player.getCurrentHealth() - 5);
                        player.setCurrentMana(player.getCurrentMana() - 5);
                        player.setDamage(player.getDamage() - 1);
                        player.setArmor(player.getArmor() - 1);
                        player.setGoldHeld(player.getGoldHeld() - 5);
                        return String.valueOf(player.getCurrentHealth());
                    }
                    //case "loot" -> Item.attemptPickup();
                    //case "showinventory" -> TheKnight.printInventoryContent();
                    //case "useitem" -> TheKnight.useItem(input);
                    //case "spelllist" -> Spells.printSpelllist();
                    //case "cast" -> Spells.castSpells(input);
                    //case "interact" -> Interactions.attemptInteraction(input);

                    case "quitgame" -> System.exit(0);
                    default -> {
                        return ("Unknown command, use HELP command if you are lost.");
                    }
                }
            } else if (gameState==MOVEMENT)
                switch (input) {
                    case "north" -> {
                        player.moveKnight("north",player,gameMap);
                    }
                    case "cancel" -> {
                        gameState=EXPLORATION;
                        return "Cancelling move command.";
                    }
                    default -> {
                        return "Unknown direction! Use compass directions or type" +
                                "cancel to terminate command";
                    }
                }
        return "";
    }


}
