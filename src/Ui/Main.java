package Ui;

import Jeu.*;
import Data.*;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) throws IOException {
        Monopoly monopoly = new Monopoly();
        Controleur controleur = new Controleur(monopoly);
    }
}
