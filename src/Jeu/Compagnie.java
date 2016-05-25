package Jeu;

public class Compagnie extends Propriete {
    
    public Compagnie(int numero,String nom, int prix) {
        super(numero, nom, prix);
    }
    
    @Override
    public int getPrix() {
        return 150;
    }
    
    public int getLoyer(Joueur joueur) {
        if (this.getProprietaire().getNbCompagnie()==1){
            return 4*joueur.getDernierLancé();
        }else{
            return 10*joueur.getDernierLancé();
        }
    }

    @Override
    public void achatPropriete(Joueur joueur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}