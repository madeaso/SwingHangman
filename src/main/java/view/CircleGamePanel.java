/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.view;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Chris, Omar
 */
public class CircleGamePanel extends JPanel{
    private JLabel goalLabel;
    private JLabel dateLabel;
    private ColorButton redButton;
    private ColorButton greenButton;
    private ColorButton blueButton;
    private ColorButton yellowButton;
    private ColorButton purpleButton;
    private ColorButton[] colorButtons;
    
    private LocalDateTime dateTime;
    private int circleGameScore;
    private int currentRound;
    private String currentGoal;
    
    private static final String RED = "RED";
    private static final String GREEN = "GREEN";
    private static final String BLUE = "BLUE";
    private static final String YELLOW = "YELLOW";
    private static final String PURPLE = "PURPLE";
    private String[] colors;
    
    public CircleGamePanel(){
        goalLabel = new JLabel("Placeholder");
        dateLabel = new JLabel("Placeholder");
        redButton = new ColorButton(RED);
        redButton.setToolTipText("Red");
        greenButton = new ColorButton(GREEN);
        greenButton.setToolTipText("Green");
        blueButton = new ColorButton(BLUE);
        blueButton.setToolTipText("Blue");
        yellowButton = new ColorButton(YELLOW);
        yellowButton.setToolTipText("Yellow");
        purpleButton = new ColorButton(PURPLE);
        purpleButton.setToolTipText("Purple");
        
        colorButtons = new ColorButton[]{
            redButton,
            greenButton,
            blueButton,
            yellowButton,
            purpleButton
        };
        
        colors = new String[]{
            RED,
            GREEN,
            BLUE,
            YELLOW,
            PURPLE
        };
        
        circleGameScore = 0;
        currentRound = 1;
        currentGoal = new String("Placeholder");
        
        initComponents();
    }
    
    private void initComponents(){
        setRandomGoal();
        goalLabel.setBounds(275,20,150,50);
        goalLabel.setFont(new Font("Impact", Font.PLAIN, 32));
        add(goalLabel);
    }
    
    //name: getDateTime
    //purpose: returns current displayed date/time
    public String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM-dd-uuuu hh:mm:ss a");
        return dtf.format(dateTime);
    }
    
    //name: reset
    //purpose: reinitializes game's default data
    public void reset(){
        circleGameScore = 0;
        currentRound = 1;
        setRandomGoal();
    }
    
    //name: getCurrentRound
    //purpose: retrieves current round data   
    public int getCurrentRound(){
        return currentRound;
    }

    //name: incrementRound
    //purpose: increments current round data by 1 
    public void incrementRound(){
        setCurrentRound(getCurrentRound()+1);
    }
 
    //name: setCurrentRound
    //purpose: sets current round to given integer
    public void setCurrentRound(int round){
        currentRound = round;
    }

    //name: increaseScore
    //purpose: adds value s to current score  
    public void increaseScore(int s){
        circleGameScore+=s;
    }

    //name: setRandomGoal
    //purpose: sets the color goal randomly    
    public void setRandomGoal(){
        Random rand = new Random();
        goalLabel.setText(colors[rand.nextInt(colors.length)]);
        currentGoal = colors[rand.nextInt(colors.length)];
        if(currentGoal.equals(RED)){
            goalLabel.setForeground(Color.RED);
        } else if(currentGoal.equals(GREEN)){
            goalLabel.setForeground(Color.GREEN);
        } else if(currentGoal.equals(BLUE)){
            goalLabel.setForeground(Color.BLUE);
        } else if(currentGoal.equals(YELLOW)){
            goalLabel.setForeground(Color.YELLOW);
        } else if(currentGoal.equals(PURPLE)){
            goalLabel.setForeground(Color.MAGENTA);
        }
    }
    
    public void setGoalLabelText(String text){
        goalLabel.setText(text);
    }

    //name: getCurrentGoal
    //purpose: retrieves current color needed to score    
    public String getCurrentGoal(){
        return currentGoal;
    }

    //name: getColorButtons
    //purpose: retrieves all buttons packaged into array 
    public ColorButton[] getColorButtons(){
        return colorButtons;
    }

    public int getCircleGameScore() {
        return circleGameScore;
    }
    
}
