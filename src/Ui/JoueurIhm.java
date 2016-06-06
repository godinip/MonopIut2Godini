/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author godinip
 */
public class JoueurIhm extends  JPanel{
    JFrame window;
    int nbJoueur;
    
    public JoueurIhm(JFrame w,int nbj){
        super();
        window =w;
        
        nbJoueur = nbj;
        System.out.println(nbj);
        displayJIhm();
        
    }
    public  void displayJIhm(){
       
        
        for (int i = 1; i < nbJoueur+1; i++) {
             this.add(new JLabel("Joueur "+i+" :"));
             JTextField champNom = new JTextField(20);
            this.add(champNom);
        }
 
    }
    
}
