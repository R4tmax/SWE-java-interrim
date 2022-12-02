package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary.Setup;
import cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary.TextHandler;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.GameState.COMBAT;
import static cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.GameState.EXPLORATION;

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

    @FXML
    private void initialize (){

        present(Setup.introMessage());
        game.player.registerObserver(this);

        healthLabel.appendText("HEALTH:");
        showHealth(game.player);
        manaLabel.appendText("MANA:");
        showMana(game.player);

        armorLabel.appendText("ARMOR");
        showArmor(game.player);

        damageLabel.appendText("DAMAGE");
        showDamage(game.player);

        goldLabel.appendText("GOLD HELD");
        showGold(game.player);

        centerImage();

        present(game.gameMap.presentPosition(game.player));
        Platform.runLater(() -> GUIInput.requestFocus());
    }

    @FXML
    private void present(String text){
        GUIOutput.appendText(text);
        GUIOutput.appendText("\n");
    }

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

    private void syncGameState() {
        present(game.checkGameStatus());
        if(game.player.isDead()) {
            present("You have died, better luck next time!");
            GUIOutput.setDisable(true);
            GUIInput.setDisable(true);
        }

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
        }
    }

        private void showHealth(TheKnight player) {
        GUIHealth.clear();
        GUIHealth.appendText(String.valueOf(player.getCurrentHealth()));
    }

    private void showMana(TheKnight player) {
        GUIMana.clear();
        GUIMana.appendText(String.valueOf(player.getCurrentMana()));
    }

    private void showArmor(TheKnight player) {
        GUIArmor.clear();
        GUIArmor.appendText(String.valueOf(player.getArmor()));
    }

    private void showDamage(TheKnight player) {
        GUIAttack.clear();
        GUIAttack.appendText(String.valueOf(player.getDamage()));
    }

    private void showGold(TheKnight player) {
        GUIGold.clear();
        GUIGold.appendText(String.valueOf(player.getGoldHeld()));
    }

    @Override
    public void updateStatus() {
        showHealth(game.player);
        showMana(game.player);
        showArmor(game.player);
        showDamage(game.player);
        showGold(game.player);
    }

    @FXML
    protected void quickmoveLook () {
        present(game.gameMap.presentPosition(game.player));
    }

    @FXML
    protected void quickmoveNorth () {
        present(game.player.moveKnight("north",game));
        present(game.gameMap.presentPosition(game.player));
        syncGameState();
    }

    @FXML
    protected void quickmoveSotuh () {
        present(game.player.moveKnight("south",game));
        present(game.gameMap.presentPosition(game.player));
        syncGameState();
    }

    @FXML
    protected void quickmoveEast () {
        present(game.player.moveKnight("east",game));
        present(game.gameMap.presentPosition(game.player));
        syncGameState();
    }

    @FXML
    protected void quickmoveWest () {
        present(game.player.moveKnight("west",game));
        present(game.gameMap.presentPosition(game.player));
        syncGameState();
    }

    @FXML
    public void quickcastLT() {
        present(game.player.lightningTouch(game));
        syncGameState();
    }
    @FXML
    public void quickcastLS() {
        present(game.player.lightningStrike(game));
        syncGameState();
    }
    @FXML
    public void quickcastH() {
        present(game.player.heal());
        syncGameState();
    }
    @FXML
    public void quickcastS() {
        present(game.player.holySmite(game));
        syncGameState();
    }
    @FXML
    public void quickcastPoS() {
        present(game.player.prayerOfStrength(game));
        syncGameState();
    }
    @FXML
    public void quickcastPoR() {
        present(game.player.prayerOfResolve(game));
        syncGameState();
    }

    /**
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

}
