package model_hangman;

import util.PrintyShortcuts;

import java.util.Random;
import java.util.Scanner;

public class Phrase {

    private char[] currentPhrase;
    private char[] displayedPhrase;
    private boolean phraseUpdated;

    public static void main(String[] args) {

    }

    public Phrase(String phrase) { // when the game starts there always needs to be a phrase to guess
        createPhrase(phrase);
        createPhraseWithBlanks();
        //PrintyShortcuts.println(PrintyShortcuts.charToString(currentPhrase));
        //PrintyShortcuts.println(PrintyShortcuts.charToString(displayedPhrase));
    }

    public void createPhrase(String phrase) { // pulls random phrase from master array into a char array
        currentPhrase = phrase.toCharArray();
        PrintyShortcuts.println(PrintyShortcuts.charToString(currentPhrase));
    }

    public void createPhraseWithBlanks() { this.displayedPhrase = convertPhrasetoBlanks(); } // builds a blank version of the phrase th

    public char[] getRandomPhrase() { // simple getter of random phrase
        return this.currentPhrase;
    }

    public char[] getPhraseWithBlanks() { // simple getter of current phrase with blanks (hidden for guessing)
        return this.displayedPhrase;
    }

    public void updatePhraseWithBlanks(char[] updatedPhrase) { // when a user guesses the correct letter, the phrase with blanks updates to add guesses letter to phrase
        this.displayedPhrase = updatedPhrase;
    }

    public void setPhraseUpdateState(boolean newState) { this.phraseUpdated = newState; }

    public boolean getPhraseUpdated() { return this.phraseUpdated; }

    private char[] convertPhrasetoBlanks() { // converts current phrase to a displayable char array with blanks for the view
         char[] tempPhraseWithBlanks = new char[currentPhrase.length];
         for (int i = 0; i < currentPhrase.length; i++) {
             if (currentPhrase[i] != ' ') {
                 tempPhraseWithBlanks[i] = '_'; // every letter switches to underscore
             } else {
                 tempPhraseWithBlanks[i] = ' '; // all non letters in phrase remain a space
             }
         }
         return tempPhraseWithBlanks;
    }

    /**
     * @Deprecated
     */
    /*private String generateRandomPhrase() { // simple selection of a random phrase based on index
        Random randomizer = new Random();
        int bound = masterPhraseList.length;
        int i = randomizer.nextInt(bound);

        String randomPhrase = masterPhraseList[i];

        PrintyShortcuts.println(randomPhrase);
        return randomPhrase;

    }*/
}



