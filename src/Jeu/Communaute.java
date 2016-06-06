package Jeu;

public class Communaute extends AutreCarreau {
    
    private String message;

    public Communaute(String nom, int numero, int Montant, String message) {
        super(nom, numero, Montant);
        this.message = message;
    }
    
    @Override
    public Data.Actions action(Joueur J){
        return Data.Actions.carteCommunaute;
    }
    
}
//AYYYYYYYYYYYYYYYYYYY LMAO