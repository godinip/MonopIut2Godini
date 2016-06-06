package Jeu;

public class Chance extends AutreCarreau {
    
    private String message;

    public Chance(String nom, int numero, int Montant, String message) {
        super(nom, numero, Montant);
        this.message = message;
    }
    
    @Override
    public Data.Actions action(Joueur J){
        return Data.Actions.carteCommunaute;
    }
    
}