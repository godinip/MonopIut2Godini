package Diagramme_du_domaine;

import main_package.Groupe;
import main_package.Joueur;

public abstract class Propriete {

	private Joueur proprietaire;
        
        private int prix;
        
        private String nom;
        
        
        public Propriete(String nm, int prx){
            prix = prx;
            nom = nm;
        }

	public abstract int getPrix();

	public abstract int getLoyer();

	public Joueur getProprietaire() {
		return this.proprietaire;
	}

	/**
	 * 
	 * @param j
	 */
	public void action(Joueur j) {
		// TODO - implement Propriete.action
		throw new UnsupportedOperationException();
	}


	public String getNom() {
		// TODO - implement Propriete.getNom
		return nom;
	}

	/**
	 * 
	 * @param J
	 */
	public void setPropriétaire(Joueur J) {
		// TODO - implement Propriete.setPropriétaire
		proprietaire = J;
	}

}