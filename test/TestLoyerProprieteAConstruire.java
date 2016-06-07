
import Jeu.*;
import Data.*;
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
public class TestLoyerProprieteAConstruire {
    public static void main(String[] args) throws IOException {
        Carreau c1 = new AutreCarreau("c1",1,0);   
        int[] loyer = new int[]{60,65,70,75,80};
        Groupe g1 = new Groupe(CouleurPropriete.rouge);
        Joueur j1 = new Joueur("j1",c1);
        Joueur j2 = new Joueur("j2",c1);
        
        Propriete p1 = new ProprieteAConstruire(1,"p1",50,g1,new int[]{60,165,170,175,180});    
        Propriete p2 = new ProprieteAConstruire(2,"p1",52,g1,new int[]{62,167,172,177,182});
        Propriete p3 = new ProprieteAConstruire(3,"p1",54,g1,new int[]{64,169,176,179,184});
        Propriete p4 = new ProprieteAConstruire(4,"p1",56,g1,new int[]{66,171,176,181,186});    

 
        
        p1.achatPropriete(j1);
        p2.achatPropriete(j1);      
        p3.achatPropriete(j1);       
        
        System.out.println("Loyer: "+p1.getLoyer(j2));        
        System.out.println("Loyer: "+p2.getLoyer(j2));            
        System.out.println("Loyer: "+p3.getLoyer(j2));  
        
        p4.achatPropriete(j1);
        
        System.out.println("Loyer: "+p1.getLoyer(j2));        
        System.out.println("Loyer: "+p2.getLoyer(j2));            
        System.out.println("Loyer: "+p3.getLoyer(j2));
        System.out.println("Loyer: "+p4.getLoyer(j2));
        
        
        System.out.println();
        
        
        
        
        
    }
}
