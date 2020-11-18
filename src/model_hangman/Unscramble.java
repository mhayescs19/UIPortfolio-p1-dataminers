package model_hangman;

import java.util.Random;
import java.util.Scanner;

class Unscramble {

    private String[] words = new String[]{"apple", "orange", "modulo", "print", "hello"};
    private String word;
    private char[] scrambledWord;
    private boolean wordCorrect = false;


    public Unscramble() {

        generateWord();
        scramble(this.word);
        begin();
        playGame();

    }


    public void generateWord() {
        Random randomizer = new Random();
        int bound = this.words.length;
        int i = randomizer.nextInt(bound);

        String randomWord = words[i];

        this.word = randomWord;
    }

    public void scramble(String currentWord) {
        char[] scrambled = currentWord.toCharArray();
        //char[] wordInChar = currentWord.toCharArray();
        int length = currentWord.length() -1;
        for (int i = length; i >= 0; i--) {
            Random randomizer = new Random();
            int bound = currentWord.length();
            int j = randomizer.nextInt(bound);
            int h = randomizer.nextInt(bound);
            char temp = scrambled[j];
            char swap = scrambled[h];

            scrambled[j] = swap;
            scrambled[h] = temp;
        }

        this.scrambledWord = scrambled;

    }

    public void begin() {
        System.out.println("Welcome to Iteration'y'");
        System.out.println("Unscramble the word by typing two numbers");
        System.out.println("1) the location of the letter you want to replace");
        System.out.println("2) the location of the letter you want to swap");

    }

    public void playGame() {
        Scanner scan = new Scanner(System.in);
        int f, s;
        while (this.wordCorrect != true) {
            System.out.println(new String(this.scrambledWord));
            System.out.println("Location of letter to replace");
            f = scan.nextInt() - 1;
            System.out.println("Location of letter to swap");
            s = scan.nextInt() - 1;

            char temp = this.scrambledWord[f];
            char swap = this.scrambledWord[s];

            this.scrambledWord[f] = swap;
            this.scrambledWord[s] = temp;

            String currentScramble = new String(this.scrambledWord);
            if (currentScramble.equals(this.word)) {
                this.wordCorrect = true;
            }
        }

        System.out.println("Congratulations you unscrambled the word " + "'" + this.word + "'");

    }

    public static void main(String[] args) {
        new Unscramble();
    }
}
