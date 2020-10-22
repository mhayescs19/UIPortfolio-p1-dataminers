package view_control;

import control_hangman.Hangman;

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
        JButton button_a = new JButton("A"); // text displayed on button
        button_a.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button_a.setBackground(Color.decode("#9E9E9E"));
            } // change color to get an interaction
            @Override
            public void mouseReleased(MouseEvent e) {
                button_a.setBackground(Color.decode("#B4B4B4"));
            }
        });
        button_a.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#9DAF9B")));
        button_a.setOpaque(true);
        button_a.setForeground(Color.decode("#2B425B"));
        button_a.setFont(new Font("Gilroy-ExtraBold", Font.PLAIN, 25));
        button_a.setBackground(Color.WHITE);
        //button_a.addActionListener(/*e -> updateCalcArea(button_1.getText())*/);
        button_a.setBounds(200, 400, 30, 30);
        hangmanFrame.add(button_a);

        JButton button_b = new JButton("B"); // text displayed on button
        button_b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button_b.setBackground(Color.decode("#9E9E9E"));
            } // change color to get an interaction
            @Override
            public void mouseReleased(MouseEvent e) {
                button_b.setBackground(Color.decode("#B4B4B4"));
            }
        });
        button_b.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#9DAF9B")));
        button_b.setOpaque(true);
        button_b.setForeground(Color.decode("#2B425B"));
        button_b.setFont(new Font("Gilroy-ExtraBold", Font.PLAIN, 25));
        button_b.setBackground(Color.WHITE);
        //button_b.addActionListener(/*e -> updateCalcArea(button_1.getText())*/);
        button_b.setBounds(240, 400, 30, 30);
        hangmanFrame.add(button_b);
    }
}
