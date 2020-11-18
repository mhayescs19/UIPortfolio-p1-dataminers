/*

    Created by Michael Hayes
    2020

 */

package util;

public class HangmanImage {
    public static String[] filePath;
    public static String[][] consoleArt;

    static {
        filePath = new String[]{"hangman_initial.png", "hangman_guess1.png", "hangman_guess2.png", "hangman_guess3.png", "hangman_guess4.png", "images/hangman_guess5.png", "images/hangman_full.png"};
        consoleArt = new String[][]{

                {
                        "  *---*  ",
                        "  |   |  ",
                        "      |  ",
                        "      |  ",
                        "      |  ",
                        "      |  ",
                        "=========",
                },
                {
                        "  *---*  ",
                        "  |   |  ",
                        "  0   |  ",
                        "      |  ",
                        "      |  ",
                        "      |  ",
                        "=========",
                },
                {
                        "  *---*  ",
                        "  |   |  ",
                        "  0   |  ",
                        "  |   |  ",
                        "      |  ",
                        "      |  ",
                        "=========",
                },
                {
                        "  *---*  ",
                        "  |   |  ",
                        "  0   |  ",
                        " /|   |  ",
                        "      |  ",
                        "      |  ",
                        "=========",
                },
                {
                        "  *---*  ",
                        "  |   |  ",
                        "  0   |  ",
                        " /|\\  |  ",
                        "      |  ",
                        "      |  ",
                        "=========",
                },
                {
                        "  *---*  ",
                        "  |   |  ",
                        "  0   |  ",
                        " /|\\  |  ",
                        " /    |  ",
                        "      |  ",
                        "=========",
                },
                {
                        "  *---*  ",
                        "  |   |  ",
                        "  0   |  ",
                        " /|\\  |  ",
                        " / \\  |  ",
                        "      |  ",
                        "=========",
                },
        };
    }
}
