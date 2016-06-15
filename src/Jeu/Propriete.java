package Jeu;

import Data.*;
import Jeu.Groupe;
import Jeu.Joueur;

public abstract class Propriete extends Carreau{
    
    private Joueur  proprietaire = null;
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
        
    public void setPrix(int prix) {
        this.prix = prix;
    }
    
    public  void achatPropriete(Joueur joueur){
        joueur.payer(this.getPrix());
        this.setProprietaire(joueur);
        joueur.addPropriete(this);
    }
    
    public Events action(Joueur joueur){
     if (getProprietaire()==null){
            if (joueur.getArgent()<this.getPrix()){
                return new Events(Actions.neRienFaire, joueur);
            }else{
                return new Events(Actions.acheter, joueur, this.getPrix());
            }
        }else if(this.getProprietaire()==joueur){
            return new Events(Actions.neRienFaire, joueur);
        }else{     
            this.getProprietaire().gagnerArgent(this.getLoyer(joueur));
            joueur.payer(this.getLoyer(joueur));
            return new Events(Actions.payerLoyer,joueur, this.getProprietaire(), this.getLoyer(joueur));    
        }
    }
    
    public void clean(){
        proprietaire = null;
    }
    
}
