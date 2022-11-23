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
                    game.gameState = MOVEMENT;
                    return "In which direction do you want to move?";
                }
                case "lookaround" -> {
                    return game.gameMap.presentPosition(game.player);
                }
                case "status" -> {
                    return game.player.presentKnightStatusExploration();
                }
                case "loot" -> {
                    return game.player.attemptPickup(game);
                }
                case "showinventory" -> {
                    return game.player.presentInventoryContent();
                }
                case "useitem" -> {
                    game.gameState = INVENTORY;
                    return "Which item do you want to use?";
                }
                case "spelllist" -> {
                    return game.player.presentSpelllist();
                }
                case "cast" -> {
                    game.gameState = SPELLCAST;
                    return "Which spell do you want to cast? Use spell list if you are unsure!";
                }
                    //case "interact" -> Interactions.attemptInteraction(input);



                case "quitgame" -> System.exit(0);
                default -> {
                    return ("Unknown command, use HELP command if you are lost.");
                }
            }
        } else if (game.gameState == MOVEMENT) {
            switch (input) {
                case "north" -> {
                    game.gameState = EXPLORATION;
                    return game.player.moveKnight("north", game) + "\n"
                            + game.gameMap.presentPosition(game.player);
                }
                case "south" -> {
                    game.gameState = EXPLORATION;
                    return game.player.moveKnight("south", game) + "\n"
                            + game.gameMap.presentPosition(game.player);
                }
                case "west" -> {
                    game.gameState = EXPLORATION;
                    return game.player.moveKnight("west", game) + "\n"
                            + game.gameMap.presentPosition(game.player);
                }
                case "east" -> {
                    game.gameState = EXPLORATION;
                    return game.player.moveKnight("east", game) + "\n"
                            + game.gameMap.presentPosition(game.player);
                }
                case "cancel" -> {
                    game.gameState = EXPLORATION;
                    return "Cancelling move command.";
                }
                default -> {
                    return "Unknown direction! Use compass directions or type " +
                            "cancel to terminate command";
                }
            }
        } else if (game.gameState == INVENTORY) {
            switch (input) {
                case "showinventory" -> {
                    return game.player.presentInventoryContent();
                }
                case "cancel" -> {
                    if (game.initiative)  game.gameState = EXPLORATION;
                    else game.gameState = COMBAT;

                    return "Cancelling useitem command.";
                }
                default -> {
                    if (game.initiative)  game.gameState = EXPLORATION;
                    else game.gameState = COMBAT;
                    return game.player.useItem(game, input);
                }
            }
        } else if (game.gameState == COMBAT) {
            game.initiative = false;
            System.out.println("Testing print - killing initiative boolean");
            switch (input) {
                case "attack" -> {
                    String attackResolution = game.getGameMap().getCurrentPosition(game.player.getPosition().getHorizontal(),game.player.getPosition().getVertical()).getRoomEnemy().attackPattern(game);
                    return "attempted strike TBD" + attackResolution;
                }
                case "showinventory" -> {
                    return game.player.presentInventoryContent();
                }
                case "useitem" -> {
                    String attackResolution = game.getGameMap().getCurrentPosition(game.player.getPosition().getHorizontal(),game.player.getPosition().getVertical()).getRoomEnemy().attackPattern(game);
                    game.gameState = INVENTORY;
                    return "Your foe senses the opening and goes for the attack!" + "\n"
                            + attackResolution
                            + "\n" + "Which item do you want to use?";

                }
                case "spelllist" -> {
                    return game.player.presentSpelllist();
                }
                case "help" -> {
                    return game.player.presentCommandListCombat();
                }
                case "cast" -> {
                    game.gameState = SPELLCAST;
                    String attackResolution = game.getGameMap().getCurrentPosition(game.player.getPosition().getHorizontal(),game.player.getPosition().getVertical()).getRoomEnemy().attackPattern(game);
                    return "Your foe senses the opening and goes for the attack!" + "\n"
                            + attackResolution
                            + "\n"
                            + "Which spell do you want to cast? Use spell list if you are unsure!";
                }
                default -> {
                    return "You are under attack! Use HELP command if you are lost.";
                }
            }
        } else if (game.gameState == SPELLCAST) {
            switch (input) {
                case "cancel" -> {
                    if (game.initiative)  game.gameState = EXPLORATION;
                    else game.gameState = COMBAT;

                    return "Cancelling casting";
                }
                case "lightningtouch" -> {
                    if (game.initiative)  game.gameState = EXPLORATION;
                    else game.gameState = COMBAT;
                    return game.player.lightningTouch(game);
                }
                case "heal" -> {
                    if (game.initiative)  game.gameState = EXPLORATION;
                    else game.gameState = COMBAT;
                    return game.player.heal();
                }
                case "smite" -> {
                    if (game.initiative)  game.gameState = EXPLORATION;
                    else game.gameState = COMBAT;
                    return game.player.holySmite(game);
                }
                case "lightningstrike" -> {
                    if (game.initiative)  game.gameState = EXPLORATION;
                    else game.gameState = COMBAT;
                    return game.player.lightningStrike(game);
                }
                case "prayerofresolve" -> {
                    if (game.initiative)  game.gameState = EXPLORATION;
                    else game.gameState = COMBAT;
                    return game.player.prayerOfResolve(game);
                }
                case "prayerofstrength" -> {
                    if (game.initiative)  game.gameState = EXPLORATION;
                    else game.gameState = COMBAT;
                    return game.player.prayerOfStrength(game);
                }
                case "spelllist" -> {
                    return game.player.presentSpelllist();
                }
                default -> {
                    return "Unknown command";
                }
            }

        }
        return "";
    }


}
