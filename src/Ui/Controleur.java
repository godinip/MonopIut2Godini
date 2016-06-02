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

public class Controleur {
    
    IHM         ihm;
    Monopoly    monopoly;
    
    public Controleur(Monopoly monopoly){
        this.monopoly = monopoly;
        ihm = new IHM(this);
        initialiserPartie();
        ArrayList<String> noms = ihm.CreationJoueur();
        for (String nom : noms) {
            monopoly.addJoueur(new Joueur(nom,getCarreau(1)));
        }
        while (monopoly.getJoueurs().size() > 1) {
            for (Joueur joueur : monopoly.getJoueurs()) {
                joueur.setTourDeJeu(true);
                ihm.afficher("Au tour de " + joueur.getNomJoueur() + " de jouer");
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
                Boolean b = ihm.afficherBoiteDialogue("Vous avez gagné: "+R, 0);
            } else if (a == Actions.payerLoyer) {
                Propriete P = (Propriete) c;
                joueur.payer(P.getLoyer(P.getProprietaire()));
                P.getProprietaire().gagnerArgent(P.getLoyer(P.getProprietaire()));
                Boolean b = ihm.afficherBoiteDialogue("le joueur "+joueur.getNomJoueur()+" a payé "+P.getLoyer(P.getProprietaire())+" au joueur "+P.getProprietaire().getNomJoueur(), 0);
            } else if (a == Actions.acheter) {
                acheterPropriete(joueur,(Propriete) joueur.getPositionCourante());
            } else if (a == Actions.payer) {
                AutreCarreau AC = (AutreCarreau) c;
                int R = AC.getMontant();
                joueur.payer(-R);
                Boolean b = ihm.afficherBoiteDialogue("Vous avez perdu: "+(-R), 0);
            } else if (a == Actions.neRienFaire) {
                Boolean b = ihm.afficherBoiteDialogue("Vous ne pouvez effectuer aucune action", 0);
            }
            if (joueur.getPerdu()) {
                HashSet<Joueur> joueurs = new HashSet();
                joueurs = monopoly.getJoueurs();
                joueurs.remove(joueur);
                monopoly.setJoueurs(joueurs);
                Boolean b = ihm.afficherBoiteDialogue(joueur.getNomJoueur()+", vous n'avez plus d'argent et perdez", 0);
            }
            
        }
    }
    
    public void initialiserPartie() {
        this.monopoly.CreerPlateau("./src/Data/data.txt");
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
        if (n == m ) {
            joueur.setTourDeJeu(true);
        }
        joueur.setDernierLancé(n+m);
        if (position+n+m>40) {
            joueur.setPositionCourante(getCarreau(position+n+m-40));
            joueur.gagnerArgent(200);
            ihm.afficher("Vous êtes arrivé sur : " + joueur.getPositionCourante().getNom());
            ihm.afficher("Argent actuel :" + joueur.getArgent());
            return joueur.getPositionCourante();
        } else {
            joueur.setPositionCourante(getCarreau(position+n+m));
            ihm.afficher("Vous êtes arrivé sur : " + joueur.getPositionCourante().getNom());
            ihm.afficher("Argent actuel :" + joueur.getArgent());
            return joueur.getPositionCourante();
        }
    }
    
    private int lancerDé() {
        return (int) (Math.random()*(6) +1);
    }
    
    private Carreau getCarreau(int numero) {
        return monopoly.getCarreaux().get(numero);
    }
        
}