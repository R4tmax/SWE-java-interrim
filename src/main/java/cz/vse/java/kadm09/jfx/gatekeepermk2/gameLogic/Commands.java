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
                case "kill" -> {
                    game.player.setCurrentHealth(0);
                    return "Killing the player"; //TODO: Cleanse once not needed
                }
                case "move" -> {
                    game.setGameState(MOVEMENT);
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
                    game.setGameState(INVENTORY);
                    return "Which item do you want to use?";
                }
                case "spelllist" -> {
                    return game.player.presentSpelllist();
                }
                case "cast" -> {
                    game.setGameState(SPELLCAST);
                    return "Which spell do you want to cast? Use spell list if you are unsure!";
                }
                case "interact" -> {
                    switch (game.gameMap.getCurrentPosition(game.player.getPosition().getHorizontal(),game.player.getPosition().getVertical()).getRoomBehavior()) {
                        case TALKABLE -> {
                            return game.callScout();
                        }
                        case RECON -> {
                            return "Hmm, no one is around.";
                        }
                        case REST_AREA -> {
                            game.setGameState(INN);
                            return "You enter the tavern. You could rest here, for a measly price of 300 gold. (Y/N)";
                        }
                        case TRADABLE -> {
                            game.setGameState(MARKET);
                            return "You enter the market and peruse the local stands.";
                        }
                        case HOSTILE -> {
                            return "Shivers run down your spine. Some unnatural darkness still looms here and your thoughts disperse.";
                        }
                        default -> {
                            return "Room-type ERROR";
                        }
                    }
                }
                case "quitgame" -> System.exit(0);
                default -> {
                    return ("Unknown command, use HELP command if you are lost.");
                }
            }
        } else if (game.gameState == MOVEMENT) {
            switch (input) {
                case "north" -> {
                    game.setGameState(EXPLORATION);
                    return game.player.moveKnight("north", game) + "\n"
                            + game.gameMap.presentPosition(game.player);
                }
                case "south" -> {
                    game.setGameState(EXPLORATION);
                    return game.player.moveKnight("south", game) + "\n"
                            + game.gameMap.presentPosition(game.player);
                }
                case "west" -> {
                    game.setGameState(EXPLORATION);
                    return game.player.moveKnight("west", game) + "\n"
                            + game.gameMap.presentPosition(game.player);
                }
                case "east" -> {
                    game.setGameState(EXPLORATION);
                    return game.player.moveKnight("east", game) + "\n"
                            + game.gameMap.presentPosition(game.player);
                }
                case "cancel" -> {
                    game.setGameState(EXPLORATION);
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
                    if (game.initiative)  game.setGameState(EXPLORATION);
                    else game.setGameState(COMBAT);

                    return "Cancelling useitem command.";
                }
                default -> {
                    if (game.initiative)  game.setGameState(EXPLORATION);
                    else game.setGameState(COMBAT);
                    return game.player.useItem(game, input);
                }
            }
        } else if (game.gameState == COMBAT) {
            game.initiative = false;

            switch (input) {
                case "attack" -> {
                    String monsterAttackResolution = game.getGameMap().getCurrentPosition(game.player.getPosition().getHorizontal(),game.player.getPosition().getVertical()).getRoomEnemy().attackPattern(game);
                    String knightAttackResoultion = game.strikeEnemy();
                    return monsterAttackResolution + "\n" + knightAttackResoultion ;
                }
                case "showinventory" -> {
                    return game.player.presentInventoryContent();
                }
                case "useitem" -> {
                    String attackResolution = game.getGameMap().getCurrentPosition(game.player.getPosition().getHorizontal(),game.player.getPosition().getVertical()).getRoomEnemy().attackPattern(game);
                    game.setGameState(INVENTORY);
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
                    game.setGameState(SPELLCAST);
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
                    if (game.initiative)  game.setGameState(EXPLORATION);
                    else game.setGameState(COMBAT);

                    return "Cancelling casting";
                }
                case "lightningtouch" -> {
                    if (game.initiative)  game.setGameState(EXPLORATION);
                    else game.setGameState(COMBAT);
                    return game.player.lightningTouch(game);
                }
                case "heal" -> {
                    if (game.initiative)  game.setGameState(EXPLORATION);
                    else game.setGameState(COMBAT);
                    return game.player.heal();
                }
                case "smite" -> {
                    if (game.initiative)  game.setGameState(EXPLORATION);
                    else game.setGameState(COMBAT);
                    return game.player.holySmite(game);
                }
                case "lightningstrike" -> {
                    if (game.initiative)  game.setGameState(EXPLORATION);
                    else game.setGameState(COMBAT);
                    return game.player.lightningStrike(game);
                }
                case "prayerofresolve" -> {
                    if (game.initiative)  game.setGameState(EXPLORATION);
                    else game.setGameState(COMBAT);
                    return game.player.prayerOfResolve(game);
                }
                case "prayerofstrength" -> {
                    if (game.initiative)  game.setGameState(EXPLORATION);
                    else game.setGameState(COMBAT);
                    return game.player.prayerOfStrength(game);
                }
                case "spelllist" -> {
                    return game.player.presentSpelllist();
                }
                default -> {
                    return "Unknown command";
                }
            }
        } else if (game.gameState == INN) {
            switch (input) {
                case "y" -> {
                    game.setGameState(EXPLORATION);
                    return game.rest();
                }
                case "n" -> {
                    game.setGameState(EXPLORATION);
                    return "You left the INN and walked outside." + "\n" +
                            game.getGameMap().presentPosition(game.player);
                }
                default -> {
                    return "Press Y to pay 300 gold for sleep and rest" +"\n" +
                            "Press N to walkout";
                }
            }
        } else if (game.gameState == MARKET) {
            switch (input) {
                case "huntsman" -> {
                    game.setGameState(HUNTSMAN);
                    return "You approach the huntsman, who gives you a lukewarm smile.";
                }
                case "armorsmith" -> {
                    game.setGameState(ARMORSMITH);
                    return "You approach the armorsmith, who has a tired look and a underline of fear in his eyes";
                }
                case "cancel" -> {
                    game.setGameState(EXPLORATION);
                    return "You have left the market" + "\n" +
                            game.gameMap.presentPosition(game.player);
                }
                default -> {
                    return """
                            You are at the market, of all the stalls, only two catch your eyes.
                            There is a HUNTSMAN, selling whetting stones and such.
                            Furthermore, a bulky ARMORSMITH with a wagon is residing nearby.
                            You can always just walkout, of course (CANCEL)
                            """;
                }
            }
        } else if (game.gameState == HUNTSMAN) {
            switch (input) {
                case "y" -> {
                    return game.improveWeapons();
                }
                case "n" -> {
                    game.setGameState(EXPLORATION);
                    return "You walked away from the stands, and headed back towards the village." + "\n" +
                            game.getGameMap().presentPosition(game.player);
                }
                default -> {
                    return "You can improve your weapons here, for a price. \n Upgrades get more expensive with each iteration."
                            + "Press Y for weapon upgrades, press N to walkaway";
                }
            }
            
        } else if (game.gameState == ARMORSMITH) {
            switch (input) {
                case "y" -> {
                    return game.improveArmor();
                }
                case "n" -> {
                    game.setGameState(EXPLORATION);
                    return "You walked away from the stands, and headed back towards the village." + "\n" +
                            game.getGameMap().presentPosition(game.player);
                }
                default -> {
                    return """
                            You can improve your weapons here, for a price.\s
                             Upgrades get more expensive with each iteration.\s
                            Press Y for armor upgrades, press N to walkaway""";
                }
            }
        }
        return "";
    }


}
