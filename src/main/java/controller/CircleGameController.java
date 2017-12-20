/*************************************************************** 
* File: CircleGameController.java 
* Author: Omar Rodriguez 
* Class: CS 245 - Programming Graphical User Interfaces
* 
* Assignment: Swing Project V1.1 
* Date last modified: 10/23/2016
* 
* Purpose: This class is the controller representation of the circle game screen.
* Contains logic for the game using the CircleGamePanel class.
* 
* V1.2: Changed following page to switch to sudoku after game is complete.
* 
* 
****************************************************************/
package main.java.controller;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import main.java.SwingProject;
import main.java.view.CircleGamePanel;
import main.java.view.ColorButton;

/**
 *
 * @author Omar
 */
public class CircleGameController {
    private final int MAX_CIRCLE_DIAMETER = 120;
    
    private CircleGamePanel panel;
    private MainFrameController rootController;
    
    public CircleGameController(CircleGamePanel panel, MainFrameController rootController){
        this.panel = (CircleGamePanel) panel;
        this.rootController = rootController;
        setup();
    }
    
    private void setup(){
        panel.setLayout(null); // absolute positioning for this window
        panel.setSize(600, 400);
        panel.setRandomGoal(); //This should set a random font color and random color text for the label. It should also update a currentColorGoal variable
        panel.setCurrentRound(1); // Make sure set to round 1 for a new game
        
        ColorButton[] colorButtons = panel.getColorButtons();
        for(ColorButton jb : colorButtons){
            jb.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(panel.getCurrentGoal().equals(jb.getColor())){
                        for(ColorButton button : colorButtons){
                            button.setEnabled(false);
                        }
                        panel.setGoalLabelText("RIGHT!");
                        Timer timer = new Timer(500, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(panel.getCurrentRound() > 5){
                                    rootController.changeVisibleCard(SwingProject.SUDOKU_KEY);
                                }
                                panel.setRandomGoal();
                                for(ColorButton button : colorButtons){
                                    button.setEnabled(true);
                                }
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                        panel.increaseScore(100);
                    } else {
                        for(ColorButton button : colorButtons){
                            button.setEnabled(false);
                        }
                        panel.setGoalLabelText("WRONG!");
                        Timer timer = new Timer(500, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(panel.getCurrentRound() > 5){
                                    rootController.changeVisibleCard(SwingProject.SUDOKU_KEY);
                                }
                                panel.setRandomGoal();
                                for(ColorButton button : colorButtons){
                                    button.setEnabled(true);
                                }
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                    panel.incrementRound();
                }
                
            });
        }
        
        Point lastLegalCoordinate = new Point(550 - MAX_CIRCLE_DIAMETER,350 - MAX_CIRCLE_DIAMETER);
        placeButtons(colorButtons,lastLegalCoordinate);
        
    }
    
    private void placeButtons(JButton[] buttons,Point bound){
        Random random = new Random();
        for(JButton jb:buttons){
                    jb.setVisible(false);
        }
        for(int i = 0;i<buttons.length;i++){
            boolean placed = false;
            int tries = 0;
            while(!placed && tries < 100){
                Point candidateLocation = new Point(random.nextInt(bound.x),random.nextInt(bound.y));
                if(testClearance(candidateLocation)){
                    buttons[i].setBounds(candidateLocation.x, candidateLocation.y, MAX_CIRCLE_DIAMETER, MAX_CIRCLE_DIAMETER);
                    panel.add(buttons[i]);
                    buttons[i].setVisible(true);
                    placed = true;
                } else {
                    tries++;
                }
            }
            if(tries > 99){
                for(JButton jb:buttons){
                    jb.setVisible(false);
                }
                i = 0;
            }
        }
    }
    
    private boolean testClearance(Point p){
        Component c = panel.findComponentAt(p);
        boolean clearTopLeft = (c != null) && (c == panel);
        c = panel.findComponentAt(p.x + MAX_CIRCLE_DIAMETER,p.y);
        boolean clearTopRight = (c != null) && (c == panel);
        c = panel.findComponentAt(p.x,p.y + MAX_CIRCLE_DIAMETER);
        boolean clearBottomLeft = (c != null) && (c == panel);
        c = panel.findComponentAt(p.x + MAX_CIRCLE_DIAMETER,p.y + MAX_CIRCLE_DIAMETER);
        boolean clearBottomRight = (c != null) && (c == panel);
        
        return clearTopLeft && clearTopRight && clearBottomLeft && clearBottomRight;
    }
    
    public void resetGame(){
        panel.reset();
        
    }

    public JPanel getPanel() {
        return this.panel;
    }
    
}
