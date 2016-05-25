package Jeu;

public class Gare extends Propriete {

    public Gare(int numero,String nom, int prix) {
        super(numero,nom, prix);
    }
    
    public void achatPropriete(Joueur joueur) {
	super.setPropriétaire(joueur);
        this.getProprietaire().addGare(this); 
    }
        
    @Override
    public int getPrix(){
        return 200;
    }
    
    public int getLoyer(Joueur joueur){
        if (joueur.getNbGare()!=0){
            return (joueur.getNbGare()*25);
        }else{
            return 0;
        }
    }

    @Override
    public void addPropriete() {
        this.getProprietaire().addGare(this); 
    }
    
}
