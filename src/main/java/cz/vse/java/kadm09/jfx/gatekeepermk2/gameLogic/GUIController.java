package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary.Setup;
import cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary.TextHandler;
import cz.vse.java.kadm09.jfx.gatekeepermk2.items.Consumable;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collection;
import java.util.Objects;

import static cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.GameState.*;

/**
 * @author Martin Kadelec
 * @version Last refactor on 4.12.2022
 * <p>
 * Controller for the GKConsole JavaFX instance and related FXML.
 * Invoked via the loader from Main.
 * </p>
 */
public class GUIController implements Observer{
    public TextArea GUIHealth;
    public TextArea GUIMana;
    public TextArea GUIArmor;
    public TextArea GUIAttack;
    public TextArea GUIGold;
    public TextArea healthLabel;
    public TextArea manaLabel;
    public TextArea armorLabel;
    public TextArea damageLabel;
    public TextArea goldLabel;
    public TextArea GUIOutput;
    public TextField GUIInput;
    public SplitPane GUIOutputSplitter;

    public Game game = new Game();
    public Button GUIQMLeft;
    public Button GUIQMUp;
    public Button GUIQMLook;
    public Button GUIQMRight;
    public Button GUIQMDown;
    public Button GUIQCLT;
    public Button GUIQCLS;
    public Button GUIQCH;
    public Button GUIQCS;
    public Button GUIQCPoS;
    public Button GUIQCPoR;
    public ImageView GUIGamestateIcon;
    public ListView<Consumable> GUIInventoryList;

