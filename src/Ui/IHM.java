package Ui;

import Jeu.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class IHM {
    
    private Controleur  controleur;
    private Scanner sc;
    private String scVal = "o";
    
    public IHM(Controleur controleur) {
        sc = new Scanner(System.in);
        this.controleur = controleur;
    }
    public LinkedList<Joueur> CreationJoueur(){
        boolean isOk = false;
        LinkedList<Joueur> joueurs = new LinkedList<>();
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
            joueurs.addLast(new Joueur(nom, controleur.getCarreau(1)));
            
        }
        return joueurs;
     }
    
    public boolean afficherBoiteDialogue(String string, int i) {
        if (i == 0){
            System.out.println(string);
            System.out.println("Appuyez sur entrée pour continuer");
            scVal = sc.nextLine();
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
        System.out.println("Position Joueur : " + joueur.getPositionCourante().getNom());
        System.out.println("Argent actuel : "+joueur.getArgent()+"€");
        System.out.println("Propriétés :");
        if (joueur.getProprietes().isEmpty()){
            System.out.println("Aucune Propriété construisible n'est possédée");
        } else {
        for (ProprieteAConstruire p : joueur.getProprietes()){
            System.out.println(p.getNom());
        }}
        System.out.println("----------");
        System.out.println("Gares :");
        if (joueur.getGares().isEmpty()){
            System.out.println("Aucune Gare n'est possédée");
        } else {
        for (Gare g : joueur.getGares()){
            System.out.println(g.getNom());
        }}
        
        System.out.println("----------");
        System.out.println("Compagnies :");
        if (joueur.getCompagnies().isEmpty()){
            System.out.println("Aucune Compagnie n'est possédée");
        } else {
            for (Compagnie c : joueur.getCompagnies()){
                System.out.println(c.getNom());
            }
        }
        System.out.println("o---------------------------------------------o");
        System.out.println();
        
        
    }
    
}
