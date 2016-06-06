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
            J.payer(-1 * montant);
            return Actions.payer;
        } else if (montant > 0) {
            J.gagnerArgent(montant);
            return Actions.gain;
        } else if (this.getNumero() == 31) {
            J.setPrison(3);
            return Actions.prison;
        } else {
            return Actions.neRienFaire;
        }
    }
    
    public int getMontant() {
        return montant;
    }
    
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
}