package view_control;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

public class NavigationMenu extends JFrame{
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NavigationMenu frame = new NavigationMenu();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public NavigationMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);

        // JLabel with image
        JLabel homeSplashScreen = new JLabel("Period 1 Dataminers");
        homeSplashScreen.setFont(new Font("Gilroy-ExtraBold", Font.PLAIN, 30));
        homeSplashScreen.setForeground(Color.WHITE);
        homeSplashScreen.setBounds(900, 300, 100, 50);
        getContentPane().add(homeSplashScreen);

        // Content Panel to add Label and Image
        //https://docs.oracle.com/javase/tutorial/uiswing/components/rootpane.html
        Container content = getContentPane();
        content.setBackground(Color.decode("#6084A3"));//9DAF9B


        // Menu Objects
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnHone = new JMenu("Home");
        menuBar.add(mnHone);

        JMenuItem mntmPreferences = new JMenuItem("Preferences");
        mnHone.add(mntmPreferences);

        JMenuItem mntmConsole = new JMenuItem("Console");
        mnHone.add(mntmConsole);

        JMenuItem mntmUI = new JMenuItem("UI");
        mnHone.add(mntmUI);

        JMenu mnDemos = new JMenu("Demos");
        menuBar.add(mnDemos);

        JMenuItem mntmScientificCalc = new JMenuItem("Scientific Calculator");
        mntmScientificCalc.addActionListener(e -> {

        });
        mnDemos.add(mntmScientificCalc);

        JMenuItem mntmNotePad = new JMenuItem("Notepad");
        mntmNotePad.addActionListener(e -> {

        });
        mnDemos.add(mntmNotePad);

        JMenuItem mntmTicTacToe = new JMenuItem("TicTacToe");
        mntmTicTacToe.addActionListener(e -> {

        });
        mnDemos.add(mntmTicTacToe);

        JMenuItem mntmHangman = new JMenuItem("Hangman");
        mntmHangman.addActionListener(e -> {

        });
        mnDemos.add(mntmHangman);

        JMenu mnPairShare = new JMenu("Pair Share Labs");
        menuBar.add(mnPairShare);

        JMenuItem mntmPeggHayes = new JMenuItem("Pegg, Hayes");
        mnPairShare.add(mntmPeggHayes);

        JMenuItem mntmSimpleCalc = new JMenuItem("Simple Calculator");
        mntmSimpleCalc.addActionListener(e -> {
            CalculatorUI frame = new CalculatorUI();
            frame.setVisible(true);
        });
        mnPairShare.add(mntmSimpleCalc);
    }
}
