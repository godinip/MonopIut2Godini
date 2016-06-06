package Jeu;

import Data.Actions;

public class Gare extends Propriete {

    public Gare(int numero,String nom, int prix) {
        super(numero,nom, prix);
    }
    
    /**
     *
     * @param joueur
     * @return
     */
    @Override
    public int getLoyer(Joueur joueur){
        if (joueur.getNbGare()!=0){
            return (joueur.getNbGare()*25);
        }else{
            return 0;
        }
    }
    
    @Override
    public Data.Actions action(Joueur J){
     if (this.getProprietaire()==null){
            if (J.getArgent()<this.getPrix()){
                return Actions.neRienFaire;
            }else{
                J.payer(this.getPrix());
                this.setProprietaire(J);
                return Actions.acheter;
            }
        
        }else if(this.getProprietaire()==J){
            return Actions.neRienFaire;
        }else{     
            this.getProprietaire().gagnerArgent(this.getLoyer(this.getProprietaire()));
            J.payer(this.getLoyer(this.getProprietaire()));
            return Actions.payerLoyer;    
        }
    }
    
}
