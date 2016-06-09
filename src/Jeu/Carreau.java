package Jeu;

import Data.*;
import java.awt.event.MouseListener;

public abstract class Carreau {
    
    private int numero;
    private String nomCarreau;
    
    public Carreau(int numero,String nomCarreau){
        this.setNumero(numero);
        this.setNom(nomCarreau);
    }
    
    public int getNumero() {
        return numero;
    }
    
    public String getNom(){
        return nomCarreau;
    }
    
    public abstract Events action(Joueur J);
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public void setNom(String nomCarreau) {
        this.nomCarreau = nomCarreau;
    }

}
