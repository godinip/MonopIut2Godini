package Jeu;

import Data.*;

public class AutreCarreau extends Carreau {
    
    private int montant;
    
    public AutreCarreau(String nom, int numero, int Montant){
        super(numero,nom);
        this.setMontant(Montant);
    }
    
    public Events action(Joueur joueur){
        if (this.getMontant()< 0) {
            joueur.payer(-this.getMontant());
            return new Events(Actions.payer,joueur, - this.getMontant());
        } else if (this.getMontant() > 0) {
            joueur.gagnerArgent(this.getMontant());
            return new Events(Actions.gain, joueur, this.getMontant());
        } else if (this.getNumero() == 31) {
            joueur.setPrison(3);
            return new Events(Actions.prison,joueur);
        } else {
            return new Events(Actions.neRienFaire, joueur);
        }
    }
    
    public int getMontant() {
        return montant;
    }
    
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
}