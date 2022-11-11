module cz.vse.java.kadm09.jfx.gatekeepermk2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic to javafx.fxml;
    exports cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

}