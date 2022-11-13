package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

import java.util.Scanner;

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

        switch (gameState) {
            case EXPLORATION -> {
                switch (input) {
                    case "help" -> {
                        return player.presentCommandListExploration();
                    }
                    case "move" -> player.moveKnight(input, player, gameMap);
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
            }
            case MOVEMENT -> {

            }
            default -> System.exit(404);
        }
        return "";
    }


}
