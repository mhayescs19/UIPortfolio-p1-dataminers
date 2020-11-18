/*

    Created by Michael Hayes
    2020

 */

package util;

public class HangmanImage {
    public static String[] filePath;
    public static String[][] consoleArt;

    static {
        filePath = new String[]{"src/images/hangman_initial.png", "src/images/hangman_guess1.png", "src/images/hangman_guess2.png", "src/images/hangman_guess3.png", "src/images/hangman_guess4.png", "src/images/hangman_guess5.png", "src/images/hangman_full.png"};
    }

    static {
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

    public static void main(String[] args) {
        for (int i = 0; i < consoleArt.length; i++) {
            for (int j = 0; j < consoleArt[0].length; j++){
                System.out.println(consoleArt[i][j]);
            }
        }
    }
}

