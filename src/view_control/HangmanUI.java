package view_control;

import control_hangman.Hangman;
import util.HangmanImage;
import util.PrintyShortcuts;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class HangmanUI extends JFrame {
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                //HangmanUI frame = new HangmanUI();
                //frame.setVisible(true); // inherited from JFrame "magic method"
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Real definitions
     */
    JLabel phraseLabel = new JLabel();

    /**
     * Tester definitions
     */
    private String[] hangmanImagePaths = new String[7];
    int i = 1;

    public void simulateGuess(JLabel image) {
        if (i == 7) {
            i = 0;
        }
        ImageIcon currentHangmanState = new ImageIcon(HangmanImage.filePath[i]);
        image.setIcon(currentHangmanState);
        PrintyShortcuts.println(HangmanImage.filePath[i]);
        i += 1;
    }

    public HangmanUI(Hangman control) {
        /**
         * Creates frame
         */
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        getContentPane().setLayout(null); // code declaration equivalent to GUI form layout
        Container hangmanFrame = getContentPane();
        hangmanFrame.setBackground(Color.decode("#6084A3")); // color of main frame


        /**
         * Phrase Display Label
         */
        phraseLabel = new JLabel(PrintyShortcuts.charToString(control.model.getPhraseWithBlanks()));
        phraseLabel.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#9DAF9B")));
        phraseLabel.setOpaque(true);
        phraseLabel.setForeground(Color.decode("#2B425B"));
        phraseLabel.setFont(new Font("Gilroy-ExtraBold", Font.PLAIN, 35));
        phraseLabel.setBounds(395, 100, 500, 100);
        phraseLabel.setBackground(Color.WHITE);
        phraseLabel.setHorizontalAlignment(SwingConstants.LEFT);
        getContentPane().add(phraseLabel);

        /**
         * Alphabet buttons
         */
        String[] alphabetButtons = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int xCoordinate = 200; // starting x position of first alphabet button (a)
        int yCoordinate = 400; // starting y position of first alphabet button (a)
        int i = 0; // counter tracking number of buttons drawn on screen

        /* For loop explanation
         * 1. Cycle through entire alphabetButton array storing alphabet letters, creating a button for each letter
         * 2. Use variable "letter" to set corresponding letter to each button
         * 3. Improvement = put buttons into an ArrayList since it is impossible to change attributes after creation
         */
        for (String letter : alphabetButtons) {
            if (i == 13) { // after letter l, start a new line of the alphabet buttons
                yCoordinate += 40;
                xCoordinate = 200; //160
            }
            JButton new_button = new JButton(letter.toUpperCase()); // text displayed on button
            new_button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    new_button.setBackground(Color.decode("#9E9E9E"));
                } // change color to get an interaction

                @Override
                public void mouseReleased(MouseEvent e) {
                    new_button.setBackground(Color.WHITE);
                }
            });
            new_button.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#9DAF9B")));
            new_button.setOpaque(true);
            new_button.setForeground(Color.decode("#2B425B"));
            new_button.setFont(new Font("Gilroy-ExtraBold", Font.PLAIN, 25));
            new_button.setBackground(Color.WHITE);
            new_button.addActionListener(e -> {
                char currentLetter = new_button.getText().toLowerCase().charAt(0);
                System.out.println(currentLetter);
                control.checkLetter(currentLetter);

                String updatedPhrase = PrintyShortcuts.charToString(control.model.getPhraseWithBlanks());
                phraseLabel.setText(updatedPhrase);

            });
            new_button.setBounds(190 + xCoordinate, yCoordinate, 30, 30);
            hangmanFrame.add(new_button);
            xCoordinate += 40; // set x coordinate for next alphabet button (b) 40px to the right
            i++;
        }
        /**
         * Image Display Exploration
         * 1. GOAL= practice switching icons of labels triggered by buttons
         */
        ImageIcon image = new ImageIcon("src/images/hangman_initial.png");
        JLabel hangmanDisplay = new JLabel(image);
        hangmanDisplay.setBounds(-10, -2, 400, 600);
        hangmanFrame.add(hangmanDisplay);

        JButton button_sim_guess = new JButton("guess tester"); // text displayed on button
        button_sim_guess.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button_sim_guess.setBackground(Color.decode("#9E9E9E"));
            } // change color to get an interaction

            @Override
            public void mouseReleased(MouseEvent e) {
                button_sim_guess.setBackground(Color.WHITE);
            }
        });
        button_sim_guess.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#9DAF9B")));
        button_sim_guess.setOpaque(true);
        button_sim_guess.setForeground(Color.decode("#2B425B"));
        button_sim_guess.setFont(new Font("Gilroy-ExtraBold", Font.PLAIN, 14));
        button_sim_guess.setBackground(Color.WHITE);
        button_sim_guess.addActionListener(e -> simulateGuess(hangmanDisplay)); // test to check button values
        button_sim_guess.setBounds(600, 350, 100, 20);
        hangmanFrame.add(button_sim_guess);

    }

    /**
     * UI Update Blocks
     */

}

