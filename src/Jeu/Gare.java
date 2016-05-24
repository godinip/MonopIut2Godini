package Jeu;

public class Gare extends Propriete {

    public Gare(String nom, int prix) {
        super(nom, prix);
    }
    
    private void acheterPropriete(Joueur joueur) {
	this.setPropriétaire(joueur);
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
    
}