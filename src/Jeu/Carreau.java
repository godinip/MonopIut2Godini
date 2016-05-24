package Jeu;

public abstract class Carreau {

	private int numero;
	private String nomCarreau;
        
        public Carreau(int num,String nom){
            this.numero = num;
            this.nomCarreau = nomCarreau;
        }

	public int getNumero() {
		return this.numero;
	}

}