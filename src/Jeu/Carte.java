package Jeu;

public class Carte {
    
    private int numero;
    private Data.ActionsCarte action;
    private int x = 0;
    private int y = 0;
    
    public Carte(Data.ActionsCarte action, int numero, int x, int y) {
        this.numero = numero;
        this.action = action;
        this.x = x;
        this.y = y;
    }
    
}
