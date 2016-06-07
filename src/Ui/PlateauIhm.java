/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import com.sun.prism.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author godinip
 */
public class PlateauIhm extends JFrame{
  private JPanel plateau,information;
    public PlateauIhm(){
        super("Monopoly");
         initUIComponents();
    }
    public void initUIComponents(){
       Plateau plateau = new Plateau();
       information = new JPanel();
       information.setBackground(Color.YELLOW);
       information.add(Box.createRigidArea(new Dimension(150, 0)));
       
        
        add(plateau,BorderLayout.CENTER);
        add(information,BorderLayout.EAST);
    }
    public void affiche(){
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(1040,900);
        setVisible(true);
    }
    
    
    
}
