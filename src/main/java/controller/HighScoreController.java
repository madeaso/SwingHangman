/***************************************************************
* file: HighScoreController.java
* author: Nahid Enayatzadeh, Marc Deaso
* class: CS 245 - Programming Graphical User Interfaces
*
* assignment: Swing Project v1.2
* date last modified: 10/23/2016
*
* purpose: This is the controller that controls the communication
* between the highscore screen model and view
* 
* V1.2: Implementation of reading high scores from a file, bug fixes.
*
****************************************************************/ 
package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import main.java.SwingProject;
import main.java.model.HighScoreModel;
import main.java.view.HighScorePanel;

public class HighScoreController {
    private HighScorePanel panel;
    private HighScoreModel model;
    private MainFrameController rootController;
    
    public HighScoreController(HighScorePanel panel, HighScoreModel model, MainFrameController rootController) {
        this.panel = panel;
        this.model = model;
        this.rootController = rootController; 
        
        this.setup();
    }
    
    //Method: setup
    //Purpose: Modifing variables title name, members name, background color and back button
    private void setup() {
        panel.setTitle(model.getHighScoreTitle());
        panel.setMembers(model.getLeadersLabelsText());
        panel.setBackgroundColor(model.getBackgroundColor());
        panel.getBackButton().setText(model.getBackButtonText());
        
        panel.getBackButton().addActionListener(new ActionListener(){
            
         public void actionPerformed(ActionEvent e){
               rootController.changeVisibleCard(SwingProject.FUNCTION_KEY);
         }   
        });
        
        panel.addAncestorListener(new AncestorListener(){
            @Override
            public void ancestorAdded(AncestorEvent event) {
                model.readScores();
                panel.setMembers(model.getLeadersLabelsText());
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
            
        });
    }
    
    //Method: getPanel
    //Purpose: Having access to panel variable
    public HighScorePanel getPanel() {
        return this.panel;
    }
    
    //Method: setPanel
    //Purpose: Modyfing the panel of HighScore
    public void setPanel(HighScorePanel panel) {
        this.panel = panel;
    }
    
    //Method: getModel
    //Purpose: Having access to model variable
    public HighScoreModel getModel() {
        return this.model;
    }
    
    //Method: setModel
    //Purpose: Modyfing the Model variable
    public void setModel(HighScoreModel model) {
        this.model = model;
    }

}
