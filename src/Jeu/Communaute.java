package Jeu;

public class Communaute extends Carreau {

    public Communaute(String nom, int numero) {
        super(nom, numero);
    }
    
    public Events action(Joueur J){
        return new Events(Data.Actions.carteCommunaute, J);
    }
    
}
