package Diagramme_du_domaine;

public class ProprieteAConstruire extends Propriete {

	private Groupe couleur;
	private int prix;
	private int loyer;

	public int getPrix() {
		return this.prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getLoyer() {
		return this.loyer;
	}

	public void setLoyer(int loyer) {
		this.loyer = loyer;
	}

}