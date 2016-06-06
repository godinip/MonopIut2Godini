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
            return (getProprietaire().getNbGare()*25);
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
                return Actions.acheter;
            }
        
        }else if(this.getProprietaire()==J){
            return Actions.neRienFaire;
        }else{     
            this.getProprietaire().gagnerArgent(this.getLoyer(J));
            J.payer(this.getLoyer(J));
            return Actions.payerLoyer;    
        }
    }
    
}
