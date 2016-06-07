
import Jeu.*;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lordeym
 */
public class TestPayerLoyerGare {
    public static void main(String[] args) throws IOException {
        Carreau c1 = new AutreCarreau("c1",1,0);
        
        Joueur j1 = new Joueur("j1",c1);
        Joueur j2 = new Joueur("j2",c1);
        
        Propriete g2 = new Gare(2,"Gare une",200);
        Propriete g3 = new Gare(3,"Gare deux",200); 
        Propriete g4 = new Gare(4,"Gare quatre",200);
        Propriete g5 = new Gare(5,"Gare cinq",200); 
        
        
        
        g2.achatPropriete(j1);
        System.out.println("Loyer: "+g2.getLoyer(j2));
        System.out.println();
        
        g3.achatPropriete(j1);
        System.out.println("Loyer: "+g2.getLoyer(j2));
        System.out.println();
        
        g4.achatPropriete(j1);
        System.out.println("Loyer: "+g2.getLoyer(j2));
        System.out.println();
        
        
        g5.achatPropriete(j1);
        System.out.println("Loyer: "+g2.getLoyer(j2));
        System.out.println();
        

}
    }

