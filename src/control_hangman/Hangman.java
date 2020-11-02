package control_hangman;

import model_hangman.Phrase;
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

    /**
     * Checks if the guessed letter is one or more of the letters in the phrase;
     * @param letter
     */
    public void checkLetter(char letter) {
        char[] currentPhrase = model.getRandomPhrase();
        char[] phraseWithBlanks = model.getPhraseWithBlanks();
        boolean phraseUpdated = false;

        for (int i = 0; i < currentPhrase.length; i++) {
           if (letter == currentPhrase[i]) {
               phraseWithBlanks[i] = letter; // adds current letter to blank phrase
           phraseUpdated = true;
           }
        }
        if (phraseUpdated == true){ // simple catch to avoid unnecessarily updating the blank phrase
            model.updatePhraseWithBlanks(phraseWithBlanks); // updates current model phrase with blanks to include correctly guess letters

        }
    }
}
