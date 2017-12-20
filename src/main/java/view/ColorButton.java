/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.view;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author omar
 */
public class ColorButton extends JButton{
    private String color;
    ImageIcon imageUp;
    ImageIcon imageDown;
    
    public ColorButton(String color){
        this.color = color;
        imageUp = new ImageIcon(getClass().getClassLoader().getResource("images/"+ color + "_UP.png"));
        imageDown = new ImageIcon(getClass().getClassLoader().getResource("images/"+ color + "_DOWN.png"));
        this.setIcon(imageUp);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        setFocusPainted(false);
        
        this.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                setIcon(imageDown);
            }
            public void mouseExited(MouseEvent evt)
            {
                setIcon(imageUp);
            }
            public void mousePressed(MouseEvent evt)
            {
            }
            public void mouseReleased(MouseEvent evt)
            {
            }
        });
        
    }
    
    public String getColor(){
        return color;
    }
    
    public void setColor(String c){
        color = c;
    }
}
