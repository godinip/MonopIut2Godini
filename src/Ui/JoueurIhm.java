/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
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
    private JFrame window;
    private int nbJoueur;
    private ArrayList<JTextField> nomJList = new ArrayList<>();
    
    public JoueurIhm(JFrame w,int nbj){
        super();
        window =w;
        
        nbJoueur = nbj;
        displayJIhm();
        
    }
    public  void displayJIhm(){
        
        JButton play,exit;
        JPanel affichage = new JPanel();
        affichage.setLayout(new BoxLayout(affichage, BoxLayout.PAGE_AXIS));
        
        this.add(affichage,BorderLayout.CENTER);
         for (int i = 1; i < nbJoueur+1; i++) {
           JPanel panelNom = new JPanel();
                  affichage.add(panelNom);
                 panelNom.add(new JLabel("Joueur "+i+" :"));
                 JTextField champNom = new JTextField(20);
                 champNom.setText("Test "+ i);
                 panelNom.add(champNom);
                 nomJList.add(champNom);
          
        }
         JPanel panelBouton = new JPanel();
         this.add(panelBouton,BorderLayout.SOUTH);
        panelBouton.add(play = new  JButton("Jouer !"),BorderLayout.CENTER);
        panelBouton.add(exit = new  JButton("Quitter"),BorderLayout.CENTER);
        
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> erreurNom = new ArrayList<>();
                boolean erreur = true;
                for (JTextField jt1  : nomJList) {
                    for (JTextField jt2  : nomJList) {
                        if(nomJList.indexOf(jt1) ==  nomJList.indexOf(jt2)){
                        }else{
                            if(jt1.getText().equals( jt2.getText())){
                                if(!erreurNom.contains(jt1.getText())){
                                    erreur = false;
                                    erreurNom.add(jt1.getText());
                                }
                            }
                        }
                    }
                    
                }
                if(!erreur){
                    String message = null;
                    for (String s : erreurNom) {
                        if( message == null){
                            message = s;
                        }else{
                            message = message +", "+s;
                        }
                    }
                    IhmBoiteMessage.afficherBoiteDialogue("Le(s) pseudo(s) "+message+" sont enregistré(s) plusieurs fois\nCorrigez le !", 1);
                    
                }else{
                    
                }
                
 
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
    }
    
}
