package Ui;

import Jeu.*;
import Data.*;

public class Main {
    
    public static void main(String[] args) {
       Monopoly monopoly = new Monopoly();
       monopoly.CreerPlateau("data.txt");
    }
    
}
