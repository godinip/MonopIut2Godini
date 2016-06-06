package Ui;

import Jeu.*;
import Data.*;
import java.util.HashMap;
import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {
        if(IhmBoiteMessage.afficherBoiteDialogue("Ihm ?", 1)){
            
        
        JFrame frame = new JFrame();
        frame.setTitle("Monopoly");
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        IhmGraph graph = new IhmGraph(frame);
        frame.add(graph);
        frame.setVisible(true); 
        }else{
        
        Monopoly monopoly = new Monopoly();
        Controleur controleur = new Controleur(monopoly);
        }
    }
    
}