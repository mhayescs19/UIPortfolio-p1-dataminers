package control_hangman;

import model_hangman.Phrase;
import util.PrintyShortcuts;
import view_control.HangmanUI;

public class Hangman {

    public Phrase model;
    public HangmanUI view;

    private int guessesRemaining; // counter to limit number of guesses for word
    /**
     * Control file constructor acts as a driver... activates model and view,
     * sets initial values for any instance variables
     */
    public Hangman() {
        this.model = new Phrase();
        this.view = new HangmanUI(this);
        view.setVisible(true);

        guessesRemaining = 5; // 6 guesses per round in non zero based counting
    }

    public String getCurrentPhraseForDisplay() { return PrintyShortcuts.charToString(this.model.getPhraseWithBlanks()); }

    public boolean getPhraseState() { return model.getPhraseUpdated(); } // returns display ready phrase updated based on previous guess

    public int getGuessesRemaining() { return this.guessesRemaining; }
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
}
