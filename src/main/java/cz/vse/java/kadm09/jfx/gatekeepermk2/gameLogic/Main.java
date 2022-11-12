package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary.Setup;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class Main extends Application {

    public static void main(String[] args) {
        TheKnight player=Setup.createKnight();
        Map gameMap = Setup.createMap();
        //Setup.initGame();

        if (args.length > 0 && args[0].equals("cli")) {
            System.out.println(Commands.processInput(player));
            exit(0);
        } else
        {
            launch();
        }

    }

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
