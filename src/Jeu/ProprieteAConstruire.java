package Jeu;

import Jeu.Propriete;
import Ui.*;
import Data.*;
import Jeu.Groupe;
import java.util.Scanner;

public class ProprieteAConstruire extends Propriete {

    private Groupe  couleur;
    private int     prix;
    private int[] loyer;
    private int    maisons;

    
    public ProprieteAConstruire(int numero,String nm, int prx,Groupe couleur,int[] loyer) {
        super(numero,nm, prx);
        this.setMaisons(0);
        this.setLoyer(loyer);
        this.setCouleur(couleur);
        
    }

    
    @Override
    public void setPrix(int prix) {
	this.prix = prix;
    }
    
    public int getLoyer(Joueur joueur) {
        if (this.getCouleur().LoyerDouble(this.getProprietaire()) && this.getMaisons()==0){
            return 2*loyer[0];
        }else{
            return loyer[this.getMaisons()];
        }
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

    /**
     * @return the maisons
     */
    public int getMaisons() {
        return maisons;
    }

    /**
     * @param maisons the maisons to set
     */
    public void setMaisons(int maisons) {
    if (maisons>5){
        throw new IllegalArgumentException();
    }else{
        this.maisons = maisons;    
         }
    }
    
    
    @Override
    public Data.Actions action(Joueur J){
        if (this.getProprietaire()==null){
            if (J.getArgent()<this.getPrix()){
                return Actions.neRienFaire;
            }else{
                return Actions.acheter;
            }
        
        }else if(this.getProprietaire()==J){
            return Actions.neRienFaire;
        }else{         
            return Actions.payerLoyer;    
        }
        
        
    }


}
