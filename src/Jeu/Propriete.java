package Jeu;

import Jeu.Groupe;
import Jeu.Joueur;

public abstract class Propriete extends Carreau{

	private Joueur proprietaire;
        
        private int prix;
        
        private String nom;
        
        
        public Propriete(String nm, int prx){
            super(prx, nom);
        }

	public abstract int getPrix();


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