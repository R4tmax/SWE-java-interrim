package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import java.util.Scanner;

public class Commands {


    public static String processInput () {
        Scanner input = new Scanner(System.in);
        System.out.println(" > ");
        String enteredText = input.nextLine();
        return enteredText.toLowerCase();
    }

   /* public static String CommandList (String input) {

        switch (command.toLowerCase()) {
            case "help" -> TheKnight.printCommandListExploration();
            case "move" -> TheKnight.moveKnight(input);
            case "lookaround" -> System.out.println(Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).getName() + "\n" +Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).getDescription());
            case "status" -> TheKnight.printKnightStatusExploration();
            case "loot" -> Item.attemptPickup();
            case "showinventory" -> TheKnight.printInventoryContent();
            case "useitem" -> TheKnight.useItem(input);
            case "spelllist" -> Spells.printSpelllist();
            case "cast" -> Spells.castSpells(input);
            case "interact" -> Interactions.attemptInteraction(input);
            case "quitgame" -> {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
            case "testposition" -> System.out.println(Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()));
            default -> System.out.println("Unknown command, use HELP command if you are lost.");
        }

    }*/


}
