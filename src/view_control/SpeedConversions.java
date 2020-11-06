package view_control;


import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Conversions.SpeedConversionMath;
import Conversions.SpeedConversionMath.SpdConversion;

public class SpeedConversions extends UIBase {

        boolean number =false;
        boolean ReadyToConvert = false;
        boolean decimal = false;
        private SpeedConversionMath Spconv;
        private double speedIn;
        private double speedT;

        private double speedF;
        private int op1;
        private int op2;




        private final JLabel field = new JLabel("Type the number you want converted here");
        private void updateCalcArea(String text, int op){
            if (op >= 1 && !ReadyToConvert){
                if(!number) {
                    speedIn = 0;
                    field.setText("0 " + text);

                }
                else{
                    speedIn = Float.parseFloat(field.getText());
                    String currentS = field.getText();
                    field.setText(currentS + " " + text);
                }
                op1 = op;
                ReadyToConvert = true;
            }
            else if (op >= 1 && ReadyToConvert){
                op2 = op;
                if(speedIn == 0){
                    speedF = speedIn;
                }
                else {
                    switch (op1) {
                        case 1:
                            switch (op2) {
                                case 1:
                                    speedF = speedIn;
                                    break;
                                case 2:
                                    speedF = Spconv.Speed(speedIn, SpdConversion.MphtoKph);
                                    break;
                                case 3:
                                    speedF = Spconv.Speed(speedIn, SpdConversion.MphtoKnot);
                                    break;
                                case 4:
                                    speedF = Spconv.Speed(speedIn, SpdConversion.MphtoMeterPerSecond);
                                    break;
                                case 5:
                                    speedF = Spconv.Speed(speedIn, SpdConversion.MphtoFps);
                            }
                            break;
                        case 2:
                            switch (op2) {
                                case 1:
                                    speedT = Spconv.Speed((1f/speedIn), SpdConversion.MphtoKph);
                                    speedF = 1f / speedT;
                                    break;
                                case 2:
                                    speedF = speedIn;
                                    break;
                                case 3:
                                    speedF = Spconv.Speed(speedIn, SpdConversion.KphtoKnots);
                                    break;
                                case 4:
                                    speedF = Spconv.Speed(speedIn, SpdConversion.KphtoMetersPerSecond);
                                    break;
                                case 5:
                                    speedT = Spconv.Speed((1f/speedIn), SpdConversion.FeetperSecondtoKph);
                                    speedF = 1f / speedT;
                                    break;
                            }
                            break;
                        case 3:
                            switch (op2) {
                                case 1:
                                    speedT = Spconv.Speed((1f/speedIn), SpdConversion.MphtoKnot);
                                    speedF = 1f / speedT;
                                    break;
                                case 2:
                                    speedT = Spconv.Speed((1f/speedIn), SpdConversion.KphtoKnots);
                                    speedF = 1f / speedT;
                                    break;
                                case 3:
                                    speedF = speedIn;
                                    break;
                                case 4:
                                    speedT = Spconv.Speed((1f/speedIn), SpdConversion.MphtoKnot);
                                    speedF = Spconv.Speed((1f / speedT), SpdConversion.MphtoMeterPerSecond);
                                    break;
                                case 5:
                                    speedT = Spconv.Speed((1f/speedIn), SpdConversion.KphtoKnots);
                                    speedF = 1f/(Spconv.Speed(speedT, SpdConversion.FeetperSecondtoKph));

                                    break;
                            }
                            break;
                        case 4:
                            switch (op2) {
                                case 1:
                                    speedT = Spconv.Speed((1f/speedIn), SpdConversion.MphtoMeterPerSecond);
                                    speedF = 1f/speedT;
                                    break;
                                case 2:
                                    speedT = Spconv.Speed((1f/speedIn), SpdConversion.KphtoMetersPerSecond);
                                    speedF = 1f / speedT;
                                    break;
                                case 3:
                                    speedT = Spconv.Speed((1f/speedIn), SpdConversion.MphtoMeterPerSecond);
                                    speedF = Spconv.Speed((1 / speedT), SpdConversion.MphtoKnot);
                                    break;
                                case 4:
                                    speedF = speedIn;
                                    break;
                                case 5:
                                    speedT = Spconv.Speed((1f/speedIn), SpdConversion.MphtoMeterPerSecond);
                                    speedF = Spconv.Speed((1f/ speedT), SpdConversion.MphtoFps);
                                    break;
                            }
                        case 5:
                            switch (op2) {
                                case 1:
                                    speedF = 1f / (Spconv.Speed((1f/speedIn), SpdConversion.MphtoFps));
                                    break;
                                case 2:
                                    speedF = Spconv.Speed(speedIn, SpdConversion.FeetperSecondtoKph);
                                    break;
                                case 3:
                                    speedT = Spconv.Speed(speedIn, SpdConversion.FeetperSecondtoKph);
                                    speedF = Spconv.Speed(speedT, SpdConversion.KphtoKnots);
                                    break;
                                case 4:
                                    speedT = Spconv.Speed(speedIn, SpdConversion.FeetperSecondtoKph);
                                    speedF = Spconv.Speed(speedT, SpdConversion.KphtoMetersPerSecond);
                                    break;
                                case 5:
                                    speedF = speedIn;
                                    break;
                            }

                    }
                }
                String currentS = field.getText();
                field.setText(currentS + "-> "+ speedF + text);
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
            view_control.SpeedConversions frame = new view_control.SpeedConversions();
            frame.setVisible(true);

        }
        public SpeedConversions(){
            setFocusable(true);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int pass = TypeNumbers(e,ReadyToConvert, decimal,number,field);
                    if (pass == 1)
                        number = true;
                    else if (pass == 2)
                        decimal = true;

                }
            });
            getContentPane().setBackground(Color.decode("#4C4C4C"));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            setBounds(100, 100, 418, 340); // -25
            int offset = 25;

            //NumberFormatter digitsOnly = new NumberFormatter();
            //digitsOnly.setAllowsInvalid(false);

            setTitle("Speed Converter");
            setSize(377,300);
            field.setBackground(Color.decode("#B4B4B4"));
            field.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            field.setBounds(0, 0 , 368, 75);
            getContentPane().add(field);
            //Possible method: Filter text box after clicking, use text field, regex[a-zA-Z]

            JButton button_1 = new JButton("Mph"); // text displayed on button
            defineButton(button_1, offset,1,1,2, ReadyToConvert, decimal,number,field);
            button_1.addActionListener(e -> updateCalcArea(button_1.getText(),1));


            JButton button_2 = new JButton("Kph"); // text displayed on button
            defineButton(button_2 ,-25,2,1,2, ReadyToConvert, decimal,number,field);
            button_2.addActionListener(e -> updateCalcArea(button_2.getText(),2));


            JButton button_3 = new JButton("Knots"); // text displayed on button
            defineButton(button_3, -75,3, 1,2,ReadyToConvert, decimal,number,field);
            button_3.addActionListener(e -> updateCalcArea(button_3.getText(),3));

            JButton button_4 = new JButton("m/s");
            defineButton(button_4, offset , 4,2,2,ReadyToConvert,decimal,number,field);
            button_4.addActionListener(e -> updateCalcArea(button_4.getText(),4));

            JButton button_5 = new JButton("ft/s");
            defineButton(button_5, -25, 5,2,2,ReadyToConvert,decimal,number,field);
            button_5.addActionListener(e -> updateCalcArea(button_5.getText(),5));
        }


    }


