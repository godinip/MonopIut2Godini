package Jeu;

public class Carte {
    
    private int numero;
    private String texte;
    private Data.ActionsCarte action;
    private int x = 0;
    private int y = 0;
    
    public Carte(int numero, Data.ActionsCarte action, String texte, int x, int y) {
        this.numero = numero;
        this.action = action;
        this.texte = texte;
        this.x = x;
        this.y = y;
    }
    
}
