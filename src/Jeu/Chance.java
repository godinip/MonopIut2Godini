package Jeu;

public class Chance extends AutreCarreau {

    public Chance(int numero, String nom, int Montant) {
        super(nom, numero, Montant);
    }
    
    @Override
    public Data.Actions action(Joueur J){
        return Data.Actions.carteChance;
    }
    
}