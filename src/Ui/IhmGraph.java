/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import Jeu.Monopoly;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author godinip
 */
public class IhmGraph extends JPanel{
    JFrame window;
    JComboBox listJ ;
    Controleur controleur;
    Monopoly monopoly;
    
    public IhmGraph(JFrame w,Controleur c,Monopoly m){
         super();
         window = w;
         displaySelection();
         controleur = c ;
        monopoly = m;
    }
    
    public void displaySelection(){
        
        Font f = new Font("Helvetica", Font.PLAIN, 50); // par exemple
        JLabel labMonop = new JLabel("MONOPOLY");
        labMonop.setFont(f);
        this.add(labMonop,BorderLayout.NORTH);
        JPanel panSelection = new JPanel();
        
        panSelection.add(new JLabel("Nombre de Joueur :"));
         listJ = new JComboBox();
        for (int i = 2; i < 7; i++) {
            listJ.addItem(String.valueOf(i));
        }
        panSelection.add(listJ);
        
        JButton play = new JButton("Jouer !");
        JButton exit = new JButton("Quitter");
        JPanel bouton = new JPanel();
        bouton.add(play);
        bouton.add(exit);
        play.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.SIZE));
        exit.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.SIZE));
    
        this.add(panSelection,BorderLayout.CENTER);
        this.add(bouton);
        
         play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                JFrame frame = new JFrame();
                frame.setTitle("Joueur");
                frame.setSize(350,((listJ.getSelectedIndex()+2)*30)+70);

                JoueurIhm jIhm = new  JoueurIhm(frame,listJ.getSelectedIndex()+2,controleur,monopoly);
                frame.add(jIhm);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true); 
 
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
