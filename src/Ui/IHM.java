package Ui;

import Jeu.Carreau;
import Jeu.Joueur;
import Jeu.Monopoly;
import java.util.ArrayList;
import java.util.Scanner;

public class IHM {

    static boolean afficherBoiteDialogue(String string, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    Controleur  controleur;
    Scanner sc ;
    public IHM(Controleur controleur) {
        sc = new Scanner(System.in);
    }
    public ArrayList<String> CreationJoueur(){
        boolean isOk = false;
        ArrayList<String> joueurs = new ArrayList<>();
        int nb;
        do{
            System.out.println("Combien de Joueurs voulez vous ajouter (2 à 6) ? ");
             nb = sc.nextInt();
            
            if (nb >=2 && nb <=6){
                isOk = true;
            }
        }while (!isOk);
        for (int i = 1; i < nb+1; i++) {  
            String nom;
            do {                
                System.out.print("Nom du joueur "+i+": ");
                nom = sc.next();
            }while(joueurs.contains(nom));
            joueurs.add(nom);
            
        }
        return joueurs;
     }
    

            
        

    public void messageEtatJouer(Joueur joueur) {
    
        
        
    }
    
}