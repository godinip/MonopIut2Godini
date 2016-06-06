package Jeu;

import Data.Actions;

public class AutreCarreau extends Carreau {
    
    private int montant;
    
    public AutreCarreau(String nom, int numero, int Montant){
        super(numero,nom);
        montant = Montant;
    }
    
    @Override
    public Data.Actions action(Joueur J){
        if (montant < 0) {
            return Actions.payer;
        } else {
            return Actions.gain;
        }
    }
    
    public int getMontant() {
        return montant;
    }
    
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
}