
import Jeu.*;
import Ui.*;
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
public class TestCompagniePayerLoyer {
    public static void main(String[] args) throws IOException {
        Carreau c1 = new AutreCarreau("c1",1,0);
        Propriete c2 = new Compagnie(2,"Compagnie une",200);
        Propriete c3 = new Compagnie(3,"Compagnie deux",200);        
        
        Joueur j1 = new Joueur("j1",c1);
        Joueur j2 = new Joueur("j2",c1);
        
        c2.achatPropriete(j1);
        
        j2.setDernierLancé(9);
        System.out.println(c2.getLoyer(j2));
        
        c3.achatPropriete(j1);       
        
        j2.setDernierLancé(9);
        System.out.println(c2.getLoyer(j2));
        
        
        
        
        
    }
}
