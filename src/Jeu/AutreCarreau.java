package Jeu;

import Data.Actions;

public abstract class AutreCarreau extends Carreau {
    
    private int montant;
    
    public AutreCarreau(String nom, int numero, int Montant){
        super(numero,nom);
        montant = Montant;
    }
    
    public int getMontant() {
        return montant;
    }
    
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
}