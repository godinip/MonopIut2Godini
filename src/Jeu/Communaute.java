package Jeu;

public class Communaute extends Carreau {

    public Communaute(String nom, int numero) {
        super(nom, numero);
    }
    
    @Override
    public Data.Actions action(Joueur J){
        return Data.Actions.carteCommunaute;
    }
    
}