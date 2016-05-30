package Jeu;

import Data.Actions;

public class Compagnie extends Propriete {
    
    public Compagnie(int numero,String nom, int prix) {
        super(numero, nom, prix);
    }
    
    @Override
    public int getPrix() {
        return 150;
    }
    
    public int getLoyer(Joueur joueur) {
        if (this.getProprietaire().getNbCompagnie()==1){
            return 4*joueur.getDernierLancé();
        }else{
            return 10*joueur.getDernierLancé();
        }
    }

    
    @Override
    public Data.Actions action(Joueur J){
        if (this.getProprietaire()==null){
            if (J.getArgent()<this.getPrix()){
                return Actions.neRienFaire;
            }else{
                return Actions.acheter;
            }
        
        }else if(this.getProprietaire()==J){
            return Actions.neRienFaire;
        }else{      
            return Actions.payerLoyer;    
        }
        
        
    }

    
}