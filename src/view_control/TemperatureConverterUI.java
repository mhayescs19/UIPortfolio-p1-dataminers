package view_control;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;

import Conversions.TemperatureConversions.TempConversions;
import Conversions.TemperatureConversions;

public class TemperatureConverterUI extends JFrame {

    boolean number =false;
    boolean ReadyToConvert = false;
    boolean decimal = false;
    private Conversions.TemperatureConversions Tconv;
    private double tempin;
    private double tempt;

    private double tempF;
    private int op1;
    private int op2;

    private void TypeNumbers(KeyEvent e){
        if (!ReadyToConvert) {
            if((e.getKeyCode() <= 105 && e.getKeyCode() >= 96) || (e.getKeyCode() <= 57 && e.getKeyCode() >= 48)){
                String temp = "" +e.getKeyChar();
                updateCalcArea(temp,0);
                number = true;
            }
            else if (!decimal && (e.getKeyCode() == 190 || e.getKeyCode() == 110)){
                String temp = "." ;
                updateCalcArea(temp,0);
                decimal = true;
            }
            else if ((e.getKeyCode() == 8)){
                String temp = field.getText();
                int length = temp.length();
                String text;
                if(length > 0) { text = temp.substring(0,(length-1));}
                else
                     text = "";
                field.setText(text);
            }
        }
    }
    private void defineButton(JButton jbutton, int offset,int operation){
        jbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                jbutton.setBackground(Color.decode("#9E9E9E"));
            } // change color to get an interaction
            @Override
            public void mouseReleased(MouseEvent e) {
                jbutton.setBackground(Color.decode("#B4B4B4"));
            }
        });
        jbutton.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        jbutton.addActionListener(e -> updateCalcArea(jbutton.getText(),operation));
        jbutton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                TypeNumbers(e);
            }
        });
        jbutton.setOpaque(true);
        jbutton.setForeground(Color.WHITE);
        jbutton.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        jbutton.setBackground(Color.decode("#B4B4B4"));
        jbutton.setBounds(0, 167 + offset, 368, 50);
        getContentPane().add(jbutton);
    }


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
            field.setText(currentS + "-> "+ tempF +"° "+ text);
            ReadyToConvert = false;
            decimal = false;
            number = false;


        }
        else{
            if(number){
                String currentString = field.getText();
                field.setText(currentString + text);
            }
            else{
                field.setText("");
                field.setText(text);
            }
            //Create a new string, add it here
        }
    }

    public static void main(String[] args) {
        TemperatureConverterUI frame = new TemperatureConverterUI();
        frame.setVisible(true);

    }
    public TemperatureConverterUI(){
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            TypeNumbers(e);
            }
        });
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
        //Possible method: Filter text box after clicking, use text field, regex[a-zA-Z]

        JButton button_1 = new JButton("Celsius"); // text displayed on button
        defineButton(button_1, offset,1);

        JButton button_2 = new JButton("Farenheit"); // text displayed on button
        defineButton(button_2 ,-25,2);

        JButton button_3 = new JButton("Kelvin"); // text displayed on button
        defineButton(button_3, -75,3);
    }


}

