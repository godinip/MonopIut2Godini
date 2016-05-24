package Jeu;

public class Compagnie extends Propriete {
    
    public Compagnie(String nom, int prix) {
        super(nom, prix);
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
    
}