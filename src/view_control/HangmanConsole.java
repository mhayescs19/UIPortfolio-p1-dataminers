/*
    Created by Michael Hayes
    2020
 */
package view_control;

import control_hangman.Hangman;
import util.HangmanImage;

import java.util.Scanner;

public class HangmanConsole {

    public HangmanConsole(Hangman control) {
        printPhraseWithBlanks(control);

        Scanner scan = new Scanner(System.in);
        boolean phraseMatch = control.getPhraseAccuracy(); // retrieves if current phrase matches the reference phrase (user correctly guesses phrase)
        while (phraseMatch != true) {

        }
    }

    private void printView(Hangman control) {




    }

    /**
     * Prints current hangman image based on guesses remaining from control
     * @param control
     */
    public void printHangmanImage(Hangman control) {
        int hangmanImageIndex = 5 - control.getGuessesRemaining();
            for (int j = 0; j < HangmanImage.consoleArt[hangmanImageIndex].length; j++){
                System.out.println(HangmanImage.consoleArt[hangmanImageIndex][j]);
            }

    }

    public void printPhraseWithBlanks(Hangman control) {
        System.out.println(control.getCurrentPhraseForDisplay());
    }

}
