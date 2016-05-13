package Diagramme_du_domaine;

public abstract class Propriete {

	private Joueur proprietaire;

	public abstract int getPrix();

	public abstract int getLoyer();

	/**
	 * 
	 * @param j
	 */
	private void acheterPropriete(Joueur j) {
		// TODO - implement Propriete.acheterPropriete
		throw new UnsupportedOperationException();
	}

	public Joueur getProprietaire() {
		return this.proprietaire;
	}

	/**
	 * 
	 * @param j
	 */
	public void setPropriete(Joueur j) {
		// TODO - implement Propriete.setPropriete
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

}