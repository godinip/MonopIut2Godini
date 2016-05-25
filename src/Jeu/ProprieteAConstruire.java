package Jeu;

import Jeu.Propriete;
import Jeu.Groupe;

public class ProprieteAConstruire extends Propriete {

    private Groupe  couleur;
    private int     prix;
    private int[]     loyer;

    public ProprieteAConstruire(int numero,String nom, int prix) {
        super(numero,nom, prix);
    }
    
    public ProprieteAConstruire(int numero,String nm, int prx,Groupe groupe,int[] loyer) {
        super(numero,nm, prx);
        this.setLoyer(loyer);
        this.setCouleur(couleur);
        
    }

    @Override
    public int getPrix() {
	return prix;
    }
    
    public void setPrix(int prix) {
	this.prix = prix;
    }
    
    public int getLoyer() {
        return loyer;
    }
    
    public void setLoyer(int[] loyer) {
        this.loyer = loyer;
    }
    
    public Groupe getCouleur() {
        return couleur;
    }
    
    public void setCouleur(Groupe couleur) {
        this.couleur = couleur;
    }
    
}
