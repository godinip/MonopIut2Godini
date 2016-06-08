package Jeu;

import Data.Actions;

public class Gare extends Propriete {

    public Gare(int numero,String nom, int prix) {
        super(numero,nom, prix);
    }
    
    @Override
    public int getLoyer(Joueur joueur){
        if (super.getProprietaire().getNbGare()!=0){
            return (super.getProprietaire().getNbGare()*25);
        }else{
            return 0;
        }
    }
    
}
