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

public class GUIController {
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


        healthLabel.appendText("HEALTH:");
        //showHealth();
        manaLabel.appendText("MANA:");
        showMana();

        armorLabel.appendText("ARMOR");
        showArmor();

        damageLabel.appendText("DAMAGE");
        showDamage();

        goldLabel.appendText("GOLD HELD");
        showGold();

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
        present(Commands.commandList(GUIUserInput,player));

    }

       /* private void showHealth(TheKnight player) {
        GUIHealth.appendText(String.valueOf(player.getCurrentHealth()));
    }*/

    private void showMana() {

    }

    private void showArmor() {

    }

    private void showDamage() {

    }

    private void showGold() {
    }
    
}
