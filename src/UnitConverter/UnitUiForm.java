package UnitConverter;
/*
Readme
for the this converter to work you must enter in decimal number ie 1.0 or 512.123
for all convert you must use enter to save the text, if you used an illegal character the program will throw an error in console telling you somthing went wrong.
ctrl plus Z = clear text shortcut
*/

import javax.swing.*;
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

    private JButton consoleVersionButton;


    public UnitUiForm() {

        fToCButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textareadouble = convert.FtoC();
                SetText();
            }
        });
        inchesToFeetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textareadouble = convert.ItoF();
                SetText();
            }
        });
        feetInchesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textareadouble = convert.Ftoi();
                SetText();
            }
        });
        meeterToCentimeterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textareadouble = convert.MeeterToCm();
                SetText();
            }
        });
        CMToMeeterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textareadouble = convert.CentimeterToMeeter();
                SetText();
            }
        });
        cToFButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               textareadouble = convert.CToF();
               SetText();
            }

        });

        enterNumberHereTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    TextAeraParse();
                }
                if(e.getKeyCode()==KeyEvent.VK_Z && e.isControlDown()) //cool key combo system that checks for control key is pressed
                {
                    ClearText();
                }
            }
        });

        consoleVersionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    ConsoleUi();
                } catch (Exception exception) {
                    exception.printStackTrace();
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
public void ConsoleUi() throws Exception {
    UnitConsole var = new UnitConsole();
    var.ConsoleUi();
}
public void ClearText()
{
    enterNumberHereTextArea.setText("");
}

 public static void FrameStart() {
     JFrame frame = new JFrame("UnitUiForm");
     frame.setContentPane(new UnitUiForm().panel1);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(800,400);
     frame.setLocation(1250,740);
     frame.setVisible(true);
 }
 public void SetText()
 {
     enterNumberHereTextArea.setText(String.valueOf(textareadouble));
     /*
     method to set the label to value of the text area double.
     */
 }




}
