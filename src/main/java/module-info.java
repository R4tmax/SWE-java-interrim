module cz.vse.java.kadm09.jfx.gatekeepermk2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic to javafx.fxml;
    exports cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;
    exports cz.vse.java.kadm09.jfx.gatekeepermk2.knight;
    exports cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld;
    exports cz.vse.java.kadm09.jfx.gatekeepermk2.items;
    exports cz.vse.java.kadm09.jfx.gatekeepermk2.enemies;

}