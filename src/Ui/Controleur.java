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
        IHM ihm = new IHM(this);
        initialiserPartie();
        for (String nom : ihm.CreationJoueur()) {
            Joueur joueur = new Joueur(nom,getCarreau(1));
            monopoly.addJoueur(joueur);
        }
        while (monopoly.getJoueurs().size() > 1) {
            for (Joueur joueur : monopoly.getJoueurs()) {
                joueur.setTourDeJeu(true);
                jouerUnCoup(joueur);
            }
        }
        if (monopoly.getJoueurs().size() == 1) {
            for (Joueur joueur : monopoly.getJoueurs()) {
               ihm.afficherBoiteDialogue(joueur.getNomJoueur()+", vous avez gagné !", 0);
            }
        }
    }
    
    public void jouerUnCoup(Joueur joueur) {
        while (joueur.getTourDeJeu()){
            joueur.setTourDeJeu(false);
            Carreau c = lancerDésAvancer(joueur);
            if (c.action(joueur) == Actions.gain) {
                AutreCarreau AC = (AutreCarreau) c;
                int R = AC.getMontant();
                joueur.gagnerArgent(R);
                ihm.afficherBoiteDialogue("Vous avez gagné: "+R, 0);
            } else if (c.action(joueur) == Actions.payerLoyer) {
                Propriete P = (Propriete) c;
                joueur.payer(P.getLoyer(P.getProprietaire()));
                P.getProprietaire().gagnerArgent(P.getLoyer(P.getProprietaire()));
                ihm.afficherBoiteDialogue("le Joueur "+joueur.getNomJoueur()+" a payer "+P.getLoyer(P.getProprietaire())+" au joueur "+P.getProprietaire(), 0);
            } else if (c.action(joueur) == Actions.acheter) {
                acheterPropriete(joueur,(Propriete) joueur.getPositionCourante());
            } else if (c.action(joueur) == Actions.payer) {
                AutreCarreau AC = (AutreCarreau) c;
                int R = AC.getMontant();
                joueur.payer(-R);
                ihm.afficherBoiteDialogue("Vous avez perdue: "+(-R), 0);
            } else if (c.action(joueur) == Actions.neRienFaire) {
                ihm.afficherBoiteDialogue("Vous ne pouvez effectuer aucune action", 0);
            }
            if (joueur.getPerdu()) {
                HashSet<Joueur> joueurs = new HashSet();
                joueurs = monopoly.getJoueurs();
                joueurs.remove(joueur);
                monopoly.setJoueurs(joueurs);
                ihm.afficherBoiteDialogue(joueur.getNomJoueur()+", vous n'avez plus d'argent et perdez", 0);
            }
        }
    }
    
    public void initialiserPartie() {
        this.monopoly.CreerPlateau("./src/Data/data.txt");
    }
    
    public void acheterPropriete(Joueur joueur, Propriete achat) {
        if (ihm.afficherBoiteDialogue("Voulez-vous acheter "+achat.getNom()+" pour "+achat.getPrix()+" ?", 1)) {
            joueur.payer(achat.getPrix());
            joueur.addPropriete(achat);
            }
    }
    
    private Carreau lancerDésAvancer(Joueur joueur) {
        int position = joueur.getPositionCourante().getNumero();
        int n = lancerDé();
        int m = lancerDé();
        if (n == m ) {
            joueur.setTourDeJeu(true);
        }
        joueur.setPositionCourante(getCarreau(position+n+m));
        joueur.setDernierLancé(n+m);
        if (joueur.getPositionCourante().getNumero() >40) {
            return getCarreau(position - 40);
        } else {
            return getCarreau(position);
        }
        
        
    }
    
    private int lancerDé() {
        return (int) (Math.random()*(6) +1);
    }
    
    private Carreau getCarreau(int numero) {
        return monopoly.getCarreaux().get(numero);
    }
    
}