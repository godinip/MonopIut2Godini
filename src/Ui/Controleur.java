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

public class Controleur {
    
    private IHM         ihm;
    private Monopoly    monopoly;
//    private int[] dés = {3,2,2,2,4,1,2,2,5,1,4,2,4,6,5,5,6,6,1,1,3,1,5,5,1,1,1,1,3,2,1,1,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5};
//    private int dé = -1;
    
    public Controleur(Monopoly monopoly){
        this.monopoly = monopoly;
        ihm = new IHM(this);
        initialiserPartie();
        monopoly.setJoueurs(ihm.CreationJoueur());

        while (monopoly.getJoueurs().size() > 1) {
            for (Joueur joueur : monopoly.getJoueurs()) {
                joueur.setTourDeJeu(true);
                
                ihm.afficher("\nAu tour de " + joueur.getNomJoueur() + " de jouer");
                ihm.messageEtatJouer(joueur);
                jouerUnCoup(joueur);
            }
        }
        if (monopoly.getJoueurs().size() == 1) {
            for (Joueur joueur : monopoly.getJoueurs()) {
               Boolean b = ihm.afficherBoiteDialogue(joueur.getNomJoueur()+", vous avez gagné !", 0);
            }
        }
    
    }
    
    public void jouerUnCoup(Joueur joueur) {
        while (joueur.getTourDeJeu()){
            joueur.setTourDeJeu(false);
            Carreau c = lancerDésAvancer(joueur);
            Actions a = c.action(joueur);
            if (a == Actions.gain) {
                AutreCarreau AC = (AutreCarreau) c;
                int R = AC.getMontant();
                joueur.gagnerArgent(R);
                Boolean b = ihm.afficherBoiteDialogue("Vous avez gagné: "+R+"€", 0);
            } else if (a == Actions.payerLoyer) {
                Propriete P = (Propriete) c;
                joueur.payer(P.getLoyer(P.getProprietaire()));
                P.getProprietaire().gagnerArgent(P.getLoyer(P.getProprietaire()));
                Boolean b = ihm.afficherBoiteDialogue("le joueur "+joueur.getNomJoueur()+" a payé "+P.getLoyer(P.getProprietaire())+"€ au joueur "+P.getProprietaire().getNomJoueur(), 0);
            } else if (a == Actions.acheter) {
                acheterPropriete(joueur,(Propriete) joueur.getPositionCourante());
            } else if (a == Actions.payer) {
                AutreCarreau AC = (AutreCarreau) c;
                int R = AC.getMontant();
                joueur.payer(-R);
                Boolean b = ihm.afficherBoiteDialogue("Vous avez perdu: "+(-R)+"€", 0);
            } else if (a == Actions.neRienFaire) {
                Boolean b = ihm.afficherBoiteDialogue("Vous ne pouvez effectuer aucune action", 0);
            }
            if (joueur.getPerdu()) {
                LinkedList<Joueur> joueurs = new LinkedList();
                joueurs = monopoly.getJoueurs();
                joueurs.remove(joueur);
                monopoly.setJoueurs(joueurs);
                joueur.setTourDeJeu(false);
                Boolean b = ihm.afficherBoiteDialogue(joueur.getNomJoueur()+", vous n'avez plus d'argent et perdez", 0);
            }
            
        }
    }
    
    public void initialiserPartie() {
        this.monopoly.CreerPlateau("./src/Data/data.txt");
        this.monopoly.CreerCartes("./src/Data/Chance.txt", "./src/Data/Communaute.txt");
    }    
    
    public void acheterPropriete(Joueur joueur, Propriete achat) {
        boolean b = ihm.afficherBoiteDialogue("Voulez-vous acheter "+achat.getNom()+" pour "+achat.getPrix()+"€ ?", 1);
        if (b) {
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
    
    private int lancerDé() {
    //    dé = dé + 1;
    //    return dés[dé];
        return (int) (Math.random()*(6) +1);
    }
    
    public Carreau getCarreau(int numero) {
        return monopoly.getCarreaux().get(numero);
    }
        
}