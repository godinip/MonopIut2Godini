package Jeu;

import Data.Actions;
import Jeu.Groupe;
import Jeu.Joueur;

public abstract class Propriete extends Carreau{

	private Joueur  proprietaire;
        private int     prix;

        public Propriete(int numero,String nom, int prix){
            super(numero, nom);
            this.setPrix(prix);
        }
        
	public int getPrix(){
            return prix;
        }
        

        public abstract int getLoyer(Joueur joueur);
        
	public Joueur getProprietaire() {
		return this.proprietaire;
	}
        
        
	public void setProprietaire(Joueur J) {
            proprietaire = J;
	}

    /**
     * @param prix the prix to set
     */
    public void setPrix(int prix) {
        this.prix = prix;
    } 

    
    public  void achatPropriete(Joueur joueur){
        joueur.payer(this.getPrix());
        this.setProprietaire(joueur);
        joueur.addPropriete(this);
    }
    
    @Override
    public abstract Data.Actions action(Joueur J);
    
}
