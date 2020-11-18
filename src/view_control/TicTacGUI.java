package view_control;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This is the GUI version.
 */

public class TicTacGUI extends JPanel
{
    JButton[] buttons = new JButton[9];
    int alternate = 0;

    public TicTacGUI()
    {
        setLayout(new GridLayout(3,3));
        initializebuttons();
    }

    public void initializebuttons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].addActionListener(new buttonListener());

            add(buttons[i]);
        }
    }
    public void resetButtons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i].setText("");
        }
    }

    private class buttonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

            JButton buttonClicked = (JButton)e.getSource();
            if(alternate%2 == 0)
                buttonClicked.setText("X");
            else
                buttonClicked.setText("O");

            if(checkForWin())
            {
                JOptionPane.showConfirmDialog(null, "Did you win?");
                resetButtons();
            }

            alternate++;

        }

        public boolean checkForWin()
        {

            if( checkAdjacent(0,1) && checkAdjacent(1,2) )
                return true;
            else if( checkAdjacent(3,4) && checkAdjacent(4,5) )
                return true;
            else if ( checkAdjacent(6,7) && checkAdjacent(7,8))
                return true;


            else if ( checkAdjacent(0,3) && checkAdjacent(3,6))
                return true;
            else if ( checkAdjacent(1,4) && checkAdjacent(4,7))
                return true;
            else if ( checkAdjacent(2,5) && checkAdjacent(5,8))
                return true;


            else if ( checkAdjacent(0,4) && checkAdjacent(4,8))
                return true;
            else return checkAdjacent(2, 4) && checkAdjacent(4, 6);


        }

        public boolean checkAdjacent(int a, int b)
        {
            return buttons[a].getText().equals(buttons[b].getText()) && !buttons[a].getText().equals("");
        }

    }

    public static void main(String[] args)
    {
        JFrame window = new JFrame("Tic Tac Toe");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.getContentPane().add(new TicTacGUI());
        window.setBounds(300,200,300,300);
        window.setVisible(true);
    }
}