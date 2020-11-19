package view_control;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConverterHub extends JFrame
{
    private void OpenTempConverter()
    {
        TemperatureConverterUI tempConverter = new TemperatureConverterUI();
        tempConverter.setLocation(850,100);
        tempConverter.setVisible(true);
    }
    private void OpenSpeedConverter()
    {

        SpeedConversions speedConverter = new SpeedConversions();
        speedConverter.setLocation(475,100);
        speedConverter.setVisible(true);
    }

    public static void main(String[] args)
    {
        view_control.ConverterHub frame = new view_control.ConverterHub();
        frame.setVisible(true);
    }
    public ConverterHub()
    {

        setBackground(Color.decode("#4C4C4C"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setBounds(100, 100, 418, 340); // -25
        int offset = 25;

        setTitle("Converter Hub");
        setSize(377,300);

        JButton button_1 = new JButton("Temperature Converter");
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
        button_1.setOpaque(true);
        button_1.setForeground(Color.WHITE);
        button_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        button_1.setBackground(Color.decode("#B4B4B4"));
        button_1.setBounds(0, offset, 368, 50);
        getContentPane().add(button_1);
        button_1.addActionListener(e -> OpenTempConverter());
        JButton button_2 = new JButton("Speed Converter");
        button_2.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                button_2.setBackground(Color.decode("#9E9E9E"));
            } // change color to get an interaction
            @Override
            public void mouseReleased(MouseEvent e)
            {
                button_2.setBackground(Color.decode("#B4B4B4"));
            }
        });
        button_2.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        button_2.setOpaque(true);
        button_2.setForeground(Color.WHITE);
        button_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        button_2.setBackground(Color.decode("#B4B4B4"));
        button_2.setBounds(0, 50 + offset, 368, 50);
        button_2.addActionListener(e -> OpenSpeedConverter());
        getContentPane().add(button_2);

    }

}