    /**
     * Standard JFX setup method.
     * Called after launch by the Main class
     * and upon invocation of new game.
     */
    @FXML
    private void initialize (){

        present(Setup.introMessage());
        game.player.registerObserver(this);

        prepareLabels();

        centerImage();

        present(game.gameMap.presentPosition(game.player));
        Platform.runLater(() -> GUIInput.requestFocus());


        GUIInventoryList.setCellFactory(ConsumableListView -> new ListCell<>() {
            /**
             * Modified callback method used to handle Item projection to the GUI
             * @param consumable Consumable item held within the ListView
             * @param empty Boolean flag representing data presence
             */
            @Override
            protected void updateItem(Consumable consumable, boolean empty) {
                super.updateItem(consumable, empty);
                if(!empty) {
                    setText(consumable.getName());
                    URL imageUrl = getClass().getResource(TextHandler.simplifyInput(consumable.getName())+".png");
                    if(imageUrl==null) return;
                    ImageView iw = new ImageView(imageUrl.toString());
                    iw.setFitHeight(50);
                    iw.setPreserveRatio(true);
                    setGraphic(iw);

                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });

    }

    /**
     * Sets labels for the GUI stat fields
     */
    private void prepareLabels() {
        healthLabel.clear();
        healthLabel.appendText("HEALTH:");
        showHealth(game.player);

        manaLabel.clear();
        manaLabel.appendText("MANA:");
        showMana(game.player);

        armorLabel.clear();
        armorLabel.appendText("ARMOR");
        showArmor(game.player);

        damageLabel.clear();
        damageLabel.appendText("DAMAGE");
        showDamage(game.player);

        goldLabel.clear();
        goldLabel.appendText("GOLD HELD");
        showGold(game.player);
    }

    /**
     * Standard ouput method for the controller, takes the String values and appends them to the Output
     * with extra whitespaces for better readability
     * @param text String value to output to the GKConsole
     */
    @FXML
    private void present(String text){
        GUIOutput.appendText(text);
        GUIOutput.appendText("\n");
    }

    /**
     * Initial input processing method of the controller.
     * Provides command readback for the user for clarity.
     * Then strips unnecessary chars and calls further input processing.
     * <p>
     * Syncing method is called at the beginning and after each call to maintain GUI synced
     * to the rest of the game data
     * @see #handleInput(String)
     * @see #syncGameState()
     */
    @FXML
    public void acceptInput() {
        syncGameState();

        String GUIUserInput = GUIInput.getText();
        GUIOutput.appendText("> " + GUIUserInput);
        GUIInput.clear();

        GUIUserInput = TextHandler.simplifyInput(GUIUserInput);
        handleInput(GUIUserInput);

        GUIInput.requestFocus();
        syncGameState();
    }


    /**
     * First, checks the gamestate of the game, this is done
     * to handle transitioning into combat.
     * Then calls the command list and presents the output value.
     * Sync method is called between states to ensure data seen by the player correspond
     * to the actual gamestate.
     *
     * @param input Validate string to compare against command list
     *
     * @see Commands
     * @see #syncGameState()
     * @see #present(String)
     */
    private void handleInput(String input) {

        syncGameState();

        if (game.getGameMap().getCurrentPosition(game.getPlayer().getPosition().getHorizontal(), game.getPlayer().getPosition().getVertical()).getRoomEnemy() != null &&
                game.initiative) {
            game.setGameState(COMBAT);
            game.initiative = false;
        }

        syncGameState();

        present(Commands.commandList(input,game));

        syncGameState();

        GUIInput.requestFocus();
    }

    /**
     * Auxiliary method called on demand by other methods.
     * Checks the gameStatus as per the Game template,
     * depending on the state of flags turns various GUI elements
     * ON/OFF. Special care is given to the combat trainstions.
     * Overrides gameStateIcon in special cases.
     */
    private void syncGameState() {
        present(game.checkGameStatus());
        if(game.player.isDead()) {
            present("You have died, better luck next time!");
            GUIOutput.setDisable(true);
            GUIInput.setDisable(true);
            GUIInventoryList.setDisable(true);
            GUIQMLook.setDisable(true);
            GUIQMUp.setDisable(true);
            GUIQMDown.setDisable(true);
            GUIQMLeft.setDisable(true);
            GUIQMRight.setDisable(true);
            GUIQCH.setDisable(true);
            GUIQCS.setDisable(true);
            GUIQCPoR.setDisable(true);
            GUIQCPoS.setDisable(true);
            GUIQCLS.setDisable(true);
            GUIQCLT.setDisable(true);
            Image toShow = new Image(String.valueOf(getClass().getResource("gameover.png")));
            GUIGamestateIcon.setImage(toShow);
            return;
        }

        changeIcon();
        centerImage();

        if (game.gameState != EXPLORATION) {
            GUIQMLook.setDisable(true);
            GUIQMUp.setDisable(true);
            GUIQMDown.setDisable(true);
            GUIQMLeft.setDisable(true);
            GUIQMRight.setDisable(true);
        } else if (game.getGameMap().getCurrentPosition(game.player.getPosition().getHorizontal(),game.player.getPosition().getVertical()).getRoomEnemy() != null) {
            GUIQMLook.setDisable(true);
            GUIQMUp.setDisable(true);
            GUIQMDown.setDisable(true);
            GUIQMLeft.setDisable(true);
            GUIQMRight.setDisable(true);
            Image toShow = new Image(String.valueOf(getClass().getResource("combat.png")));
            GUIGamestateIcon.setImage(toShow);
        } else if (!game.initiative) {
            GUIQMLook.setDisable(true);
            GUIQMUp.setDisable(true);
            GUIQMDown.setDisable(true);
            GUIQMLeft.setDisable(true);
            GUIQMRight.setDisable(true);
        } else {
            GUIQMLook.setDisable(false);
            GUIQMUp.setDisable(false);
            GUIQMDown.setDisable(false);
            GUIQMLeft.setDisable(false);
            GUIQMRight.setDisable(false);
        }

        if (game.endgame) {
            present("The Matriarch, the source of local discord, lies at your feet \n " +
                    "Your task is done, rest easy, Knight of the King!.");
            GUIOutput.setDisable(true);
            GUIInput.setDisable(true);
            GUIInventoryList.setDisable(true);
            GUIQMLook.setDisable(true);
            GUIQMUp.setDisable(true);
            GUIQMDown.setDisable(true);
            GUIQMLeft.setDisable(true);
            GUIQMRight.setDisable(true);
            GUIQCH.setDisable(true);
            GUIQCS.setDisable(true);
            GUIQCPoR.setDisable(true);
            GUIQCPoS.setDisable(true);
            GUIQCLS.setDisable(true);
            GUIQCLT.setDisable(true);
            Image toShow = new Image(String.valueOf(getClass().getResource("victory.png")));
            GUIGamestateIcon.setImage(toShow);
        }
    }

    /**
     * Resets and displays updated health value of the player
     * in the GUI.
     *
     * @param player Instance of the player whose health should be displayed
     */
        private void showHealth(TheKnight player) {
        GUIHealth.clear();
        GUIHealth.appendText(String.valueOf(player.getCurrentHealth()));
    }

    /**
     * Resets and displays updated mana value of the player
     * in the GUI.
     *
     * @param player Instance of the player whose mana should be displayed
     */
    private void showMana(TheKnight player) {
        GUIMana.clear();
        GUIMana.appendText(String.valueOf(player.getCurrentMana()));
    }

    /**
     * Resets and displays updated armor value of the player
     * in the GUI.
     *
     * @param player Instance of the player whose armor should be displayed
     */
    private void showArmor(TheKnight player) {
        GUIArmor.clear();
        GUIArmor.appendText(String.valueOf(player.getArmor()));
    }

    /**
     * Resets and displays updated damage value of the player
     * in the GUI.
     *
     * @param player Instance of the player whose damage should be displayed
     */
    private void showDamage(TheKnight player) {
        GUIAttack.clear();
        GUIAttack.appendText(String.valueOf(player.getDamage()));
    }

    /**
     * Resets and displays updated gold held value of the player
     * in the GUI.
     *
     * @param player Instance of the player whose money should be displayed
     */
    private void showGold(TheKnight player) {
        GUIGold.clear();
        GUIGold.appendText(String.valueOf(player.getGoldHeld()));
    }

    /**
     * Method called upon observer notifications.
     * Updates the stat labels and inventory
     * listView
     */
    @Override
    public void updateStatus() {
        showHealth(game.player);
        showMana(game.player);
        showArmor(game.player);
        showDamage(game.player);
        showGold(game.player);

        fillInventoryList();
    }

    /**
     * Allows the player to look around without using text input
     */
    @FXML
    protected void quickmoveLook () {
        present(game.gameMap.presentPosition(game.player));
    }

    /**
     * Allows the player to move without using text input
     */
    @FXML
    protected void quickmoveNorth () {
        present(game.player.moveKnight("north",game));
        present(game.gameMap.presentPosition(game.player));
        syncGameState();
    }

    /**
     * Allows the player to move without using text input
     */
    @FXML
    protected void quickmoveSotuh () {
        present(game.player.moveKnight("south",game));
        present(game.gameMap.presentPosition(game.player));
        syncGameState();
    }

    /**
     * Allows the player to move without using text input
     */
    @FXML
    protected void quickmoveEast () {
        present(game.player.moveKnight("east",game));
        present(game.gameMap.presentPosition(game.player));
        syncGameState();
    }

    /**
     * Allows the player to move without using text input
     */
    @FXML
    protected void quickmoveWest () {
        present(game.player.moveKnight("west",game));
        present(game.gameMap.presentPosition(game.player));
        syncGameState();
    }

    /**
     * Allows the player to cast spells without using text input
     */
    @FXML
    public void quickcastLT() {
        present(game.player.lightningTouch(game));
        syncGameState();
    }

    /**
     * Allows the player to cast spells without using text input
     */
    @FXML
    public void quickcastLS() {
        present(game.player.lightningStrike(game));
        syncGameState();
    }

    /**
     * Allows the player to cast spells without using text input
     */
    @FXML
    public void quickcastH() {
        present(game.player.heal());
        syncGameState();
    }

    /**
     * Allows the player to cast spells without using text input
     */
    @FXML
    public void quickcastS() {
        present(game.player.holySmite(game));
        syncGameState();
    }

    /**
     * Allows the player to cast spells without using text input
     */
    @FXML
    public void quickcastPoS() {
        present(game.player.prayerOfStrength(game));
        syncGameState();
    }

    /**
     * Allows the player to cast spells without using text input
     */
    @FXML
    public void quickcastPoR() {
        present(game.player.prayerOfResolve(game));
        syncGameState();
    }

    /**
     * Grabs the current inventory contents of the player and displays them on
     * the GUI. Auxiliary call is made to the Cell factory, hidden to the programmer.
     * Terminates early if the inventory is empty.
     *
     * @see #initialize()
     */
    public void fillInventoryList () {
        GUIInventoryList.getItems().clear();
        if (game.player.inventory.size() == 0) return;
        Collection<Consumable> items = game.player.getInventory();


        GUIInventoryList.getItems().addAll(items);
    }


    /**
     * Facilitates interaction with the inventory upon the player click.
     * Cancels NULL calls. otherwise invokes related Knight method
     *
     * @see TheKnight#useItem(Game, String)
     */
    public void handleClickInventory() {
        Consumable toUse = GUIInventoryList.getSelectionModel().getSelectedItem();
        if(toUse==null) return;
        present(game.player.useItem(game,toUse.getName()));
    }





    /**
     * Centers gameStateIcon within its div.
     * <a href="https://stackoverflow.com/questions/32781362/centering-an-image-in-an-imageview">Original SO reference</a>
     */
    public void centerImage() {
        Image img = GUIGamestateIcon.getImage();
        if (img != null) {
            double w;
            double h;

            double ratioX = GUIGamestateIcon.getFitWidth() / img.getWidth();
            double ratioY = GUIGamestateIcon.getFitHeight() / img.getHeight();

            double reducCoeff = Math.min(ratioX, ratioY);

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            GUIGamestateIcon.setX((GUIGamestateIcon.getFitWidth() - w) / 2);
            GUIGamestateIcon.setY((GUIGamestateIcon.getFitHeight() - h) / 2);

        }
    }

    /**
     * Checks for current gamestate and replaces default Icon as needed
     * does not work well with switch statement for some reason.
     */
    private void changeIcon() {
        Enum<GameState> state = game.gameState;
        Image toShow;
        if (state.equals(EXPLORATION)) {
            toShow = new Image(String.valueOf(getClass().getResource("explore.png")));
        } else if (state.equals(MOVEMENT)) {
            toShow = new Image(String.valueOf(getClass().getResource("movement.png")));
        }else if (state.equals(COMBAT)) {
            toShow = new Image(String.valueOf(getClass().getResource("combat.png")));
        } else if (state.equals(SPELLCAST)) {
            toShow = new Image(String.valueOf(getClass().getResource("magic.png")));
        } else if (state.equals(INVENTORY)) {
            toShow = new Image(String.valueOf(getClass().getResource("backpack.png")));
        }else if (state.equals(INN)) {
            toShow = new Image(String.valueOf(getClass().getResource("bed.png")));
        }else if (state.equals(MARKET)) {
            toShow = new Image(String.valueOf(getClass().getResource("pouch.png")));
        }else if (state.equals(HUNTSMAN)) {
            toShow = new Image(String.valueOf(getClass().getResource("bow.png")));
        }else if (state.equals(ARMORSMITH)) {
            toShow = new Image(String.valueOf(getClass().getResource("armor.png")));
        }else if (state.equals(ENDGAME)) {
            toShow = new Image(String.valueOf(getClass().getResource("victory.png")));
        } else {
            toShow = new Image(String.valueOf(getClass().getResource("nourishingbread.png")));
        }
        GUIGamestateIcon.setImage(toShow);
    }

    /**
     * Handles all the necessary GUI and BE
     * calls to quickly recreate new game.
     * Take note that fresh instance of the Game class
     * will be used to handle all the interactions upon invocation.
     *
     * @see Game
     */
    public void startNewGame() {
        game = new Game();
        initialize();
        syncGameState();
        fillInventoryList();
        GUIInput.setDisable(false);
        GUIOutput.setDisable(false);
        GUIInventoryList.setDisable(false);
        GUIQMLook.setDisable(false);
        GUIQMUp.setDisable(false);
        GUIQMDown.setDisable(false);
        GUIQMLeft.setDisable(false);
        GUIQMRight.setDisable(false);
        GUIQCH.setDisable(false);
        GUIQCS.setDisable(false);
        GUIQCPoR.setDisable(false);
        GUIQCPoS.setDisable(false);
        GUIQCLS.setDisable(false);
        GUIQCLT.setDisable(false);
    }

    /**
     * Terminates the game upon click
     * without the need to use text input.
     * Also accessible in each gamestate.
     */
    public void quitGame() {
        System.exit(0);
    }

    /**
     * Displays the master manual for the game.
     */
    public void showHelp() {
        Stage stage = new Stage();
        String url = Objects.requireNonNull(getClass().getResource("help.html")).toExternalForm();
        WebView webView = new WebView();
        webView.getEngine().load(url);
        Scene scene = new Scene(webView);
        stage.setScene(scene);
        stage.show();

    }

}
