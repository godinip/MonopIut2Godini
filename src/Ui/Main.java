package Ui;

import Jeu.*;
import Data.*;
import java.util.HashMap;

public class Main {
    
    public static void main(String[] args) {
       Monopoly monopoly = new Monopoly();
       Controleur controleur = new Controleur(monopoly);
       IHM ihm = new IHM(controleur);
    }
    
}
