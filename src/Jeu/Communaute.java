package Jeu;

import Data.*;

public class Communaute extends Carreau {

    public Communaute(String nom, int numero) {
        super(numero, nom);
    }
    
    public Events action(Joueur J){
        return new Events(Data.Actions.carteCommunaute, J);
    }
    
}
