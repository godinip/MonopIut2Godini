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
        initialiserPartie();
    }
    
    public void jouerUnCoup(Joueur joueur) {
        while (joueur.getTourDeJeu()){
            joueur.setTourDeJeu(false);
            Carreau c = lancerDésAvancer(joueur);
            if (c.action(joueur) == Actions.gain) {
                AutreCarreau AC = (AutreCarreau) c;
                int R = AC.getMontant();
                joueur.gagnerArgent(R);
                IHM.afficherBoiteDialogue("Vous avez gagné: "+R, 0);
            } else if (c.action(joueur) == Actions.payerLoyer) {
                Propriete P = (Propriete) c;
                joueur.payer(P.getLoyer(P.getProprietaire()));
                P.getProprietaire().gagnerArgent(P.getLoyer(P.getProprietaire()));
                IHM.afficherBoiteDialogue("le Joueur "+joueur.getNomJoueur()+" a payer "+P.getLoyer(P.getProprietaire())+" au joueur "+P.getProprietaire(), 0);
            } else if (c.action(joueur) == Actions.acheter) {
                acheterPropriete(joueur,(Propriete) joueur.getPositionCourante());
            } else if (c.action(joueur) == Actions.payer) {
                AutreCarreau AC = (AutreCarreau) c;
                int R = AC.getMontant();
                joueur.payer(-R);
                IHM.afficherBoiteDialogue("Vous avez perdue: "+R, 0);
            } else if (c.action(joueur) == Actions.neRienFaire) {
                IHM.afficherBoiteDialogue("Vous ne pouvez effectuer aucune action", 0);
            }
        }
    }
    
    public void initialiserPartie() {
        this.monopoly.CreerPlateau("./src/Data/data.txt");
    }
    
    public void acheterPropriete(Joueur joueur, Propriete achat) {
        if (IHM.afficherBoiteDialogue("Voulez-vous acheter "+achat.getNom()+" pour "+achat.getPrix()+" ?", 1)) {
            joueur.payer(achat.getPrix());
            joueur.addPropriete(achat);
            }
    }
    
    private Carreau lancerDésAvancer(Joueur joueur) {
        int pos = joueur.getPositionCourante().getNumero();
        int n = lancerDé();
        int m = lancerDé();
        if (n == m ) {
            joueur.setTourDeJeu(true);
        }
        pos = pos + n + m;
        joueur.setDernierLancé(n+m);
        if (pos >40) {
            return getCarreau(pos - 40);
        } else {
            return getCarreau(pos);
        }
        
        
    }
    
    private int lancerDé() {
        return (int) (Math.random()*(6) +1);
    }
    
    private Carreau getCarreau(int numero) {
        return monopoly.getCarreaux().get(numero);
    }
    
}