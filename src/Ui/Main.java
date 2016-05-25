package Ui;

import Jeu.*;
import Data.*;
import java.util.HashMap;

public class Main {
    private HashMap<Integer,Carreau> carreaux = new HashMap<>();
    public static void main(String[] args) {
       Monopoly monopoly = new Monopoly();
       monopoly.CreerPlateau("data.txt");
       
         for(<Integer, Carreau> h : monopoly.getCarreaux()) {
            
         }
       
    }
    
}
