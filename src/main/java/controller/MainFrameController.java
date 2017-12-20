/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import static main.java.SwingProject.*;
import main.java.model.MainFrameModel;
import main.java.view.MainFrame;


/**
 *
 * @author Omar
 */
public class MainFrameController {
    private MainFrameModel model;
    private MainFrame frame;
    private String f1Message;
     
    public MainFrameController(MainFrameModel model,MainFrame frame){
        this.model = model;
        this.frame = frame;
        
        initF1Message();
        
        setup();
    }
    
    private void setup(){
        frame.setTitle(model.getTitle());
        frame.setSize(model.getWindowWidth(),model.getWindowHeight());
        frame.setLocationRelativeTo(model.getPositionReference());
        frame.setDefaultCloseOperation(model.getActionOnClose());
        
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "EXIT"); 
        frame.getRootPane().getActionMap().put("EXIT", new AbstractAction(){ 
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                System.exit(0);
            }
        });
        
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "HELP"); 
        frame.getRootPane().getActionMap().put("HELP", new AbstractAction(){ 
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(frame, f1Message, "About", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
    
    public void changeVisibleCard(String key){
        ((CardLayout)frame.getContainer().getLayout()).show(frame.getContainer(),key);
    }
    
    public void addPanel(JPanel panel, String key){
        frame.getContainer().add(panel, key);
    }

    public MainFrameModel getModel() {
        return model;
    }

    public void setModel(MainFrameModel model) {
        this.model = model;
    }

    public MainFrame getFrame() {
        return frame;
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }
    
    public void setWindowTitle(String newTitle){
        model.setTitle(newTitle);
        frame.setTitle(model.getTitle());
    }

    private void initF1Message() {
        f1Message = PROJECT_NAME + "\n";
        f1Message += "Fall 2016\n\n";
        for (int i = 0; i < CONTRIBUTORS.length; i++) {
            f1Message += CONTRIBUTORS[i] + "\n";
        }    
    }
}
