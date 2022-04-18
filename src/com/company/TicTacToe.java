package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class TicTacToe extends JFrame implements ActionListener {
    //Instance Variables

    private JFrame window = new JFrame("Tic-Tac-Toe");
    private JButton buttons[] = new JButton[9];

    JMenuBar menu = new JMenuBar();
    JMenuItem newGame = new JMenuItem("New Game"),
            name = new JMenuItem("Change Name"),
            instr = new JMenuItem("Instructions"),
            by = new JMenuItem("By"),
            exit = new JMenuItem("Exit");

    private int[][] winCombinations = new int[][]{
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal wins
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //vertical wins
            {0, 4, 8}, {2, 4, 6} //diagonal wins
    };

    private int count = 0;
    private String letter = "";
    private boolean win = false;

    static String x = "X";
    static String o = "O";


    public TicTacToe() {
        //Create Window
        window.setSize(500, 500);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(3, 3));
        window.setVisible(true);

        //Add Buttons To The Window
        for (int i = 0; i <= 8; i++) {
            buttons[i] = new JButton();
            window.add(buttons[i]);
            buttons[i].addActionListener(this);
        }

        menu.add(newGame);
        menu.add(name);
        menu.add(instr);
        menu.add(by);
        menu.add(exit);

        name.addActionListener(this);
        newGame.addActionListener(this);
        instr.addActionListener(this);
        by.addActionListener(this);
        exit.addActionListener(this);

        window.setJMenuBar(menu);


    }

    public void setName() {
        x = JOptionPane.showInputDialog(null, "Enter Name of player X: ", "X", 1);
        o = JOptionPane.showInputDialog(null, "Enter Name of player O: ", "O", 1);


        if (x == null)          //it works when i press cancel or press red x
        {
            x = "X";
        }
        if (o == null) {
            o = "O";
        }


        if (x.length() == 0)      //it works when i press ok without typing any name
        {
            x = "X";
        }
        if (o.length() == 0) {
            o = "O";
        }

    }

    @Override
    public void actionPerformed(ActionEvent a) {
        Object source = a.getSource();  // getSource means the Enter Button On Keyboard

        if (source == newGame) {
            int answer = JOptionPane.showConfirmDialog(null, "Your current game will not be saved...\nContinue Anyways ?", "Do you want to start a new game?", JOptionPane.YES_NO_OPTION, 2);

            if (answer == JOptionPane.YES_OPTION) {
                this.clearIt();
            }

        } else if (source == name) {
            this.setName();
        } else if (source == instr) {
            JOptionPane.showMessageDialog(null, "Your goal is to be the first player to get 3 X's or O's in a row. (horizontally, diagonally, or vertically)", "Instructions", JOptionPane.INFORMATION_MESSAGE); //JOptionPane.INFORMATION_MESSAGE   is the same meaning as   1
        } else if (source == exit) {

            JOptionPane.showMessageDialog(null, "Thank you " + x + " and " + o + " for playing", "Exit !!", 1);
            System.exit(0);

        } else if (source == by) {
            JOptionPane.showMessageDialog(null, "This Program Is Made By :\n Ahmed Abdelsalam Elgamal", "Developer", JOptionPane.INFORMATION_MESSAGE);
        } else {
            count++;

            /*Calculate whose turn it is*/
            if (count % 2 == 0) {
                letter = "O";
            } else {
                letter = "X";
            }

            /*Write the letter to the button and deactivate it*/

            JButton pressedButton = (JButton) a.getSource();// means : when you press on the button

            pressedButton.setText(letter);
            pressedButton.setBackground(Color.WHITE);
            pressedButton.setForeground(Color.blue);
            pressedButton.setEnabled(false);
            // to let the button as if it is pressed but the foreground color wont work well
            // but it is useful to avoid double click issue


            /*Determine who won*/
            for (int i = 0; i <= 7; i++) {
                if (buttons[winCombinations[i][0]].getText().equals(buttons[winCombinations[i][1]].getText()) &&
                        buttons[winCombinations[i][1]].getText().equals(buttons[winCombinations[i][2]].getText()) &&
                        buttons[winCombinations[i][0]].getText() != "")  // or [i][1]  or [i][2]
                {

                    win = true;

                    buttons[winCombinations[i][0]].setBackground(Color.BLUE);
                    buttons[winCombinations[i][1]].setBackground(Color.BLUE);
                    buttons[winCombinations[i][2]].setBackground(Color.BLUE);

                    buttons[winCombinations[i][0]].setForeground(Color.red);
                    buttons[winCombinations[i][1]].setForeground(Color.red);
                    buttons[winCombinations[i][2]].setForeground(Color.red);

                }
            }

            /*Show a dialog when game is over*/
            if (win == true) {
                if (letter.equals("X"))
                    letter = x;
                else
                    letter = o;


                int answer = JOptionPane.showConfirmDialog(null, letter + "  Is Winner!\nDo You Want To Try Again ?", "WIN!!", JOptionPane.YES_NO_OPTION);

                if (answer == JOptionPane.YES_OPTION) {
                    this.clearIt();
                } else {
                    JOptionPane.showMessageDialog(null, "Thank you " + x + " and " + o + " for playing", "Exit !!", 1);
                    System.exit(0);
                }

            } else if (count == 9 && win == false) {

                int answer = JOptionPane.showConfirmDialog(null, "No Winner Player !!\nDo You Want To Start a New Game ?", "Draw !!", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    this.clearIt();
                } else {
                    JOptionPane.showMessageDialog(null, "Thank you " + x + " and " + o + " for playing", "Exit !!", 1);
                    System.exit(0);
                }
            }
        }
    }


    public void clearIt() {
        window.setVisible(false);
        this.startIt();
    }

    public void startIt() {
        new TicTacToe();
    }

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        t.setName();
    }
}
 