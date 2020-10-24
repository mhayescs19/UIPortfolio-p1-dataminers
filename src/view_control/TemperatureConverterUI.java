package view_control;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverterUI extends JFrame {
    NumberFormatter digitsOnly = new NumberFormatter();//long list of swearwords on this subject

    private final JLabel field = new JLabel("Type the number you want converted here");
    private void updateCalcArea(String text){
        field.setText(text);
    }

    public static void main(String[] args) {
        TemperatureConverterUI frame = new TemperatureConverterUI();
        frame.setVisible(true);
    }
    public TemperatureConverterUI(){
        getContentPane().setBackground(Color.decode("#4C4C4C"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setBounds(100, 100, 418, 340); // -25
        int offset = 25;

        //NumberFormatter digitsOnly = new NumberFormatter();
        //digitsOnly.setAllowsInvalid(false);

        setTitle("Temperature Converter");
        setSize(377,300);
        field.setBackground(Color.decode("#B4B4B4"));
        field.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        field.setBounds(0, 0 , 368, 75);
        getContentPane().add(field);
            field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    updateCalcArea("WHYYYY");
                }
            });

        JButton button_1 = new JButton("Celsius"); // text displayed on button
        button_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button_1.setBackground(Color.decode("#9E9E9E"));
            } // change color to get an interaction
            @Override
            public void mouseReleased(MouseEvent e) {
                button_1.setBackground(Color.decode("#B4B4B4"));
            }
        });
        button_1.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        button_1.addActionListener(e -> updateCalcArea(button_1.getText()));
        button_1.setOpaque(true);
        button_1.setForeground(Color.WHITE);
        button_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        button_1.setBackground(Color.decode("#B4B4B4"));
        button_1.setBounds(0, 167 + offset, 368, 50);
        getContentPane().add(button_1);

        JButton button_2 = new JButton("Farenheit"); // text displayed on button
        button_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button_2.setBackground(Color.decode("#9E9E9E"));
            } // change color to get an interaction
            @Override
            public void mouseReleased(MouseEvent e) {
                button_2.setBackground(Color.decode("#B4B4B4"));
            }
        });
        button_2.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        button_2.setOpaque(true);
        button_2.setForeground(Color.WHITE);
        button_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        button_2.setBackground(Color.decode("#B4B4B4"));
        button_2.setBounds(0, 117 + offset, 368, 50);
        getContentPane().add(button_2);

        JButton button_3 = new JButton("Kelvin"); // text displayed on button
        button_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button_3.setBackground(Color.decode("#9E9E9E"));
            } // change color to get an interaction
            @Override
            public void mouseReleased(MouseEvent e) {
                button_3.setBackground(Color.decode("#B4B4B4"));
            }
        });
        button_3.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        button_3.setOpaque(true);
        button_3.setForeground(Color.WHITE);
        button_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        button_3.setBackground(Color.decode("#B4B4B4"));
        button_3.setBounds(0, 67 + offset, 368, 50);
        getContentPane().add(button_3);
    }


}

class InitializeTemperatureConverter {
    public void OpenConverter(){
        TemperatureConverterUI tempConverter = new TemperatureConverterUI();
        tempConverter.setVisible(true);
    }

}
