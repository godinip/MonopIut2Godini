package Ui;

import Jeu.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IHM {
    
    private Controleur  controleur;
    private Scanner sc;
    private String scVal = "o";
    
    public IHM(Controleur controleur) {
        sc = new Scanner(System.in);
        this.controleur = controleur;
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
    
    public boolean afficherBoiteDialogue(String string, int i) {
        if (i == 0){
            System.out.println(string);
            return true;
        } else {
            do {
                System.out.print(string);
                System.out.println(" Oui / Non (o/n): ");
                scVal = sc.nextLine();
            }while( !(scVal.equals("o") || scVal.equals("n")));
            
            if(scVal.contains("o") || scVal.equals("O")){
                return true;
            }else{
                return false;
            }
        }
    }

    public void afficher(String s){
        System.out.println(s);
    }
     
    public void messageEtatJouer(Joueur joueur) {
        System.out.println("o---------------------------------------------o");
        System.out.println("Position Joueur : Case "+joueur.getPositionCourante().getNumero()+" " +joueur.getPositionCourante().getNom());
        System.out.println("Cash Joueur : "+joueur.getArgent()+"€");
        System.out.println("Propriétés :");
        for (ProprieteAConstruire p : joueur.getProprietes()){
            System.out.println(p.getNom());
        }
        System.out.println("o---------------------------------------------o");
        System.out.println();
        
        
    }
    
}
