package Jeu;

public class Chance extends AutreCarreau {
    
    private String message;

    public Chance(int numero, String nom, int Montant, String message) {
        super(nom, numero, Montant);
        this.message = message;
    }
    
    @Override
    public Data.Actions action(Joueur J){
        return Data.Actions.carteChance;
    }
    
}