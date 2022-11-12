package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary.Setup;
import cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld.Map;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    public TheKnight player = Setup.createKnight();
    public Map gameMap = Setup.createMap();

    @FXML
    private void initialize (){

        player.registerObserver(this);

        healthLabel.appendText("HEALTH:");
        showHealth(player);
        manaLabel.appendText("MANA:");
        showMana(player);

        armorLabel.appendText("ARMOR");
        showArmor(player);

        damageLabel.appendText("DAMAGE");
        showDamage(player);

        goldLabel.appendText("GOLD HELD");
        showGold(player);

        present(gameMap.presentPosition(player));
        Platform.runLater(() -> GUIInput.requestFocus());
    }

    @FXML
    private void present(String text){
        GUIOutput.appendText(text);
        GUIOutput.appendText("\n");
    }



    public void acceptInput(ActionEvent actionEvent) {
        String GUIUserInput = GUIInput.getText();
        GUIInput.clear();
        present(Commands.commandList(GUIUserInput,player, gameMap));

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
        showHealth(player);
        showMana(player);
        showArmor(player);
        showDamage(player);
        showGold(player);
    }
}
