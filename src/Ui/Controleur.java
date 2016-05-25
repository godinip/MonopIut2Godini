package Ui;

import Jeu.Carreau;
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
        
        for(Carreau h : monopoly.getCarreaux().values()) {
            System.out.println(""+h.getNumero()+" "+h.getNom());
        }
        monopoly.CreationJoueurs();
        //TEST
        
        //TEST 
        /*while (monopoly.getJoueurs() != null) {
            for (Joueur joueur : monopoly.getJoueurs()) {
                jouerUnCoup(joueur);
                System.out.println("Le joueur "+joueur.getNomJoueur()+" se situe sur la case "+joueur.getPositionCourante().getNumero());
            }
        }*/
    }
    
    /*public void acheterPropriété() {
        monopoly.;
    }*/
    
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