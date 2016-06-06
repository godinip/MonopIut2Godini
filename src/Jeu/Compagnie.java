package Jeu;

import Data.Actions;

public class Compagnie extends Propriete {
    
    public Compagnie(int numero,String nom, int prix) {
        super(numero, nom, prix);
    }
    
    public int getLoyer(Joueur joueur) {
        if (this.getProprietaire().getNbCompagnie()==1){
            return 4*joueur.getDernierLancé();
        }else{
            return 10*joueur.getDernierLancé();
        }
    }

      
}