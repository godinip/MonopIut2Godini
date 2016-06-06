package Jeu;

public class Communaute extends AutreCarreau {

    public Communaute(String nom, int numero, int Montant) {
        super(nom, numero, Montant);
    }
    
    @Override
    public Data.Actions action(Joueur J){
        return Data.Actions.carteCommunaute;
    }
    
}
//AYYYYYYYYYYYYYYYYYYY LMAO