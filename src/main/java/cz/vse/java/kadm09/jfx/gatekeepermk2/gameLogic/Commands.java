package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

import java.util.Scanner;

public class Commands {


    public static String processInput (TheKnight player) {
        Scanner input = new Scanner(System.in);
        System.out.println(" > ");
        String enteredText = input.nextLine();
        String output = commandList(enteredText.toLowerCase(), player);
        return  output;
    }

    public static String commandList (String input, TheKnight player) {

        switch (input) {
            case "help" -> {return player.presentCommandListExploration();}
            //case "move" -> TheKnight.moveKnight(input);
            //case "lookaround" -> System.out.println(Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).getName() + "\n" +Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).getDescription());
            case "status" -> {
                return player.presentKnightStatusExploration();
            }
                //case "loot" -> Item.attemptPickup();
                //case "showinventory" -> TheKnight.printInventoryContent();
                //case "useitem" -> TheKnight.useItem(input);
                //case "spelllist" -> Spells.printSpelllist();
                //case "cast" -> Spells.castSpells(input);
                //case "interact" -> Interactions.attemptInteraction(input);

            case "quitgame" -> {
                System.exit(0);
            }
            default -> {
                return("Unknown command, use HELP command if you are lost.");
            }
        }

        return "";
    }


}
