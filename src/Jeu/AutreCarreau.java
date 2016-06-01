package Jeu;

import Data.Actions;

public class AutreCarreau extends Carreau {
    
        private int montant;
    
    
        public AutreCarreau(String nom, int numero, int Montant){
            super(numero,nom);
            montant = Montant;
        }

        @Override
        public Data.Actions action(Joueur J) {
            if (montant == 0){
                return Actions.neRienFaire;
            }else if (montant > 0){
                return Actions.gain;
            } else {
                return Actions.payer;
            }
        }

    /**
     * @return the montant
     */
    public int getMontant() {
        return montant;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontant(int montant) {
        this.montant = montant;
    }
}
