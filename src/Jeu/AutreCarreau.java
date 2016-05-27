package Jeu;

public class AutreCarreau extends Carreau {
    
        private int montant;
    
    
        public AutreCarreau(String nom, int numero, int Montant){
            super(numero,nom);
            montant = Montant;
        }

        @Override
        public void action(Joueur J) {
        
        }
}
