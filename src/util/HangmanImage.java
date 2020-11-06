package util;

public class HangmanImage {
    public static String[] filePath;
    public static String[][] consoleArt;

    static {
        filePath = new String[]{"src/images/hangman_initial.png", "src/images/hangman_guess1.png", "src/images/hangman_guess2.png", "src/images/hangman_guess3.png", "src/images/hangman_guess4.png", "src/images/hangman_guess5.png", "src/images/hangman_full.png"};
        //"/images/hangman_initial.png", "/images/hangman_guess1.png", "/images/hangman_guess2.png", "/images/hangman_guess3.png", "/images/hangman_guess4.png", "/images/hangman_guess5.png", "/images/hangman_full.png"
    }
}
