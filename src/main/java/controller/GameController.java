/***************************************************************
* file: GameController.java
* author:   Christopher Santos
*           Omar Rodriguez
* class: CS 245 - Programming Graphical User Interfaces
*
* assignment: Swing Project v1.0
* date last modified: 10/11/2016
*
* purpose: This is the controller that controls the communication
* between the game screen model and view
*
****************************************************************/ 
package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import main.java.SwingProject;
import main.java.model.GameModel;
import main.java.view.GamePanel;

public class GameController{
    private GamePanel panel;
    private GameModel model;
    private MainFrameController rootController;
    
    public GameController(GamePanel panel, GameModel model, MainFrameController rootController){
        this.panel = (GamePanel) panel;
        this.model = (GameModel) model;
        this.rootController = rootController;
        setup();
    }
    
    //method: setup
    //purpose: set contents of model to be reflected in the view, as well as
    // set button listeners, and activates time label
    private void setup(){
        panel.getPoints().setText("Points: "+ Integer.toString(model.getGameScore()));
        panel.getGameNameLabel().setText("Hangman");
        panel.addBlanks(model.getWordLength());
        
        
        for(JButton jb : panel.getKeyboardButtonArray()){
            jb.addActionListener((ActionEvent e) -> {
                jb.setEnabled(false);
                ArrayList<Integer> positions = model.makeGuess(jb.getText());
                for(int pos : positions){
                    panel.getBlanksArrayList().get(pos).setLetter(jb.getText());
                    panel.getBlanksArrayList().get(pos).repaint();
                }
                if(positions.isEmpty()){
                    panel.getHmPanel().incrementIncorrectGuesses();
                    panel.getHmPanel().repaint();
                }
                
                panel.getPoints().setText("Points: "+ Integer.toString(model.getGameScore()));
                int incorrectCount = model.getIncorrectCount();
                int correctCount = model.getCorrectCount();
                if(incorrectCount > 5 || correctCount == model.getWordLength()){
                    panel.getSkipButton().setEnabled(false);
                    for(JButton button : panel.getKeyboardButtonArray()){
                        button.setEnabled(false);
                    }
                    Timer timer = new Timer(1500, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rootController.changeVisibleCard(SwingProject.CIRCLE_GAME_KEY);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            });
        }

        model.setScore(100);
                
        panel.addAncestorListener(new AncestorListener(){
            @Override
            public void ancestorAdded(AncestorEvent event) {
                Timer clock = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model.setDateTime();
                        panel.setDateTime(model.getDateTime());
                    }
                });
                clock.setInitialDelay(0);
                clock.setRepeats(true);
                clock.start();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
            
        });
        
        panel.getSkipButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                model.setScore(0);
                rootController.changeVisibleCard(SwingProject.CIRCLE_GAME_KEY);
            }
            
        });
    }

    //method: getPanel
    //purpose: return panel associated with this controller
    public GamePanel getPanel() {
        return panel;
    }

    //method: getModel
    //purpose: return model associated with this controller
    public GameModel getModel() {
        return model;
    }
    
    //method: resetGame
    //purpose: reset associated view and controller for a new game
    public void resetGame(){
        model.reset();
        panel.getPoints().setText("Points: "+ Integer.toString(model.getGameScore()));
        panel.addBlanks(model.getWordLength());
        panel.getHmPanel().setIncorrectGuesses(0);
        for(JButton jb : panel.getKeyboardButtonArray()){
            jb.setEnabled(true);
        }
        panel.getSkipButton().setEnabled(true);
    }
}
