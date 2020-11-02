package UnitConverter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UnitUiForm {
    UnitControll convert = new UnitControll();
    private double textareadouble;
    private JTextArea enterNumberHereTextArea;
    private JPanel panel1;
    private JButton inchesToFeetButton;
    private JButton feetInchesButton;
    private JButton meeterToCentimeterButton;
    private JButton cToFButton;
    private JButton CMToMeeterButton;
    private JButton fToCButton;

    public UnitUiForm() {
        fToCButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textareadouble = convert.FtoC();
                enterNumberHereTextArea.setText(Double.toString(textareadouble));
            }
        });
        inchesToFeetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textareadouble = convert.ItoF();
                enterNumberHereTextArea.setText(Double.toString(textareadouble));
            }
        });
        feetInchesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textareadouble = convert.Ftoi();
                enterNumberHereTextArea.setText(Double.toString(textareadouble));
            }
        });
        meeterToCentimeterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        CMToMeeterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        cToFButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               textareadouble = convert.CToF();
               enterNumberHereTextArea.setText(Double.toString(textareadouble));
            }

        });

        enterNumberHereTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    TextAeraParse();
                }
            }
        });
    }

    public static void main(String[] args) {
      FrameStart();
    }
    public void TextAeraParse()
    {   try {
        this.textareadouble = Double.parseDouble(enterNumberHereTextArea.getText());
        convert.Arg1Setter(this.textareadouble);
        System.out.println("text registered");
    }
    catch (Exception e)
    {
        throw new NumberFormatException("do not use illegal characters, only . is allowed");
    }

    }

 public static void FrameStart() {
     JFrame frame = new JFrame("UnitUiForm");
     frame.setContentPane(new UnitUiForm().panel1);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(800,400);
     frame.setLocation(1250,740);
     frame.setVisible(true);
 }


}
