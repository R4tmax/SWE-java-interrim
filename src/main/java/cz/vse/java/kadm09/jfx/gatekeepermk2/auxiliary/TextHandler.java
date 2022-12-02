package cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary;

/**
 * @author Martin Kadlec
 * @version Last refactor on 3.12.2022
 *
 * <p>
 *     Simple class holding the required unified String handling
 *     operations within the program
 * </p>
 */
public class TextHandler {

    /**
     * @param input Incoming String entered by the player.
     * @return String which will be all lowercase and free of spaces
     */
    public static String simplifyInput (String input) {
        return input.toLowerCase().replaceAll("\\s","");
    }

}
