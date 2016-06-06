package Jeu;

public class Carte {
    
    private String message;
    
    public Carte(String message, String action) {
        this.message = message;
    }
    
    @Override
    public Data.Actions action(Joueur J){
        return carteChance;
    }
    
}
