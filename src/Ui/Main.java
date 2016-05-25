package Ui;

import Jeu.*;
import Data.*;
import java.util.HashMap;

public class Main {
    
    public static void main(String[] args) {
       Monopoly monopoly = new Monopoly();
       monopoly.CreerPlateau("data.txt");
       
         for(Carreau h : monopoly.getCarreaux().values()) {
             System.out.println(""+h.getNumero()+" "+h. ); 
         }
       
    }
    
}
