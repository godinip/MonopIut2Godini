package Jeu;

public class Chance extends AutreCarreau {

    public Chance(String nom, int numero, int Montant) {
        super(nom, numero, Montant);
    }
    
    @Override
    public Data.Actions action(Joueur J){
        return Data.Actions.carteChance;
    }
    
}