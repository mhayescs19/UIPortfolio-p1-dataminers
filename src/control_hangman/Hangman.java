/*

    Created by Michael Hayes
    2020

 */

package control_hangman;

import model_hangman.Phrase;
import model_linkedlists.Stack;
import util.HangmanPhrases;
import util.PrintyShortcuts;
import view_control.HangmanConsole;
import view_control.HangmanUI;

import java.util.ArrayList;
import java.util.Random;

public class Hangman {

    public Phrase model;
    public HangmanUI view;
    public HangmanConsole console;

    private enum STATE {UI, CONSOLE}

    private int guessesRemaining; // counter to limit number of guesses for word
    private boolean gameOver; // boolean that is changed once the user runs out of guesses
    private boolean correctlyGuessedPhrase; // boolean to check if entire phrase is correct
    private ArrayList<String> masterPhraseList = new HangmanPhrases().masterPhraseList; // copies phrase list into control
    private Stack randomizedPhraseStack = new Stack();


    /**
     * Control file constructor acts as a driver... activates model and view,
     * sets initial values for any instance variables
     */
    public Hangman() {
        generatePhraseStack();

        this.model = new Phrase(String.valueOf(randomizedPhraseStack.pop())); // pops off top of stack into phrase

        this.view = new HangmanUI(this);
        view.setVisible(true);

        this.guessesRemaining = 6; // 7 guesses per round in non zero based counting
        this.correctlyGuessedPhrase = false;
        this.gameOver = false;
    }

    public String getCurrentPhraseForDisplay() { return PrintyShortcuts.charToString(this.model.getPhraseWithBlanks()); }

    public boolean getPhraseState() { return model.getPhraseUpdated(); } // returns display ready phrase updated based on previous guess

    public int getGuessesRemaining() { return this.guessesRemaining; } // return number of guesses to control hangman art (console or in GUI)

    public boolean getPhraseAccuracy() { return this.correctlyGuessedPhrase; } // returns boolean to determine if the user correctly guessed the phrase

    public boolean getGameOver() { return this.gameOver; } // returns boolean either that the game is continuing or it is over

    public void activateConsole() { this.console = new HangmanConsole(this); } // "connects" control to HangmanConsole through callbacks

    /**
     * Starts a new round by creating a fresh new Phrase object
     */
    public void startNextRound() { // when the game is over, a new phrase is created to guess
        this.model = new Phrase(String.valueOf(randomizedPhraseStack.pop()));

        // resets control variables
        this.guessesRemaining = 6; // 6 guesses per round in non zero based counting
        this.correctlyGuessedPhrase = false;
        this.gameOver = false;
    }

    /**
     * Checks if the guessed letter is one or more of the letters in the phrase;
     * @param letter
     */
    public void checkLetter(char letter) {
        char[] currentPhrase = model.getRandomPhrase();
        char[] phraseWithBlanks = model.getPhraseWithBlanks();
        model.setPhraseUpdateState(false); // resets tracking when a player guesses a new letter

        for (int i = 0; i < currentPhrase.length; i++) {
           if (letter == currentPhrase[i]) {
               phraseWithBlanks[i] = letter; // adds current letter to blank phrase
               model.setPhraseUpdateState(true); // updates tracking boolean
           }
        }

        boolean phraseUpdated = model.getPhraseUpdated(); // assignment to local variable for clarity
        if (phraseUpdated == true) { // simple catch to avoid unnecessarily updating the blank phrase
            model.updatePhraseWithBlanks(phraseWithBlanks); // updates current model phrase with blanks to include correctly guess letters
            checkEntirePhraseAccuracy(); // checks guessed phrase to entire reference phrase to see if they match
        } else {
            this.guessesRemaining -= 1; // wrong letter guessed = one limb on the hangman
            if (this.guessesRemaining == 0) {
                this.gameOver = true;
            }
        }
    }

    /**
     * Simple check to test if the phrase guessed is the same as the reference phrase
     */
    private void checkEntirePhraseAccuracy() {
        // pulls current phrase that the user guessed and the entire reference phrase that the user is guessing
        String referencePhrase = new String (model.getRandomPhrase()).toUpperCase();
        String currentPhrase = new String(model.getPhraseWithBlanks()).toUpperCase();
        if (currentPhrase.equals(referencePhrase)) {
            this.correctlyGuessedPhrase = true;
        }
    }

    /**
     * Generates a randomized stack by randomizing an index in the reference array list, pushing randomized phrase into stack,
     * then deleting element in array list that was just pushed
     */
    public void generatePhraseStack() {
        Random randomizer = new Random();
        while (masterPhraseList.size() != 0){ // repeats process as long as there are elements in the array list
            int bound = masterPhraseList.size();
            int index = randomizer.nextInt(bound); // find a randomized index in the index of the array list
            randomizedPhraseStack.push(masterPhraseList.get(index)); // push randomized element
            masterPhraseList.remove(index); // delete current element
        }
    }
}
