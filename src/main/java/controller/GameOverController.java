/*************************************************************** 
*file: GameOverController.java 
* author: Jazmin Guerrero, Omar Rodriguez, Marc Deaso 
* class: CS 245 - Programming Graphical User Interfaces
* 
* 
*assignment: Swing Project 1 
* date last modified: 10/23/2016
* 
* purpose: This class is the controller between the gameover view
* and gameover model
* 
* V1.2: Addition of circle game and sudoku references. Correction of high score
* bugs.
* 
****************************************************************/ 
package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import main.java.SwingProject;
import main.java.model.GameOverModel;
import main.java.view.CircleGamePanel;
import main.java.view.GameOverPanel;

public class GameOverController {
    private GameController gameControllerReference;
    private CircleGameController circleGameControllerReference;
    private SudokuController sudokuControllerReference;
    private HighScoreController highScoreControllerReference;
    private GameOverPanel panel;
    private GameOverModel model;
    private MainFrameController rootController;

    public GameOverController(GameOverPanel panel, GameOverModel model, MainFrameController rootController) {
        this.panel = panel;
        this.model = model;
        this.rootController = rootController;
                
        this.setup();
    }

    // method: setup
    // purpose: Connects information stored in model to the view
    // ie sets label names, button names.
    private void setup() {
        panel.getGameOverLabel().setText(GameOverModel.GAME_OVER_TEXT);
        panel.getScoreLabel().setText("Final Score: " + String.valueOf(model.getScore()));
        panel.getMenuButton().setText(model.getMenuButtonText());
        panel.getResetButton().setText(model.getNewGameButtonText());
        panel.getNewHighLabel().setText(model.getNewHighLabelText());
        panel.getAddNewHighButton().setText(model.getAddNewHighBText());
         
        //main menu button listener.
        panel.getMenuButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                gameControllerReference.resetGame();
                circleGameControllerReference.resetGame();
                sudokuControllerReference.resetGame();
                rootController.changeVisibleCard(SwingProject.FUNCTION_KEY);
            }
        });
        
        //new game button listener.
        panel.getResetButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                gameControllerReference.resetGame();
                circleGameControllerReference.resetGame();
                rootController.changeVisibleCard(SwingProject.GAME_KEY);
            }
        });
        
        //submit new high score button listener.
        panel.getAddNewHighButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Check for length of input on button press.
                if(panel.getNewHighTF().getText().length() <= 3 && panel.getNewHighTF().getText().length() >=1) {
                    //add new high score and disable option to add.
                    highScoreControllerReference.getModel().addNewHighScore(
                            panel.getNewHighTF().getText(),
                            (gameControllerReference.getModel().getScore() +
                            ((CircleGamePanel)circleGameControllerReference.getPanel()).getCircleGameScore() +
                            sudokuControllerReference.getScore()));
                    panel.getNewHighTF().setText("New high score submitted!");
                    panel.getNewHighTF().setEnabled(false);
                    panel.getAddNewHighButton().setEnabled(false);
                    panel.updateScores();
                }
                else {
                    //initials must be 1-3 characters.
                    panel.getNewHighTF().setText("");
                    panel.getNewHighLabel().setText("Try again. Use 1-3 characters:");
                }
            }
        });
        
        //Controller ancestor listener.
        panel.addAncestorListener(new AncestorListener(){
            @Override
            public void ancestorAdded(AncestorEvent event) {
                panel.getScoreLabel().setText("Final Score: " + (gameControllerReference.getModel().getScore() +
                        ((CircleGamePanel)circleGameControllerReference.getPanel()).getCircleGameScore() +
                        sudokuControllerReference.getScore()));
                
                //Check for new high score after game is complete.
                if((gameControllerReference.getModel().getScore() +
                        ((CircleGamePanel)circleGameControllerReference.getPanel()).getCircleGameScore() +
                        sudokuControllerReference.getScore()) >
                        highScoreControllerReference.getModel().getLowestScore()) {
                    //player has a new high score
                    panel.getNewHighLabel().setText(model.getNewHighLabelText());
                    panel.showNewHigh();
                    panel.getNewHighTF().requestFocus();
                }
                else {
                    //no new high score
                    panel.hideNewHigh();
                }
                
                panel.updateScores();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
            
        });
    }
    
    public GameOverPanel getPanel() {
        return this.panel;
    }
    
    public void setPanel(GameOverPanel panel) {
        this.panel = panel;
    }
    
    public GameOverModel getModel() {
        return this.model;
    }
    
    public void setModel(GameOverModel model) {
        this.model = model;
    }

    public void setGameControllerReference(GameController gameControllerReference) {
        this.gameControllerReference = gameControllerReference;
    }

    public void setHighScoreControllerReference(HighScoreController highScoreController) {
        this.highScoreControllerReference = highScoreController;
    }
       
    public void setCircleGameControllerReference(CircleGameController circleGameControllerReference) {

        this.circleGameControllerReference = circleGameControllerReference;
    }
    
    public void setSudokuReference(SudokuController sudokuControllerReference) {
        this.sudokuControllerReference = sudokuControllerReference;
    }
}
