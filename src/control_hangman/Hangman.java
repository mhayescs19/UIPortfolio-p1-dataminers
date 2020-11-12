/*

    Created by Michael Hayes
    2020

 */

package control_hangman;

import model_hangman.Phrase;
import model_linkedlists.Stack;
import util.HangmanPhrases;
import util.PrintyShortcuts;
import view_control.HangmanUI;

import java.util.ArrayList;
import java.util.Random;

public class Hangman {

    public Phrase model;
    public HangmanUI view;

    private int guessesRemaining; // counter to limit number of guesses for word
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

        guessesRemaining = 5; // 6 guesses per round in non zero based counting
    }

    public String getCurrentPhraseForDisplay() { return PrintyShortcuts.charToString(this.model.getPhraseWithBlanks()); }

    public boolean getPhraseState() { return model.getPhraseUpdated(); } // returns display ready phrase updated based on previous guess

    public int getGuessesRemaining() { return this.guessesRemaining; }

    public void resetGuessesRemaining() { this.guessesRemaining = 5; }

    public void startNextRound() { // when the game is over, a new phrase is created to guess
        this.model = new Phrase(String.valueOf(randomizedPhraseStack.pop()));
        resetGuessesRemaining();
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
        } else {
            guessesRemaining -= 1; // wrong letter guessed = one limb on the hangman
        }
    }


    /**
     * Generates a randomized stack by randomizing an index in the reference array list, pushing randomized phrase into stack,
     * then deleting element in array list that was just pushed
     */
    public void generatePhraseStack()
    {
        Random randomizer = new Random();
        while (masterPhraseList.size() != 0){ // repeats process as long as there are elements in the array list
            int bound = masterPhraseList.size();
            int index = randomizer.nextInt(bound); // find a randomized index in the index of the array list
            randomizedPhraseStack.push(masterPhraseList.get(index)); // push randomized element
            masterPhraseList.remove(index); // delete current element
        }
    }
}
