package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

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

    public static String processInput (Game game) {
        Scanner input = new Scanner(System.in);
        System.out.println(" > ");
        String enteredText = input.nextLine();
        return commandList(enteredText.toLowerCase(), game);
    }

    public static String commandList (String input, Game game) {

        if (game.gameState == EXPLORATION) {
                switch (input) {
                    case "help" -> {
                        return game.player.presentCommandListExploration();
                    }
                    case "move" -> {
                        game.gameState=MOVEMENT;
                        return "In which direction do you want to move?";
                    }
                    case "lookaround" -> {
                        return game.gameMap.presentPosition(game.player);
                    }
                    case "status" -> {
                        return game.player.presentKnightStatusExploration();
                    }
                    case "testobserver" -> {
                        game.player.setCurrentHealth(game.player.getCurrentHealth() - 5);
                        game.player.setCurrentMana(game.player.getCurrentMana() - 5);
                        game.player.setDamage(game.player.getDamage() - 1);
                        game.player.setArmor(game.player.getArmor() - 1);
                        game.player.setGoldHeld(game.player.getGoldHeld() - 5);
                        return String.valueOf(game.player.getCurrentHealth());
                    }
                    case "loot" -> {
                        return game.player.attemptPickup(game);
                    }
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
            } else if (game.gameState==MOVEMENT)
                switch (input) {
                    case "north" -> {
                        game.gameState=EXPLORATION;
                        return game.player.moveKnight("north",game) + "\n"
                         + game.gameMap.presentPosition(game.player);
                    }
                    case "south" -> {
                        game.gameState=EXPLORATION;
                        return game.player.moveKnight("south",game) + "\n"
                                + game.gameMap.presentPosition(game.player);
                    }
                    case "west" -> {
                        game.gameState=EXPLORATION;
                        return game.player.moveKnight("west",game) + "\n"
                                + game.gameMap.presentPosition(game.player);
                    }
                    case "east" -> {
                        game.gameState=EXPLORATION;
                        return game.player.moveKnight("east",game) + "\n"
                                + game.gameMap.presentPosition(game.player);
                    }
                    case "cancel" -> {
                        game.gameState=EXPLORATION;
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
