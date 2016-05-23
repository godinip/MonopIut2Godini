package Ui;

import Data.Groupe;

public class ProprieteAConstruire extends Propriete {

	private Groupe couleur;
	private int prix;
	private int loyer;

    public ProprieteAConstruire(String nm, int prx) {
        super(nm, prx);
    }

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