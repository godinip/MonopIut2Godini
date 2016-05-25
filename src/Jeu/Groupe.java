package Jeu;

import Data.CouleurPropriete;
import java.util.*;

public class Groupe {

	private ArrayList<ProprieteAConstruire> proprietesdugroupe;
	private CouleurPropriete couleur;
        
        public Groupe(CouleurPropriete couleur){
            this.setCouleur(couleur);
            proprietesdugroupe = new ArrayList();
        }

    /**
     * @return the couleur
     */
    public CouleurPropriete getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(CouleurPropriete couleur) {
        this.couleur = couleur;
    }
    
    public void addPropriete(ProprieteAConstruire propriete){
        this.getProprietesdugroupe().add(propriete);
    }

    /**
     * @return the proprietesdugroupe
     */
    public ArrayList<ProprieteAConstruire> getProprietesdugroupe() {
        return proprietesdugroupe;
    }

}