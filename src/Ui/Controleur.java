package Ui;

import Data.Actions;
import Jeu.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Ui.IHM;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JFrame;

public class Controleur {
    
    private IHM         ihm;
    private Monopoly    monopoly;
    private boolean ok = false;
    
    public Controleur(Monopoly monopoly){
        this.monopoly = monopoly;
        ihm = new IHM(this);
        initialiserPartie();
        if(!IhmBoiteMessage.afficherBoiteDialogue("ihm?", 1)){
        monopoly.setJoueurs(ihm.CreationJoueur());
        }else{
        JFrame frame = new JFrame();
        frame.setTitle("Monopoly");
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        IhmGraph graph = new IhmGraph(frame,this,monopoly);
        frame.add(graph);
        frame.setVisible(true); 
        }
        
        
        while(!ok){
            System.out.print("  ");
        while (monopoly.getJoueurs().size() > 1) {
            for (Joueur joueur : monopoly.getJoueurs()) {
                joueur.setTourDeJeu(true);
                ihm.afficher("\nAu tour de " + joueur.getNomJoueur() + " de jouer");
                ihm.messageEtatJouer(joueur);
                jouerUnCoup(joueur);
            
        }
        if (monopoly.getJoueurs().size() == 1) {
            for (Joueur joueur : monopoly.getJoueurs()) {
               IhmBoiteMessage.afficherBoiteDialogue(joueur.getNomJoueur()+", vous avez gagné !", 0);
            }
        }
        }}
        
    }
    
    public void jouerUnCoup(Joueur joueur) {
        while (joueur.getTourDeJeu()){
            joueur.setTourDeJeu(false);
            if (joueur.getPrison() > 0) {
                IhmBoiteMessage.afficherBoiteDialogue("Vous êtes en prison pour encore "+joueur.getPrison()+" tours", 0);
                if (joueur.getCommunautePrison()) {
                   IhmBoiteMessage.afficherBoiteDialogue("Voulez-vous utiliser votre carte communauté Sortie de prison?", 1);
                }
            }
            Carreau c = lancerDésAvancer(joueur);
            Actions a = c.action(joueur);
            if (a == Actions.gain) {
                AutreCarreau AC = (AutreCarreau) c;
                int R = AC.getMontant();
                joueur.gagnerArgent(R);
                IhmBoiteMessage.afficherBoiteDialogue("Vous avez gagné: "+R+"€", 0);
            } else if (a == Actions.payerLoyer) {
                Propriete P = (Propriete) c;
                joueur.payer(P.getLoyer(P.getProprietaire()));
                P.getProprietaire().gagnerArgent(P.getLoyer(P.getProprietaire()));
                IhmBoiteMessage.afficherBoiteDialogue("le joueur "+joueur.getNomJoueur()+" a payé "+P.getLoyer(P.getProprietaire())+"€ au joueur "+P.getProprietaire().getNomJoueur(), 0);
            } else if (a == Actions.acheter) {
                acheterPropriete(joueur,(Propriete) joueur.getPositionCourante());
            } else if (a == Actions.payer) {
                AutreCarreau AC = (AutreCarreau) c;
                int R = AC.getMontant();
                joueur.payer(-R);
                IhmBoiteMessage.afficherBoiteDialogue("Vous avez perdu: "+(-R)+"€", 0);
            } else if (a == Actions.carteChance) {
                //tirer une carte chance et l'exécuter
            }else if (a == Actions.carteCommunaute) {
                //tirer une carte communaute et l'exécuter
            } else if (a == Actions.neRienFaire) {
                IhmBoiteMessage.afficherBoiteDialogue("Vous ne pouvez effectuer aucune action", 0);
            }
            if (joueur.getPerdu()) {
                LinkedList<Joueur> joueurs = new LinkedList();
                joueurs = monopoly.getJoueurs();
                joueurs.remove(joueur);
                monopoly.setJoueurs(joueurs);
                joueur.setTourDeJeu(false);
                IhmBoiteMessage.afficherBoiteDialogue(joueur.getNomJoueur()+", vous n'avez plus d'argent et perdez", 0);
            }
            
        }
    }
    
    public void initialiserPartie() {
        this.monopoly.CreerPlateau("./src/Data/data.txt");
    }    
    
    public void acheterPropriete(Joueur joueur, Propriete achat) {
        if (IhmBoiteMessage.afficherBoiteDialogue("Voulez-vous acheter "+achat.getNom()+" pour "+achat.getPrix()+"€ ?", 1)) {
            achat.achatPropriete(joueur);
        }
    }
    
    private Carreau lancerDésAvancer(Joueur joueur) {
        int position = joueur.getPositionCourante().getNumero();
        int n = lancerDé();
        int m = lancerDé();
        ihm.afficher("Vous avez fait " + n + " et "+ m + " avec les dés");
        if (n == m ) {
            joueur.setTourDeJeu(true);
        }
        joueur.setDernierLancé(n+m);
        if (position+n+m>40) {
            joueur.setPositionCourante(getCarreau(position+n+m-40));
            joueur.gagnerArgent(200);
            ihm.afficher("Vous êtes passé par la case départ. Vous avez donc gagné 200€");
            ihm.afficher("Vous êtes arrivé sur : " + joueur.getPositionCourante().getNom());
            ihm.afficher("Argent actuel :" + joueur.getArgent() + "€");
            return joueur.getPositionCourante();
        } else {
            joueur.setPositionCourante(getCarreau(position+n+m));
            ihm.afficher("Vous êtes arrivé sur : " + joueur.getPositionCourante().getNom());
            ihm.afficher("Argent actuel :" + joueur.getArgent() + "€");
            return joueur.getPositionCourante();
        }
    }
    
    public int lancerDé() {
        return (int) (Math.random()*(6) +1);
    }
    
    public Carreau getCarreau(int numero) {
        return monopoly.getCarreaux().get(numero);
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
        
}