package Jeu;

import Data.*;

public class Chance extends Carreau {

    public Chance(String nom, int numero) {
        super(numero, nom);
    }
    
    public Events action(Joueur J){
        return new Events(Data.Actions.carteChance, J);
    }
    
}