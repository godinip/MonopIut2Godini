package Jeu;

public class Gare extends Propriete {

    public Gare(int numero,String nom, int prix) {
        super(numero,nom, prix);
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

    @Override
    public void addPropriete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
