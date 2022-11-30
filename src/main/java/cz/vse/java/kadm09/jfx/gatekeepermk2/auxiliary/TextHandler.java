package cz.vse.java.kadm09.jfx.gatekeepermk2.auxiliary;

public class TextHandler {

    public static String simplifyInput (String input) {
        return input.toLowerCase().replaceAll("\\s","");
    }

}
