package control_hangman;

import model_hangman.Phrase;
import view_control.HangmanUI;

public class Hangman {

    Phrase model;
    HangmanUI view;

    public Hangman() {
        this.model = new Phrase();
        this.view = new HangmanUI();
        view.setVisible(true);
    }
}
