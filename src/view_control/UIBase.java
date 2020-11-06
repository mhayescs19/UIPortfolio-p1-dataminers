package view_control;

import Conversions.TemperatureConversions;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIBase extends JFrame{

    public int TypeNumbers(KeyEvent e, boolean ReadyToConvert, boolean decimal,boolean number, JLabel field){
        int pass = 0;
        if (!ReadyToConvert) {
            if((e.getKeyCode() <= 105 && e.getKeyCode() >= 96) || (e.getKeyCode() <= 57 && e.getKeyCode() >= 48)){
                String temp = "" +e.getKeyChar();
                type(temp,0, number, field);
                pass =1;
            }
            else if (!decimal && (e.getKeyCode() == 190 || e.getKeyCode() == 110)){
                String temp = "." ;
                type(temp,0, number, field);

                pass = 2;
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
        return pass;
    }
    public void type(String text, int op, boolean number, JLabel field){
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
    public void defineButton(JButton jbutton, int offset, int operation, int columm, int maxcolumms,boolean ReadyToConvert, boolean decimal, boolean number, JLabel field){
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
        jbutton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                TypeNumbers(e,ReadyToConvert, decimal,number,field);
            }
        });
        jbutton.addActionListener(e-> requestFocus());

        jbutton.setOpaque(true);
        jbutton.setForeground(Color.WHITE);
        jbutton.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        jbutton.setBackground(Color.decode("#B4B4B4"));
        jbutton.setBounds(0+((columm-1)*368/maxcolumms), 167 + offset, 368/maxcolumms, 50);
        getContentPane().add(jbutton);
    }

}
