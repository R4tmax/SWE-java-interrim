package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary.Setup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.GameState.COMBAT;
import static java.lang.System.exit;

/**
 *
 *  @author Martin Kadlec
 *  @version 1.0.0
 * <p>
 *      Main class of the program, handles the initial splitting
 *      of the CLI and GUI accesses to the program
 *      based on the config parameter.
 * </p>
 *
 * <p>
 *     For the CLI version, handles the calls to the Commands class for
 *     further logic handling.
 * </p>
 *
 * @see Commands
 */
public class Main extends Application {

    /**
     * Behaves as described in the function doc.
     * @param args standard args, expects either empty value or "CLI"
     */
    public static void main(String[] args) {


        if (args.length > 0 && args[0].equals("cli")) {

            System.out.println(Setup.introMessage());
            Game game = new Game();
            System.out.println(game.gameMap.presentPosition(game.player));

            while (true) {

                System.out.println(game.checkGameStatus());
                if (game.player.isDead()) {
                    System.out.println("You have died, better luck next time!");
                    exit(0);
                }

                if (game.getGameMap().getCurrentPosition(game.getPlayer().getPosition().getHorizontal(), game.getPlayer().getPosition().getVertical()).getRoomEnemy() != null &&
                        game.initiative) {
                    game.gameState = COMBAT;
                    System.out.println("triggered combat");
                }

                System.out.println(Commands.processInput(game));
            }


        } else {
            launch();
        }

    }

    /**
     *
     * <p>
     *     Start function that handles loading the JavaFX
     *     components before execution of the program.
     * </p>
     *
     * @param primaryStage Called by the JavaFX libraries to present the GUI output
     * @throws Exception Standard application exception throw
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GK-console.fxml"));
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gatekeeper"); //sets the name of box
        primaryStage.show();
    }

}
