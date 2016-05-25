package Jeu;

import Jeu.Propriete;
import Jeu.Groupe;
import java.util.Scanner;

public class ProprieteAConstruire extends Propriete {

    private Groupe  couleur;
    private int     prix;
    private int[] loyer;
    private int    maisons;

    public ProprieteAConstruire(int numero,String nom, int prix) {
        super(numero,nom, prix);
    }
    
    public ProprieteAConstruire(int numero,String nm, int prx,Groupe couleur,int[] loyer) {
        super(numero,nm, prx);
        this.setMaisons(0);
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
    public void achatPropriete(Joueur joueur) {
    }
    
    
    /*
    @Override
    public void action(Joueur J){
       Scanner choix = new  Scanner(System.in);
        if (this.getProprietaire()==null){
            if (J.getArgent()>=this.getPrix()){
                System.out.println("Vous n'avez pas assez d'argent");
            }else{
                System.out.println("Vouler vous acheter la Propriete: "+this.getNom()+" (oui/non)");
                
                  }
        
        }else{
            
        }
        
        
    }
*/

}
