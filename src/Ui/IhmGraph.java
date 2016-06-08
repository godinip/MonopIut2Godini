package Ui;

import Jeu.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class IhmGraph extends JFrame{
    private JComboBox listJ ;
    private Controleur controleur;
    private JButton play,exit; 
    private JPanel panSelection,monopPanel;
    private JLabel labMonop;
    private int nbJoueur;
    private JPanel bouton;
    
    public IhmGraph(Controleur c){
        super("Monopoly");
        controleur = c ;
        initUIComponents();
        initUiLIsten();
    }
    
    public void initUIComponents(){
        Font f = new Font("Helvetica", Font.PLAIN, 50);
        labMonop = new JLabel("MONOPOLY");
        labMonop.setFont(f);
        panSelection = new JPanel();
        panSelection.add(new JLabel("Nombre de Joueur :"));
        listJ = new JComboBox();
        for (int i = 2; i < 7; i++) {
            listJ.addItem(String.valueOf(i));
        }
        panSelection.add(listJ);
        play = new JButton("Jouer !");
        exit = new JButton("Quitter");
        bouton = new JPanel();
        bouton.add(play);
        bouton.add(exit);
        play.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.SIZE));
        exit.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.SIZE));
        
        monopPanel = new JPanel();
        monopPanel.add(labMonop,BorderLayout.NORTH);
        monopPanel.add(panSelection,BorderLayout.CENTER);
        monopPanel.add(bouton);
        add(monopPanel);
        
         
    }
    
    private void initUiLIsten(){
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNbJoueur(listJ.getSelectedIndex()+2);
                setVisible(false);
                controleur.getJoueurIhm().affiche();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    public void affiche(){
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(350,200);
        setVisible(true);   
    }
    
    public void setVisibles(boolean etat){
        setVisibles(etat);
    }
    
    public int getNbJoueur() {
        return nbJoueur;
    }

    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
    }
    
}
