/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author godinip
 */
public class JoueurIhm extends  JPanel{
    JFrame window;
    
    public JoueurIhm(JFrame w){
        super();
        window =w;
        displayJIhm();
        
    }
    public  void displayJIhm(){
        JButton retour = new JButton("Retour");
        this.add(retour);
    }
    
}
