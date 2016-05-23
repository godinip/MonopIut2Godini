package Jeu;

public class Compagnie extends Propriete {

    public Compagnie(String nm, int prx) {
        super(nm, prx);
    }

    @Override
    public int getPrix() {
        return 150;
    }

    
    
    public int getLoyer(Joueur j) {
        if (this.getProprietaire().getNbCompagnie()==1){
            return 4*j.getNbCompagnie();
        }else{
            return 10*j.getNbCompagnie();
        }
    }

}