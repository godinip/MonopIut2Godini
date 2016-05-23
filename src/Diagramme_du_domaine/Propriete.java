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

	/**
	 * 
	 * @param j
	 */
	private void acheterPropriete(Joueur j) {
		// TODO - implement Propriete.acheterPropriete
		null;
	}

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

	/**
	 * 
	 * @param J
	 */
	public void addPropriétaire(Joueur J) {
		// TODO - implement Propriete.addPropriétaire
		throw new UnsupportedOperationException();
	}

	public String getNom() {
		// TODO - implement Propriete.getNom
		return nom;
	}

	public Groupe getGroupe() {
		// TODO - implement Propriete.getGroupe
		throw new UnsupportedOperationException();
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