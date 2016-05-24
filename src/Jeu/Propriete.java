package Jeu;

import Jeu.Groupe;
import Jeu.Joueur;

public abstract class Propriete extends Carreau{

	private Joueur  proprietaire;
        private int     prix;
        private String  nom;
        
        public Propriete(String nom, int prix){
            super(nom, prix);
        }
        
	public abstract int getPrix();
        
	public Joueur getProprietaire() {
		return this.proprietaire;
	}
        
	public void action(Joueur j) {
		// TODO - implement Propriete.action
		throw new UnsupportedOperationException();
	}
        
	public String getNom() {
		// TODO - implement Propriete.getNom
		return nom;
	}
        
	public void setPropriétaire(Joueur J) {
            proprietaire = J;
	}

}