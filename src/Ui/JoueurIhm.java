package Ui;

import Jeu.Joueur;
import Jeu.Monopoly;
import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoueurIhm extends  JFrame implements Observateur{
    private JFrame window;
    private int nbJoueur;
    private  JButton play,exit;
    private ArrayList<JTextField> nomJList = new ArrayList<>();
    private Controleur controleur;
    private JPanel cadrePanel;
    private JPanel affichage,panelBouton;
    private LinkedList<Joueur> joueurs = new LinkedList<>();
    private PlateauIhm plateauIhm;
    
    public JoueurIhm(int nbj,Controleur c){
        super("Joueurs");
        controleur = c;
        nbJoueur = nbj;
        displayJIhm();
        initUiLIsten();
    }
    
    public  void displayJIhm(){
        affichage = new JPanel();
        affichage.setLayout(new BoxLayout(affichage, BoxLayout.PAGE_AXIS));
         for (int i = 1; i < nbJoueur+1; i++) {
             JPanel panelNom = new JPanel();
             affichage.add(panelNom);
             panelNom.add(new JLabel("Joueur "+i+" :"));
             JTextField champNom = new JTextField(20);
             champNom.setText("Test "+ i);
             panelNom.add(champNom);
             nomJList.add(champNom);
        }
        panelBouton = new JPanel();
        panelBouton.add(play = new  JButton("Jouer !"),BorderLayout.CENTER);
        panelBouton.add(exit = new  JButton("Quitter"),BorderLayout.CENTER);
        cadrePanel = new JPanel();
        cadrePanel.add(affichage,BorderLayout.CENTER);
        cadrePanel.add(panelBouton,BorderLayout.SOUTH);
        add(cadrePanel);
    }
    
    private void initUiLIsten(){
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> erreurNom = new ArrayList<>();
                ArrayList<String> nomJoueur = new ArrayList<>();
                nomJoueur.clear();
                boolean erreur = true;
                for (JTextField jt1  : nomJList) {
                     if(!nomJoueur.contains(jt1.getText() )){
                           nomJoueur.add(jt1.getText());
                     }
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
                            IhmMessage.afficherBoiteDialogue("Le(s) pseudo(s) "+message+" sont enregistré(s) plusieurs fois\nCorrigez le !", 0);
                }else{
                    
                    int desMax = 0;
                    int des = 0;
                        for (String string : nomJoueur) {
                          Joueur j = new Joueur(string,controleur.getCarreau(1));
                         getJoueurs().addLast(j);
                        }
                        setVisible(false);
                }
                //A MODIFIERcontroleur.setJoueurs(joueurs);
                try {
                    plateauIhm = new PlateauIhm(controleur);
                    plateauIhm.affiche();
                } catch (IOException ex) {
                    Logger.getLogger(JoueurIhm.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void affiche(){
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(350,((nbJoueur)*30)+70);
        setVisible(true);
        
    }

    public LinkedList<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(LinkedList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    @Override
    public void notifier(Message msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
