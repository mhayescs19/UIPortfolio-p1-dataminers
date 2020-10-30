package model_hangman;

import java.util.Random;

public class Phrase {
    String[] masterPhraseList = new String[]{"computer science a", "coronavirus pandemic", "intellij idea"};

    private char[] currentPhrase;

    public Phrase() { // when the game starts there always needs to be a phrase to guess
        setRandomPhrase();
    }

    public void setRandomPhrase() { // pulls random phrase from master array into a char array
        currentPhrase = generateRandomPhrase().toCharArray();
    }

    private String generateRandomPhrase() { // simple selection of a random phrase based on index
        Random randomizer = new Random();
        int bound = masterPhraseList.length;
        int i = randomizer.nextInt(bound);

        String randomPhrase = masterPhraseList[i];

        return randomPhrase;

    }
}
