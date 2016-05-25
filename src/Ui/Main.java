package Ui;

import Jeu.*;
import Data.*;
import java.util.HashMap;

public class Main {
    
    public static void main(String[] args) {
       Monopoly monopoly = new Monopoly();
       IHM ihm = new IHM();
       Controleur controleur = new Controleur(ihm,monopoly);
       monopoly.CreerPlateau("/users/info/etu-s2/godinip/data.txt");
       for(Carreau h : monopoly.getCarreaux().values()) {
           System.out.println(""+h.getNumero()+" "+h.getNom());
       }
    }
    
}
