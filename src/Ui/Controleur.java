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
    
    public Controleur(){
        
    }
    
    public void jouerUnCoup(Joueur joueur) {
        lancerDésAvancer(joueur);
    }
    
    private Carreau lancerDésAvancer(Joueur joueur) {
        int n = lancerDé();
        int m = lancerDé();
        joueur
        for (Carreau carreau : monopoly.getCarreaux().values()) {
            if (joueur.getPositionCourante().getNumero()+n+m>40) {
                if (carreau.getNumero() == joueur.getPositionCourante().getNumero()+n+m-40) {
                    joueur.setPositionCourante(carreau);
                }
            } else {
                if (carreau.getNumero() == joueur.getPositionCourante().getNumero()) {
                    joueur.setPositionCourante(carreau);
                }
            }
        }
        if (n == m ) {
            lancerDésAvancer(joueur);
        }
    }
    
    public int lancerDé() {
        return (int) Math.random()*(6-1);
    }
    
    public Carreau getCarreau(int numero) {
        
    }
    
}