package Jeu;

import static Data.Actions.carteCommunaute;

public class Communaute extends AutreCarreau {

    public Communaute(String nom, int numero, int Montant) {
        super(nom, numero, Montant);
    }
    
    @Override
    public Data.Actions action(Joueur J){
        return carteCommunaute;
    }
    
}
