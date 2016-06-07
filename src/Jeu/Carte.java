package Jeu;

public class Carte {
    
    private Data.ActionsCarte action;
    private float x = 0;
    private float y = 0;
    
    public Carte(Data.ActionsCarte action, int x, int y) {
        this.action = action;
        this.x = x;
        this.y = y;
    }
    
}
