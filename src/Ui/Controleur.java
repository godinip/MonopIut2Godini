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

public class Controleur {
    
    IHM         ihm;
    Monopoly    monopoly;
    
    public Controleur(IHM ihm,Monopoly monopoly){
        this.ihm = ihm;
        this.monopoly = monopoly;
    }
    
    public void jouerUnCoup(Joueur joueur) {
        lancerDésAvancer(joueur);
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
    
    public int lancerDé() {
        return (int) Math.random()*(6-1);
    }
    
    public Carreau getCarreau(int numero) {
        for (Carreau carreau : monopoly.getCarreaux().values()) {
            if (carreau.getNumero() == numero) {
                return carreau;
            }
        }
        return null;
    }
    
}