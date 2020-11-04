package control_hangman;

import model_hangman.Phrase;
import util.PrintyShortcuts;
import view_control.HangmanUI;

public class Hangman {

    public Phrase model;
    public HangmanUI view;

    private boolean letterCorrect; // structure of tracking which letters remain
    /**
     * Control file constructor acts as a driver... activates model and view
     */
    public Hangman() {
        this.model = new Phrase();
        this.view = new HangmanUI(this);
        view.setVisible(true);
    }

    public String getCurrentPhraseForDisplay() { return PrintyShortcuts.charToString(this.model.getPhraseWithBlanks()); }

    public boolean getPhraseState() { return model.getPhraseUpdated(); } // returns display ready phrase updated based on previous guess

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
        }
    }
}
