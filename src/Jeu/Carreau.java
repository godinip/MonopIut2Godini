package Jeu;

public abstract class Carreau {

	private int numero;
	private String nomCarreau;
        
        public Carreau(int numero,String nomCarreau){
            this.numero = numero;
            this.nomCarreau = nomCarreau;
        }

	public int getNumero() {
		return numero;
	}
     /**
       * @return the nomCarreau
       */
        public String getNom(){
            return nomCarreau;
        }

        public abstract void action(Joueur J);

}
