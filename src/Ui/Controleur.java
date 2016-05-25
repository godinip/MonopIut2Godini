package Ui;

import Jeu.Carreau;
import Jeu.Groupe;
import Jeu.Joueur;
import Jeu.Monopoly;
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
    }
    
    public void jouerUnCoup(Joueur joueur) {
        lancerDésAvancer(joueur);
    }
    
    public void initialiserPartie() {
        this.monopoly.CreerPlateau("./src/Data/data.txt");
        for(Carreau carreau : monopoly.getCarreaux().values()) {
            System.out.println(""+carreau.getNumero()+" "+carreau.getNom());
        }
        monopoly.addJoueur(new Joueur("Quentin",getCarreau(1)));
        monopoly.addJoueur(new Joueur("Valérian",getCarreau(1)));
        monopoly.addJoueur(new Joueur("Maxime",getCarreau(1)));
        monopoly.addJoueur(new Joueur("Paul",getCarreau(1)));
        //TEST GROUPE
        /*for(Groupe groupe : monopoly.getGroupes().values()) {
            System.out.println("groupe "+groupe.getCouleur().toString());
        }*/
        //TEST JOUEUR déplacement
        //while (monopoly.getJoueurs().size() > 1) {
            for (Joueur joueur : monopoly.getJoueurs()) {
                //jouerUnCoup(j);
                System.out.println("Le joueur "+joueur.getNomJoueur()+" se situe sur la case "+joueur.getPositionCourante().getNumero());
            }
        //}
    }
    
    public void acheterPropriété() {
        ;
    }
    
    private void lancerDésAvancer(Joueur joueur) {
        int n = lancerDé();
        int m = lancerDé();
        joueur.setDernierLancé(n+m);
        if (joueur.getPositionCourante().getNumero()+n+m>40) {
            joueur.setPositionCourante(getCarreau(joueur.getPositionCourante().getNumero()+n+m-40));
                
        } else {
            joueur.setPositionCourante(getCarreau(joueur.getPositionCourante().getNumero()+n+m));
        }
        if (n == m ) {
            lancerDésAvancer(joueur);
        }
    }
    
    private int lancerDé() {
        return (int) Math.random()*(6-1);
    }
    
    private Carreau getCarreau(int numero) {
        for (Carreau carreau : monopoly.getCarreaux().values()) {
            if (carreau.getNumero() == numero) {
                return carreau;
            }
        }
        return null;
    }
    
}