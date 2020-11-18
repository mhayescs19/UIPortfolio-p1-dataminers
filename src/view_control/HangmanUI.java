/*

    Created by Michael Hayes
    2020

 */

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
    private JLabel phraseLabel = new JLabel();
    private JLabel hangmanDisplay;
    private JButton[] trackAlphabetButtons = new JButton[26];


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
        phraseLabel = new JLabel(control.getCurrentPhraseForDisplay());
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
        int j = 0; // counter to load buttons into array to reference attributes from non button events
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
                // visual text update of phrase
                String updatedPhrase = control.getCurrentPhraseForDisplay();
                phraseLabel.setText(updatedPhrase);
                // visual update of button appearance and functionality (disabling interaction)
                updateButtonAppearance(new_button, control.getPhraseState());
                updateHangmanVisual(control, hangmanDisplay);
            });
            new_button.setBounds(190 + xCoordinate, yCoordinate, 30, 30);
            hangmanFrame.add(new_button);
            xCoordinate += 40; // set x coordinate for next alphabet button (b) 40px to the right
            trackAlphabetButtons[j] = new_button;
            i++;
            j++;
        }
        /**
         * Image Display
         * 1. JLabel manages display of hangman states (more limbs as user guesses more wrong)
         */
        ImageIcon image = new ImageIcon("src/images/hangman_initial.png");
        hangmanDisplay = new JLabel(image);
        hangmanDisplay.setBounds(-10, -2, 400, 600);
        hangmanFrame.add(hangmanDisplay);

        JButton button_newPhrase = new JButton("New Phrase"); // text displayed on button
        button_newPhrase.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button_newPhrase.setBackground(Color.decode("#9E9E9E"));
            } // change color to get an interaction

            @Override
            public void mouseReleased(MouseEvent e) {
                button_newPhrase.setBackground(Color.WHITE);
            }
        });
        button_newPhrase.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#9DAF9B")));
        button_newPhrase.setOpaque(true);
        button_newPhrase.setForeground(Color.decode("#2B425B"));
        button_newPhrase.setFont(new Font("Gilroy-ExtraBold", Font.PLAIN, 14));
        button_newPhrase.setBackground(Color.WHITE);
        button_newPhrase.addActionListener(e -> {
            prepareForNewPhrase(control); // clean reset of UI
        });
        button_newPhrase.setBounds(600, 350, 100, 20);
        hangmanFrame.add(button_newPhrase);
    }

    /**
     * UI Update Blocks
     * updateButtonAppearance
     * 1. Called after a letter is guessed
     * 2. Updates button appearance to reflect a guess (button becomes static [button cannot be pressed])
     * 3. Accuracy of guess either results in the button appearance shifting to red (incorrect) or green (correct)
     */
    private void updateButtonAppearance(JButton alphabetButton, boolean correct) {
        alphabetButton.setEnabled(false); // disables interaction with button

        if (correct == true) {
            alphabetButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#3CB371")));
            alphabetButton.setForeground(Color.decode("#2B425B"));
            alphabetButton.setBackground(Color.decode("#3CB371"));
        } else {
            alphabetButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#FA8072")));
            alphabetButton.setForeground(Color.decode("#2B425B"));
            alphabetButton.setBackground(Color.decode("#B22222"));
        }

    }

    private void updateHangmanVisual(Hangman control, JLabel hangmanDisplay) {
        int hangmanImageIndex = 6 - control.getGuessesRemaining(); // array size of image icons = 5, subtract current guesses remaining to get proper array index... possible place for stack use?

        if (control.getGameOver() == false) {
            ImageIcon currentHangmanState = new ImageIcon(HangmanImage.filePath[hangmanImageIndex]);
            hangmanDisplay.setIcon(currentHangmanState);
        } else { // visual updates and locking of interation to signal end of game
            phraseLabel.setText("Game Over!");
            ImageIcon currentHangmanState = new ImageIcon(HangmanImage.filePath[6]);
            hangmanDisplay.setIcon(currentHangmanState);
            disableButtons();
        }

    }

    /**
     * Clean reset of all of the visuals when the game starts / a new phrase is selected
     */
    public void prepareForNewPhrase(Hangman control) {
        for (JButton button : trackAlphabetButtons) {
            // resets mutated button properties (re-enables interaction, resets color)
            button.setEnabled(true);
            button.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#9DAF9B")));
            button.setOpaque(true);
            button.setForeground(Color.decode("#2B425B"));
            button.setFont(new Font("Gilroy-ExtraBold", Font.PLAIN, 25));
            button.setBackground(Color.WHITE);
            button.setEnabled(true);
        }
        // resets image display
        ImageIcon image = new ImageIcon("src/images/hangman_initial.png");
        hangmanDisplay.setIcon(image);
        // fires off code in MVC to set up new phrase
        control.startNextRound();
        // updates visual of phrase in UI
        String updatedPhrase = control.getCurrentPhraseForDisplay();
        phraseLabel.setText(updatedPhrase);
    }

    /**
     * Simple diable loop to eliminate any unhandled button input
     */
    private void disableButtons() {
        for (JButton button : trackAlphabetButtons) {
            button.setEnabled(true);
        }
    }

}

