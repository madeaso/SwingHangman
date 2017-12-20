/*************************************************************** 
* File: SudokuController.java 
* Author: Marc Deaso 
* Class: CS 245 - Programming Graphical User Interfaces
* 
* Assignment: Swing Project V1.2 
* Date last modified: 10/23/2016
* 
* Purpose: This class is the controller representation of the sudoku game screen.
* Contains logic for the game using the SudokuPanel class. 
* 
****************************************************************/
package main.java.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.java.SwingProject;
import main.java.view.SudokuPanel;

public class SudokuController {
    private SudokuPanel panel;
    private MainFrameController rootController;
    
    private int[][] solution;
    private boolean[][] shownInitial;
    private boolean[][] beenWrong;
    private LocalDateTime dateTime;
    
    private int sudokuScore;
    
    //Default constructor not supported
    public SudokuController() {
        throw(new UnsupportedOperationException("Default constructor not supported."));
    }
    
    //SudokuController class constructor.
    public SudokuController(SudokuPanel panel, MainFrameController rootController) {
        this.panel = panel;
        this.rootController = rootController;
        
        this.solution = new int[][]{
            {8, 3, 5, 4, 1, 6, 9, 2, 7},
            {2, 9, 6, 8, 5, 7, 4, 3, 1},
            {4, 1, 7, 2, 9, 3, 6, 5, 8},
            {5, 6, 9, 1, 3, 4, 7, 8, 2},
            {1, 2, 3, 6, 7, 8, 5, 4, 9},
            {7, 4, 8, 5, 2, 9, 1, 6, 3},
            {6, 5, 2, 7, 8, 1, 3, 9, 4},
            {9, 8, 1, 3, 4, 5, 2, 7, 6},
            {3, 7, 4, 9, 6, 2, 8, 1, 5}
        };
        
        this.shownInitial = new boolean[][] {
            {true, false, false, true, false, true, false, false, true},
            {false, false, false, false, false, false, true, false, false},
            {false, true, false, false, false, false, true, true, false},
            {true, false, true, false, true, false, true, true, false},
            {false, false, false, false, true, false, false, false, false},
            {false, true, true, false, true, false, true, false, true},
            {false, true, true, false, false, false, false, true, false},
            {false, false, true, false, false, false, false, false, false},
            {true, false, false, true, false, true, false, false, true}
        };
        
        this.beenWrong = new boolean[9][9];
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                this.beenWrong[col][row] = false;
            }
        }
        
        this.sudokuScore = 540;
        
        setup();
    }
    
    //Method: setup()
    //Purpose: Sets text and adds appropriate action listeners to SudokuPanel
    //         components.
    private void setup() {
        panel.getSudokuLabel().setText("Sudoku");
        panel.getSudokuLabel().setFont(new Font("Impact", Font.PLAIN,24));
        panel.getSudokuLabel().setForeground(new Color(255,30,30));
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM-dd-uuuu hh:mm:ss a");
        panel.addAncestorListener(new AncestorListener(){
            @Override
            public void ancestorAdded(AncestorEvent event) {
                Timer clock = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setDateTime();
                        panel.setDateTime(dtf.format(dateTime));
                    }
                });
                clock.setInitialDelay(0);
                clock.setRepeats(true);
                clock.start();
                
                for (int col = 0; col < 9; col++) {
                    for (int row = 0; row < 9; row++) {
                        if (shownInitial[col][row]) {
                            panel.getSudokuBoard()[col][row].setText(String.valueOf(solution[col][row]));
                            panel.getSudokuBoard()[col][row].setEnabled(false);
                        }
                        else {
                            panel.getSudokuBoard()[col][row].setText("");
                        }
                    }
                }
            }
            
            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
            
        });
        
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                final int currentCol = col;
                final int currentRow = row;
                
                panel.getSudokuBoard()[col][row].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                    }
                    
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                    }
                    
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        checkInput(currentCol, currentRow);
                    }
                });
            }
        }
        
        panel.getSubmitButton().setText("Submit");
        panel.getQuitButton().setText("Quit");
        
        panel.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkSolution();
            }
        });
        
        panel.getQuitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudokuScore = 0;
                rootController.changeVisibleCard(SwingProject.GAME_OVER_KEY);
            }
        });
    }
    
    public JPanel getPanel() {
        return this.panel;
    }
    
    public int[][] getSolution() {
        return this.solution;
    }
    
    private void setDateTime() {
        this.dateTime = LocalDateTime.now();
    }
    
    public int getScore() {
        return this.sudokuScore;
    }
    
    public void setScore(int score) {
        this.sudokuScore = score;
    }
    
    //Method: checkInput(int, int)
    //Purpose: Checks user input in each text field on the game board.
    private void checkInput(int col, int row) {
        Runnable doCheck = new Runnable() {
            @Override
            public void run() {
                int in;
                try {
                    in = Integer.parseInt(panel.getSudokuBoard()[col][row].getText());
                    if (in < 0 || in > 9) {
                        panel.getSudokuBoard()[col][row].setText("");
                        JOptionPane.showMessageDialog(null, "Please enter a number between 1-9.");
                    }
                } catch (NumberFormatException e) {
                    panel.getSudokuBoard()[col][row].setText("");
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1-9.");
                    return;
                }
            }
        };
        SwingUtilities.invokeLater(doCheck);
    }
    
    //Method: checkSolution()
    //Purpose: Invoked when the user completes the puzzle, checks game solution,
    //         and deducts appropriate amount of points as needed.
    private void checkSolution() {
        boolean again = false;
        int amountWrong = 0;
        int pointsLost = 0;
        
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                if (panel.getSudokuBoard()[col][row].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a number in all spaces.");
                    return;
                }
            }
        }
        
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                if (Integer.parseInt(panel.getSudokuBoard()[col][row].getText()) != this.solution[col][row]) {
                    again = true;
                    amountWrong++;
                    if(!this.beenWrong[col][row]) {
                        this.sudokuScore -= 10;
                        pointsLost += 10;
                        this.beenWrong[col][row] = true;
                    }
                }
            }
        }
        
        if (!again) {
            //Player is correct.
            JOptionPane.showMessageDialog(null, "Solution is correct!");
            rootController.changeVisibleCard(SwingProject.GAME_OVER_KEY);
        }
        else {
            //Player is wrong.
            JOptionPane.showMessageDialog(null, "Solution is incorrect. Please try again.\n"
                    + "Incorrect squares: " + amountWrong + ", Points lost: " + pointsLost + ".");
        }
    }
    
    //Method: resetGame()
    //Purpose: Reinitializes game board and local variables to allow multiple
    //         plays in a single session.
    public void resetGame() {
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                if (shownInitial[col][row]) {
                    panel.getSudokuBoard()[col][row].setText(String.valueOf(solution[col][row]));
                    panel.getSudokuBoard()[col][row].setEnabled(false);
                }
                else {
                    panel.getSudokuBoard()[col][row].setText("");
                }
                this.beenWrong[col][row] = false;
            }
        }
        sudokuScore = 540;
    }
}
