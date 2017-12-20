/*************************************************************** 
*file: GameOverPanel.java 
* author: Jazmin Guerrero, Omar Rodriguez, Marc Deaso 
* class: CS 245 - Programming Graphical User Interfaces
* 
* 
* assignment: Swing Project 1 
* date last modified: 10/17/2016
* 
* purpose: This class is the view representation of a gameover screen
* 
****************************************************************/ 

package main.java.view;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
//import static main.java.SwingProject.SCORE_NUMBER;
import main.java.controller.HighScoreController;
import main.java.model.HighScoreModel;

public class GameOverPanel extends JPanel {
    private GridBagConstraints gc;
    private JLabel gameOverLabel;
    private JLabel scoreLabel;
    private JButton button1;
    private JButton button2;
    
    private JLabel newHighLabel;
    private JTextField newHighTF;
    private JButton addNewHighButton;
    
    private JPanel leftPanel;
    private JPanel rightPanel;
    
    private static final Font BUTTON_FONT = new Font("Arial Black", Font.PLAIN, 16);
    private static final Border BUTTON_BORDER = BorderFactory.
            createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),
                    BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.WHITE,Color.GRAY));
    
    public GameOverPanel() {
        this.initComponents();
    }

    //method: initComponents
    //initializes and attaches components in a gameoverpanel view, and sets
    //default values
    private void initComponents() {
        this.setLayout(new GridBagLayout());
        
        gc = new GridBagConstraints();
        this.setBackground(Color.BLACK);

        rightPanel = new JPanel(new GridLayout(5, 1));
        rightPanel.setBackground(Color.BLACK);
        leftPanel = new JPanel();
        
        //add the title label.
        gameOverLabel = new JLabel();
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverLabel.setText("Game Over");
        gameOverLabel.setFont(new java.awt.Font("Arial Black", 0, 36));
        gameOverLabel.setForeground(new java.awt.Color(255, 255, 255));
        gameOverLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        gameOverLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridheight = 1;
        gc.gridwidth = 2;
        this.add(gameOverLabel, gc);
         
        //add the score label for the current game.
        scoreLabel = new JLabel();
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setText("Final Score: Placeholder");
        scoreLabel.setFont(new java.awt.Font("Arial", 0, 24));
        scoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridheight = 1;
        gc.gridwidth = 2;
        this.add(scoreLabel, gc);
        
        //add the new game button to rightPanel
        button1 = new JButton("Placeholder");
        button1.setToolTipText("Play Again");
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        button1.setFont(BUTTON_FONT);
        button1.setBorder(BUTTON_BORDER);
        rightPanel.add(button1);
        
        //add the menu button to rightPanel.
        button2 = new JButton("Placeholder");
        button2.setToolTipText("Go to Main Menu");
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.setFont(BUTTON_FONT);
        button2.setBorder(BUTTON_BORDER);
        rightPanel.add(button2);
        
        //add the placeholder label for a new score to rightPanel.
        newHighLabel = new JLabel();
        newHighLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newHighLabel.setText("Placeholder");
        newHighLabel.setFont(BUTTON_FONT);
        newHighLabel.setForeground(new java.awt.Color(255, 255, 255));
        newHighLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newHighLabel.setVisible(false);
        rightPanel.add(this.newHighLabel);
        
        //add the placeholder text field to enter initials to rightPanel.
        newHighTF = new JTextField();
        newHighTF.setAlignmentX(Component.CENTER_ALIGNMENT);
        newHighTF.setFont(BUTTON_FONT);
        newHighTF.setForeground(new java.awt.Color(0, 0, 0));
        newHighTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newHighTF.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        newHighTF.setVisible(false);
        rightPanel.add(newHighTF);
        
        //add the placeholder for the submit new score button to rightPanel.
        addNewHighButton = new JButton("Placeholder");
        addNewHighButton.setToolTipText("Add New Highscore");
        addNewHighButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addNewHighButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        addNewHighButton.setBackground(Color.BLACK);
        addNewHighButton.setForeground(Color.WHITE);
        addNewHighButton.setFont(BUTTON_FONT);
        addNewHighButton.setBorder(BUTTON_BORDER);
        addNewHighButton.setVisible(false);
        rightPanel.add(addNewHighButton);
           
        //add the rightPanel to the GameOverPanel
        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridheight = 1;
        gc.gridwidth = 1;
        this.add(rightPanel, gc);
        
        /*****
        leftPanel is created and added to GameOverPanel in updateScores method
        and contains a modified high score panel.
        *****/
    }

    //method: getResetButton
    //purpose: return reference to reset JButton
    public JButton getResetButton() {
        return this.button1;
    }
    
    //method: getMenuButton
    //purpose: return reference to menu JButton
    public JButton getMenuButton() {
        return this.button2;
    }
    
    //method: getGameOverLabel
    //purpose: return reference to main header label
    public JLabel getGameOverLabel() {
        return gameOverLabel;
    }

    //method: setGameOverLabel
    //purpose: set main header label
    public void setGameOverLabel(JLabel gameOverLabel) {
        this.gameOverLabel = gameOverLabel;
    }

    //method: getScoreLabel
    //purpose: get reference to score label
    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    //method: setScoreLabel
    //purpose: set score label
    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }
    
    //method: getButton1
    //purpose: get reference to first button on gameover panel
    /*public JButton getButton1() {
        return button1;
    }*/

    //method: setButton1
    //purpose: set first button
    /*public void setButton1(JButton button1) {
        this.button1 = button1;
    }*/

    //method: getButton2
    //purpose: return reference to second button
    /*public JButton getButton2() {
        return button2;
    }*/

    //method: setButton2
    //purpose: set second button
    /*public void setButton2(JButton button2) {
        this.button2 = button2;
    }*/
    
    //method: getNewHighTF
    //purpose: return reference to the newHighTF
    public JTextField getNewHighTF() {
        return newHighTF;
    }
    
    //method: setNewHighTF
    //purpose: set the newHighTF.
    public void setNewHighTF(JTextField newHighTF) {
        this.newHighTF = newHighTF;
    }
    
    //method: getNewHighLabel
    //purpose: return reference to the newHighLabel
    public JLabel getNewHighLabel() {
        return newHighLabel;
    }
    
    //method: setNewHighLabel
    //purpose: set the newHighLabel.
    public void setNewHighLabel(JLabel newHighLabel) {
        this.newHighLabel = newHighLabel;
    }
    
    //method: getAddNewHighButton
    //purpose: return reference to the addNewHighButton
    public JButton getAddNewHighButton() {
        return addNewHighButton;
    }
    
    //method: setAddNewHighButton
    //purpose: set the addNewHighButton.
    public void setAddNewHighButton(JButton addNewHighButton) {
        this.addNewHighButton = addNewHighButton;
    }
    
    //method: showNewHigh
    //purpose: makes the option to add a new high score available
    public void showNewHigh() {
        newHighLabel.setVisible(true);
        newHighTF.setText("");
        newHighTF.setVisible(true);
        newHighTF.setEnabled(true);
        addNewHighButton.setVisible(true);
        addNewHighButton.setEnabled(true);
    }

    //method: hideNewHigh
    //purpose: makes the option to add a new high score invisible.
    public void hideNewHigh() {
        newHighLabel.setVisible(false);
        newHighTF.setEnabled(false);
        newHighTF.setVisible(false);
        addNewHighButton.setEnabled(false);
        addNewHighButton.setVisible(false);
    }
      
    //method: updateScores
    //purpose: Creates an instance of the high score panel and adds it to the
    //         game over screen. Initially called by GameOverController's
    //         AncestorListener.
    public void updateScores() {
        //remove placeholder or old score panel
        this.remove(this.leftPanel);
        
        //new instance of HighScoreController, Does not have a rootController reference!
        HighScoreController tempHighScore = new HighScoreController(
                new HighScorePanel(),
                new HighScoreModel("High Scores", Color.BLACK),
                null);
        
        //remove the high score panel's back button.
        tempHighScore.getPanel().getBackButton().setEnabled(false);
        tempHighScore.getPanel().getBackButton().setVisible(false);
        
        //changing fonts of high score components to fit page.
        tempHighScore.getPanel().getTitle().setFont(new Font("Arial", Font.BOLD, 28));
        for(int i = 0; i < tempHighScore.getPanel().getMembersLabelsArray().length; i++) {
            tempHighScore.getPanel().getMemberLabel(i).setFont(new Font("Arial", Font.PLAIN, 20));
        }
          
        //add updated score panel to GameOverPanel.
        this.leftPanel = tempHighScore.getPanel();
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridheight = 1;
        gc.gridwidth = 1;
        this.add(leftPanel, gc);
    }

}
