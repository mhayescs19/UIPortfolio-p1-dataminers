package view_control;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;

import Conversions.TemperatureConversions.TempConversions;
import Conversions.TemperatureConversions;

public class TemperatureConverterUI extends JFrame {

    boolean number =false;
    boolean ReadyToConvert = false;
    private Conversions.TemperatureConversions Tconv;
    private double tempin;
    private double tempt;

    private double tempF;
    private int op1;
    private int op2;



    NumberFormatter digitsOnly = new NumberFormatter();//long list of swearwords on this subject

    private final JLabel field = new JLabel("Type the number you want converted here");
    private void updateCalcArea(String text, int op){
        if (op >= 1 && !ReadyToConvert){
            if(!number) {
                tempin = 0;
                field.setText("0° " + text);

            }
            else{
                tempin = Float.parseFloat(field.getText());
                String currentS = field.getText();
                field.setText(currentS + "° "+ text);
            }
            op1 = op;
            ReadyToConvert = true;
        }
        else if (op >= 1 && ReadyToConvert){
            op2 = op;

            switch(op1){
                case 1:
                    switch(op2){
                        case 1:
                            tempF = tempin;
                            break;
                        case 2:
                            tempF = Tconv.Temperature(tempin, TempConversions.CelsiustoFarenheit);
                            break;
                        case 3:
                            tempF = Tconv.Temperature(tempin,TempConversions.CelsiusToKelvin);
                            break;
                    }
                break;
                case 2:
                    switch(op2){
                        case 1:
                            tempF = Tconv.Temperature(tempin,TempConversions.FarenheitToCelsius);
                            break;
                        case 2:
                             tempF = tempin;
                             break;
                        case 3:
                            tempt = Tconv.Temperature(tempin,TempConversions.FarenheitToCelsius);
                            tempF = Tconv.Temperature(tempt, TempConversions.CelsiusToKelvin);
                            break;
                    }
                break;
                case 3:
                    switch(op2){
                        case 1:
                            tempF = Tconv.Temperature(tempin,TempConversions.KelvinToCelsius);
                            break;
                        case 2:
                            tempt = Tconv.Temperature(tempin,TempConversions.KelvinToCelsius);
                            tempF = Tconv.Temperature(tempt, TempConversions.CelsiustoFarenheit);
                            break;
                    }
                break;
            }
            String currentS = field.getText();

            field.setText(currentS + "-> "+Double.toString(tempF) +"° "+ text);
            ReadyToConvert = false;

        }
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
                    if (!ReadyToConvert) {
                        updateCalcArea("WHYYYY", 0);
                        number = true;
                    }
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
        button_1.addActionListener(e -> updateCalcArea(button_1.getText(),1));
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
        button_2.addActionListener(e -> updateCalcArea(button_2.getText(),2));
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
        button_3.addActionListener(e -> updateCalcArea(button_3.getText(),3));
        button_3.setOpaque(true);
        button_3.setForeground(Color.WHITE);
        button_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        button_3.setBackground(Color.decode("#B4B4B4"));
        button_3.setBounds(0, 67 + offset, 368, 50);
        getContentPane().add(button_3);
    }


}

