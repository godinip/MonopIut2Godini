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
        
        
        public void addPropriete(ProprieteAConstruire propriete){
            this.getProprietesdugroupe().add(propriete);
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
    
    /**
     * @return the proprietesdugroupe
     */
    public ArrayList<ProprieteAConstruire> getProprietesdugroupe() {
        return proprietesdugroupe;
    }
    
    public boolean LoyerDouble(Joueur j){
        int i=0;
        while (i<this.getProprietesdugroupe().size() && this.getProprietesdugroupe().get(i).getProprietaire()==j){
            i++;
        }
        return i==this.getProprietesdugroupe().size()+1;
    }

    public boolean maisonUniformePlus(int nbMaison){
        for(ProprieteAConstruire propriete : this.getProprietesdugroupe()){
            if(propriete.getMaisons()<nbMaison){
                return false;
            }
            
        }
        return true;
    }
    public boolean maisonUniformeMoins(int nbMaison){
        for(ProprieteAConstruire propriete : this.getProprietesdugroupe()){
            if(propriete.getMaisons()>nbMaison){
                return false;
            }
            
        }
        return true;
    }
    
    
}