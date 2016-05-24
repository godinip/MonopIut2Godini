package Jeu;

import Jeu.Propriete;
import Jeu.Groupe;

public class ProprieteAConstruire extends Propriete {

	private Groupe couleur;
	private int prix;
	private int loyer;

    public ProprieteAConstruire(String nm, int prx) {
        super(nm, prx);
    }
    
    public ProprieteAConstruire(String nm, int prx,Groupe groupe,int loyer) {
        super(nm, prx);
        this.setLoyer(loyer);
        this.setCouleur(couleur);
        
    }

        @Override
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

    /**
     * @return the couleur
     */
    public Groupe getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(Groupe couleur) {
        this.couleur = couleur;
    }

}