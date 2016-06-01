package Ui;

import Jeu.Carreau;
import Jeu.Joueur;
import Jeu.Monopoly;
import java.util.ArrayList;
import java.util.Scanner;

public class IHM {
    
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
    
    public static boolean afficherBoiteDialogue(String string, int i) {
        if (i == 0){
            System.out.println(string);
            return true;
        } else {
            System.out.println(string);
            System.out.println("Oui / Non ?");
            return true;
        }
    }

    public void afficher(String s){
        System.out.println(s);
    }
     
    public void messageEtatJouer(Joueur joueur) {
        System.out.println("o---------------------------------------------o");
        System.out.println("Position Joueur : Case "+joueur.getPositionCourante().getNumero()+" " +joueur.getPositionCourante().getNom());
        System.out.println("Cash Joueur : "+joueur.getArgent()+"€");
        System.out.println("o---------------------------------------------o");
        
        
    }
    
}
