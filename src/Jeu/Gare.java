package Jeu;

public class Gare extends Propriete {

    public Gare(String nm, int prx) {
        super(nm, prx);
    }

	/**
	 * 
	 * @param j
	 */
	private void acheterPropriete(Joueur j) {
		// TODO - implement Gare.acheterPropriete
		this.setPropriétaire(j);
	}
        /*connection*/
        
    @Override
        public int getPrix(){
            return 25;
        }

}