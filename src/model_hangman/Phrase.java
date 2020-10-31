package model_hangman;

import util.PrintyShortcuts;

import java.util.Random;
import java.util.Scanner;

public class Phrase {
    String[] masterPhraseList = new String[]{"computer science a", "coronavirus pandemic", "intellij idea"}; // current "data"

    private char[] currentPhrase;
    private char[] displayedPhrase;

    public static void main(String[] args) {

    }

    public Phrase() { // when the game starts there always needs to be a phrase to guess
        createRandomPhrase();
        createPhraseWithBlanks();
        PrintyShortcuts.println(PrintyShortcuts.charToString(currentPhrase));
        PrintyShortcuts.println(PrintyShortcuts.charToString(displayedPhrase));
    }

    public void createRandomPhrase() { // pulls random phrase from master array into a char array
        currentPhrase = generateRandomPhrase().toCharArray();
        PrintyShortcuts.println(PrintyShortcuts.charToString(currentPhrase));
    }

    public void createPhraseWithBlanks() {
        displayedPhrase = convertPhrasetoBlanks();
    }

    public char[] getRandomPhrase() { // simple getter of random phrase
        return currentPhrase;
    }

    public char[] getPhraseWithBlanks() { // simple getter of current phrase with blanks (hidden for guessing)
        return displayedPhrase;
    }

    public void updatePhraseWithBlanks(char[] updatedPhrase) { // when a user guesses the correct letter, the phrase with blanks updates to add guesses letter to phrase
        displayedPhrase = updatedPhrase;
    }

    private String generateRandomPhrase() { // simple selection of a random phrase based on index
        Random randomizer = new Random();
        int bound = masterPhraseList.length;
        int i = randomizer.nextInt(bound);

        String randomPhrase = masterPhraseList[i];

        PrintyShortcuts.println(randomPhrase);
        return randomPhrase;

    }
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
}
