package Jeu;

import Data.Actions;

public class AutreCarreau extends Carreau {
    
    private int montant;
    
    public AutreCarreau(String nom, int numero, int Montant){
        super(numero,nom);
        montant = Montant;
    }
    
    @Override
    public Data.Actions action(Joueur joueur){
        if (this.getMontant()< 0) {
            joueur.payer(-this.getMontant());
            return Actions.payer;
        } else if (this.getMontant() > 0) {
            joueur.gagnerArgent(this.getMontant());
            return Actions.gain;
        } else if (this.getNumero() == 31) {
            joueur.setPrison(3);
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