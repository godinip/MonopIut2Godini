package Jeu;

public class Carte {
    
    private String texte;
    private Data.ActionsCarte action;
    private int x = 0;
    private int y = 0;
    
    public Carte(String action, String texte, int x, int y) {
        this.action = Data.ActionsCarte.valueOf(action);
        this.setTexte(texte);
        this.setX(x);
        this.setY(y);
    }
    
    public void setTexte(String texte) {
        this.texte = texte;
    }
    public String getTexte() {
        return texte;
    }
    
    
    public Data.ActionsCarte getAction() {
        return action;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
        
    }
     public void setY(int y) {
        this.y = y;   
        
    }
    public int getY() {
        return y;
    }
    
}
