package Jeu;

public abstract class Carreau {

	private int numero;
	private String nomCarreau;
        
        public Carreau(int numero,String nomCarreau){
            this.setNumero(numero);
            this.setNomCarreau(nomCarreau);
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

    /**
     *
     * @param J
     * @return
     */
    public abstract Data.Actions action(Joueur J);

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @param nomCarreau the nomCarreau to set
     */
    public void setNomCarreau(String nomCarreau) {
        this.nomCarreau = nomCarreau;
    }

}
