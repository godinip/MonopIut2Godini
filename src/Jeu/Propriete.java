package Jeu;

import Jeu.Groupe;
import Jeu.Joueur;

public abstract class Propriete extends Carreau{

	private Joueur  proprietaire;
        private int     prix;
        private String  nom;      

        public Propriete(int numero,String nom, int prix){
            super(numero, nom);
            this.setPrix(prix);

        }
        
	public abstract int getPrix();
        
    /**
     *
     * @param joueur
     * @return
     */
    public abstract int getLoyer(Joueur joueur);
        
	public Joueur getProprietaire() {
		return this.proprietaire;
	}
        
        @Override
	public Data.Actions action(Joueur j) {
		// TODO - implement Propriete.action
		throw new UnsupportedOperationException();
	}
        
        @Override
	public String getNom() {
		// TODO - implement Propriete.getNom
		return super.getNom();
	}
        
	public void setPropriétaire(Joueur J) {
            proprietaire = J;
	}

    /**
     * @param prix the prix to set
     */
    public void setPrix(int prix) {
        this.prix = prix;
    } 

    
    public  void achatPropriete(Joueur joueur){
        joueur.payer(this.getPrix());
        this.setPropriétaire(joueur);
        joueur.addPropriete(this);
    }
}
