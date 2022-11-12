package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

/**
 * Facilitates the observed part of the Observer
 * implementation.
 * Used by TheKnight class to present changes to the
 * main character on the GUI via the GUIController and
 * associated FXML.
 *
 * @see cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight
 * @see Observer
 * @see GUIController
 */
public interface ObservedElement {

    void registerObserver(Observer observer);
}
