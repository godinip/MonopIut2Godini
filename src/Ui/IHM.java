package Ui;

import Jeu.Carreau;
import Jeu.Joueur;

public class IHM {
    
    Controleur  controleur;
    
    public IHM(Controleur controleur) {
        controleur.monopoly.CreerPlateau("./src/Data/data.txt");
        for(Carreau h : controleur.monopoly.getCarreaux().values()) {
            System.out.println(""+h.getNumero()+" "+h.getNom());
        }
    }
    
    public void messageEtatJouer(Joueur joueur) {
        
    }
    
}