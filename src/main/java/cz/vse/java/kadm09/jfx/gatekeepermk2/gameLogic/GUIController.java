package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

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

    @FXML
    private void initialize (){
        

        healthLabel.appendText("HEALTH:");
        showHealth();
        manaLabel.appendText("MANA:");
        showMana();

        armorLabel.appendText("ARMOR");
        showArmor();

        damageLabel.appendText("DAMAGE");
        showDamage();

        goldLabel.appendText("GOLD HELD");
        showGold();

        Platform.runLater(() -> GUIInput.requestFocus());
    }

    private void showMana() {
    }

    private void showArmor() {
        
    }

    private void showDamage() {
        
    }

    private void showHealth() {
        
    }

    private void showGold() {
    }

    public void acceptInput(ActionEvent actionEvent) {
    }
    
    
}
