package view_control;

import UnitConverter.UnitUiForm;
import control_hangman.Hangman;

import javax.swing.*;
import java.awt.*;

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
        /*
         * Sets up frame
         */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        getContentPane().setLayout(null); // code declaration equivalent to GUI form layout
        Container mainFrame = getContentPane();
        mainFrame.setBackground(Color.decode("#6084A3")); // color of main frame

        // JLabel with image
        JLabel homeSplashScreen = new JLabel("<HTML><center>Period 1 Dataminers<br>UI Portfolio</center><HTML>"); // HTML code to create a multi line label
        homeSplashScreen.setFont(new Font("Gilroy-ExtraBold", Font.BOLD, 30));
        homeSplashScreen.setForeground(Color.WHITE);
        homeSplashScreen.setBounds(290, 225, 900, 100);
        getContentPane().add(homeSplashScreen);

        /*
         * Sets up main menu objects in bar
         */
        JMenuBar menuBar = new JMenuBar(); // JMenuBar = main menu bar
        setJMenuBar(menuBar);

        JMenu mnHone = new JMenu("Home"); // JMenu = "columns" of menu bar
        menuBar.add(mnHone);

        JMenuItem mntmPreferences = new JMenuItem("Preferences WIP"); // JMenuItem = "rows" of menu bar
        mnHone.add(mntmPreferences);

        JMenuItem mntmConsole = new JMenuItem("Console WIP");
        mnHone.add(mntmConsole);

        JMenuItem mntmUI = new JMenuItem("UI WIP");
        mnHone.add(mntmUI);

        JMenu mnDemos = new JMenu("Demos");
        menuBar.add(mnDemos);

        JMenuItem mntmScientificCalc = new JMenuItem("Scientific Calculator");
        mntmScientificCalc.addActionListener(e -> {

        });
        mnDemos.add(mntmScientificCalc);

        JMenuItem mntmNotePad = new JMenuItem("Notepad");
        mntmNotePad.addActionListener(e -> {
            new notepad.NotepadGUI();
        });
        mnDemos.add(mntmNotePad);

        JMenuItem mntmTicTacToe = new JMenuItem("TicTacToe");
        mntmTicTacToe.addActionListener(e -> {
            TicTacGUI.main(null);
        });
        mnDemos.add(mntmTicTacToe);

        JMenuItem mntmHangman = new JMenuItem("Hangman");
        mntmHangman.addActionListener(e -> {
            Hangman run = new Hangman(); // runs Hangman view control file
        });
        mnDemos.add(mntmHangman);
        JMenuItem mntmUnitconverter = new JMenuItem("Unit converter");
        mntmUnitconverter.addActionListener(e->{
            UnitUiForm.FrameStart();
        });
        mnDemos.add(mntmUnitconverter);

        /*
         * Pair Share submenu
         *
         * Normal Menu                          Submenus
         * JMenuBar                             JMenuBar
         * JMenu       JMenu      JMenu         JMenu (with sub    JMenu(norm) JMenu
         * JMenuItem   JMenuItem  JMenuItem     JMenu JMenuItem    JMenuItem   JMenuItem
         *             JMenuItem                      JMenuItem    JMenuItem
         *                                      JMenu JMenuItem
         *                                            JMenuItem
         */

        JMenu mnPairShare = new JMenu("Pair Share Labs"); // "column" in main bar
        menuBar.add(mnPairShare);

        JMenu mnPeggHayes = new JMenu("Pegg, Hayes"); // "column" as a row under Pair Share "column"
        mnPairShare.add(mnPeggHayes); // Why? normal "rows" under menu are created by JMenuItems


        JMenuItem mntmSimpleCalcPH = new JMenuItem("Simple Calculator"); // first "row" under Pegg, Hayes submenu
        mntmSimpleCalcPH.addActionListener(e -> {
            CalculatorUI frame = new CalculatorUI();
            frame.setVisible(true);
        });
        mnPeggHayes.add(mntmSimpleCalcPH); // add to closet JMenu (acting as the row[Pegg, Hayes]) under JMenu [Pair Share}

        JMenu mnChangPhungRamsayer = new JMenu("Chang, Phung, Ramsayer");
        mnPairShare.add(mnChangPhungRamsayer);

        JMenuItem mntmDominicCalc = new JMenuItem("Dominic's Calculator");
        mntmDominicCalc.addActionListener(e -> new DominicCalculator().setVisible(true));
        mnChangPhungRamsayer.add(mntmDominicCalc);

        JMenuItem mntmJasonCalc = new JMenuItem("Jason's Calculator");
        mntmJasonCalc.addActionListener(e -> {

        });
        mnChangPhungRamsayer.add(mntmJasonCalc);

        JMenuItem mntmDavidCalc = new JMenuItem("David's Calculator");
        mntmDavidCalc.addActionListener(e -> {

        });
        mnChangPhungRamsayer.add(mntmDavidCalc);
    }
}
