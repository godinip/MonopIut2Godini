package Jeu;

public class Carte {
    
    private int numero;
    private String texte;
    private Data.ActionsCarte action;
    private int x = 0;
    private int y = 0;
    
    public Carte(String texte, int numero, Data.ActionsCarte action, int x, int y) {
        this.texte = texte;
        this.numero = numero;
        this.action = action;
        this.x = x;
        this.y = y;
    }
    
}
