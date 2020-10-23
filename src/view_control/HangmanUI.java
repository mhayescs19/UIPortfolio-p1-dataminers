package view_control;

import control_hangman.Hangman;
import util.PrintyShortcuts;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class HangmanUI extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HangmanUI frame = new HangmanUI();
                frame.setVisible(true); // inherited from JFrame "magic method"
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public HangmanUI() {
        /*
         * Creates frame
         */
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        getContentPane().setLayout(null); // code declaration equivalent to GUI form layout
        Container hangmanFrame = getContentPane();
        hangmanFrame.setBackground(Color.decode("#6084A3")); // color of main frame

        /*
         * Alphabet buttons
         */
        String[] alphabetButtons = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int xCoordinate = 200; // starting x position of first alphabet button (a)
        int yCoordinate = 400; // starting y position of first alphabet button (a)
        int i = 0; // counter tracking number of buttons drawn on screen
        /* For loop explanation
         * 1. Cycle through entire alphabetButton array storing alphabet letters, creating a button for each letter
         * 2. Use variable "letter" to set corresponding letter to each button
         */
        for (String letter : alphabetButtons) {
            if (i == 12) { // after letter l, start a new line of the alphabet buttons
                yCoordinate += 40;
                xCoordinate = 160;
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
            new_button.addActionListener(e -> PrintyShortcuts.print(new_button.getText()));
            new_button.setBounds(xCoordinate, yCoordinate, 30, 30);
            hangmanFrame.add(new_button);
            xCoordinate += 40; // set x coordinate for next alphabet button (b) 40px to the right
            i++;
        }
    }
}
