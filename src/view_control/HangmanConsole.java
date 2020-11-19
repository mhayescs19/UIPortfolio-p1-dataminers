/*
    Created by Michael Hayes
    2020
 */
package view_control;

import control_hangman.Hangman;
import util.HangmanImage;
import util.PrintyShortcuts;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * CONTROL USES DIRECTORY!
 * line 27,28
 * line 44
 * all methods line 59 and below
 */
public class HangmanConsole {

    private ArrayList<String> alphabet = new ArrayList<String>(); // alphabet to display to user, updates as they guess letters
    private boolean inConsole = true;

    public HangmanConsole(Hangman control) {

        boolean gameOver = control.getGameOver();
        boolean phraseMatch = control.getPhraseAccuracy();

        setAlphabet();

        Scanner scan = new Scanner(System.in);

        while (inConsole == true) {
            while (gameOver == false && phraseMatch == false) {
                printHangmanImage(control);
                printPhraseWithBlanks(control);
                printPossibleLetters();

                System.out.println("Enter a letter"); // new line for clarity
                String letterGuessedMaster = scan.next();
                char[] letterGuessed = letterGuessedMaster.toCharArray(); // takes input, converts to char[]
                control.checkLetter(letterGuessed[0]); // sends letter to get checked (index zero of a 1 element array)
                removeLetter(Character.toString(letterGuessed[0]));

                gameOver = control.getGameOver(); // updates boolean from control after each loop (necessary?)
                phraseMatch = control.getPhraseAccuracy(); // updates boolean from control after each loop (necessary?)
            }
            // gives user feedback to accuracy of guess and asks if they would like to play again
            System.out.println("Phrase guessed correctly!!");
            nextRoundPrompt(control);
            gameOver = control.getGameOver(); // updates boolean from control after each loop (necessary?)
            phraseMatch = control.getPhraseAccuracy(); // updates boolean from control after each loop (necessary?)
        }
    }

    /**
     * Prints current hangman image based on guesses remaining from control
     * If statement determines uses game over boolean to prevent printing an index that is out of bounds
     * @param control
     */
    public void printHangmanImage(Hangman control) {
        int hangmanImageIndex = 6 - control.getGuessesRemaining();

        if (control.getGameOver() == false){
            for (int j = 0; j < HangmanImage.consoleArt[hangmanImageIndex].length; j++){
                System.out.println(HangmanImage.consoleArt[hangmanImageIndex][j]);
            }
        } else {
            System.out.println(HangmanImage.consoleArt[hangmanImageIndex][6]);
            System.out.println("GAME OVER");

            nextRoundPrompt(control);
        }
    }

    /**
     * Prints current phrase with correctly guesses letters
     * @param control
     */
    public void printPhraseWithBlanks(Hangman control) {
        System.out.println(control.getCurrentPhraseForDisplay());
    }

    /**
     * Prints remaining letters to guess, which updates as user guesses letters
     */
    public void printPossibleLetters() {
        for (String letter: alphabet){
            System.out.print(letter + " ");
        }
        System.out.println("");
    }

    /**
     * Generates A-Z array list as reference for letters to guess
     */
    private void setAlphabet() {
        char letter;
        for(letter = 'A'; letter <= 'Z'; letter++) {
            alphabet.add(Character.toString(letter));
        }
    }

    /**
     * Removes letter that was guessed from the display (array list) based on user input
     */
    private void removeLetter(String currentLetter) {
        Iterator<String> alphabetIterator = alphabet.iterator();

        for (Iterator<String> it = alphabetIterator; it.hasNext(); ) {
            String letter = it.next();
            if (letter.equals(currentLetter.toUpperCase())) {
                alphabetIterator.remove();
            }
        }
    }

    /**
     * Prints prompt that asks if the user wants to guess another phrase
     * NOTE: triggered after either a successfully guesses phrase or a failure
     * @param control
     */
    private void nextRoundPrompt(Hangman control) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Guess another phrase? Y/N");
        String input = scan.next();

        switch (input){
            case "y":
            case "Y":
                control.startNextRound(); // manages resetting everything in control and model
                setAlphabet(); // only console related view item that needs to be reset for a new round
                break;
            case "n":
            case "N":
                inConsole = false;
                break;
            default:
                System.out.println("Unknown Error Occurred, returning to JFrame");
                break;
        }
    }
}
