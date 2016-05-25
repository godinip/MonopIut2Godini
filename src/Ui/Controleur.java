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
        //TEST
        monopoly.addJoueur(new Joueur("Quentin",getCarreau(1)));
        monopoly.addJoueur(new Joueur("Valérian",getCarreau(1)));
        monopoly.addJoueur(new Joueur("Maxime",getCarreau(1)));
        monopoly.addJoueur(new Joueur("Paul",getCarreau(1)));
        for (int i=0;i<2;i++) {
            for (Joueur joueur : monopoly.getJoueurs()) {
                System.out.println("Le joueur "+joueur.getNomJoueur()+" se situe sur la case "+joueur.getPositionCourante().getNom());
                jouerUnCoup(joueur);
                System.out.println("Le joueur "+joueur.getNomJoueur()+" avance de "+joueur.getDernierLancé()+" case et arrive sur la case "+joueur.getPositionCourante().getNom());
            }
        }
    }
    
    public void acheterPropriété() {
        ;
    }
    
    private void lancerDésAvancer(Joueur joueur) {
        int n = lancerDé()+1;
        int m = lancerDé()+1;
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
        return (int) (Math.random()*(6-1));
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