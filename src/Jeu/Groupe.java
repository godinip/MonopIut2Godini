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
        
        if (proprietesdugroupe.size()==0){return false;}
        for(ProprieteAConstruire Propriete : proprietesdugroupe){
            if (Propriete.getProprietaire()!=j){
                return false;
            }
        }
        return true;
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