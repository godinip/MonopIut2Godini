package Jeu;

import Jeu.Propriete;
import Ui.*;
import Data.*;
import Jeu.Groupe;
import java.util.Scanner;

public class ProprieteAConstruire extends Propriete {

    private Groupe  couleur;
    private int[] loyer;
    private int    maisons;
    private int coutmaison;
    

    
    public ProprieteAConstruire(int numero,String nm, int prx,Groupe couleur,int[] loyer,int cout) {
        super(numero,nm, prx);
        this.setMaisons(0);
        this.setLoyer(loyer);
        this.setCouleur(couleur);
        this.couleur.addPropriete(this);
        coutmaison = cout;
    }
    
    @Override
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
    public int getLoyers(int i){
        return loyer[i];
    }
    
    public Groupe getCouleur() {
        return couleur;
    }
    
    public void setCouleur(Groupe couleur) {
        this.couleur = couleur;
    }

    public int getMaisons() {
        return maisons;
    }
    
    public void setHotels(int h){
        setMaisons(5 * h);
    }

    public void setMaisons(int maisons) {
    if (maisons>5){
        throw new IllegalArgumentException();
    }else{
        this.maisons = maisons;    
         }
    }
    
    public boolean retirerMaison(){
        if (this.getMaisons()>0 && this.getCouleur().maisonUniformeMoins(maisons)){
            this.setMaisons(this.getMaisons()-1);
            return true;
        }
        return false;
    }
    
    public boolean ajouterMaison(){
        if (this.verificationAjoutMaisons()){
            super.getProprietaire().payer(this.getCoutmaison());
            this.setMaisons(this.getMaisons()+1);
            return true;
        }
        return false;
    }
    
    public boolean ajouterHotel(){
        if (this.verificationAjoutHotels()){
            this.setMaisons(5);
            return true;
        }
        return false;
    }
    
    public boolean retirerHotel(){
        if (this.getMaisons()==5){
            this.setMaisons(4);
            return true;
        }
        return false;
    }

    public int getCoutmaison() {
        return coutmaison;
    }

    public void setCoutmaison(int coutmaison) {
        this.coutmaison = coutmaison;
    }
    
    @Override
    public void clean(){
        super.clean();
        setMaisons(0);
        setHotels(0);
    }
    
    public boolean verificationAjoutMaisons(){
        return (this.getCouleur().LoyerDouble(super.getProprietaire()) && this.getMaisons()<4 && this.getCouleur().maisonUniformePlus(this.getMaisons()));
    }       
    
    public boolean verificationAjoutHotels(){
        return (this.getCouleur().maisonUniformePlus(this.getMaisons()) && this.getMaisons()==4);
    }
}
