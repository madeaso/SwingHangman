/*************************************************************** 
* File: CircleGameController.java 
* Author: Marc Deaso 
* Class: CS 245 - Programming Graphical User Interfaces
* 
* Assignment: Swing Project V1.2 
* Date last modified: 10/23/2016
* 
* Purpose: This class is the view representation of the sudoku game screen.
* Displays necessary components of sudoku game. 
* 
****************************************************************/
package main.java.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SudokuPanel extends JPanel {
    private JLabel sudokuLabel;
    private JLabel dateTimeLabel;
    private JButton submitButton;
    private JButton quitButton;
    
    private JTextField[][] board;
    private JPanel[][] threeBy;
    private JPanel gameArea;
    
    private int sudokuGameScore;
    
    //SudokuPanel default constructor.
    public SudokuPanel() {
        sudokuLabel = new JLabel("Placeholder");
        dateTimeLabel = new JLabel("Placeholder");
        submitButton = new JButton("Placeholder");
        submitButton.setToolTipText("Submit");
        quitButton = new JButton("Placeholder");
        quitButton.setToolTipText("Quit Game");
        
        board = new JTextField[9][9];
        threeBy = new JPanel[3][3];
        gameArea = new JPanel();
        
        sudokuGameScore = 540;
        initComponents();
    }
    
    //Method: initComponents()
    //Purpose: Adds components to sudoku screen using a BoarderLayout as the
    //         main container.
    private void initComponents() {
        this.setLayout(new BorderLayout());
        gameArea.setLayout(new GridLayout(3, 3));
        
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                threeBy[col][row] = new JPanel(new GridLayout(3, 3));
                threeBy[col][row].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
                //threeBy[col][row].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gameArea.add(threeBy[col][row]);
            }
        }
        
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                board[col][row] = new JTextField();
                board[col][row].setFont(new Font("Arial", Font.BOLD, 18));
                board[col][row].setHorizontalAlignment(JTextField.CENTER);
                board[col][row].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                threeBy[col/3][row/3].add(board[col][row]);
                board[col][row].setToolTipText("Enter a number from 1-9");
            }
        }
        
        //Top area
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.X_AXIS));
        topContainer.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        topContainer.add(sudokuLabel);
        topContainer.add(new JPanel());
        topContainer.add(dateTimeLabel);
        this.add(topContainer, BorderLayout.NORTH);
        
        //Left area
        JPanel leftContainer = new JPanel();
        leftContainer.setBorder(new EmptyBorder(40,40,40,40));
        this.add(leftContainer, BorderLayout.WEST);
        
        //Right area
        JPanel rightContainer = new JPanel();
        rightContainer.setLayout(new BoxLayout(rightContainer, BoxLayout.Y_AXIS));
        rightContainer.add(new JPanel());
        submitButton.setAlignmentX(CENTER_ALIGNMENT);
        //submitButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        quitButton.setAlignmentX(CENTER_ALIGNMENT);
        //quitButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rightContainer.add(submitButton);
        rightContainer.add(quitButton);
        this.add(rightContainer, BorderLayout.EAST);
        
        //Center area
        this.add(gameArea, BorderLayout.CENTER);
    }
    
    public JLabel getSudokuLabel() {
        return sudokuLabel;
    }
    
    public void setDateTime(String dateTime){
        dateTimeLabel.setText(dateTime);
    }
    
    public JButton getSubmitButton() {
        return this.submitButton;
    }
    
    public JButton getQuitButton() {
        return this.quitButton;
    }
    
    public JTextField[][] getSudokuBoard() {
        return board;
    }
}
