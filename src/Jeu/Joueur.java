package Jeu;


import Jeu.Gare;
import Jeu.Compagnie;
import Jeu.Carreau;
import java.util.*;

public class Joueur {
    
    private String  nomJoueur;
    private int     argent;
    /*private ArayList<Gare> gares;
    private Carreau positionCourante;
    private ArrayList<ProprieteAConstruire> proprietes;
    private ArrayList<Compagnie> compagnies;*/
    
    public Joueur() {
        this.argent = 1500;
    }
    
    public void avancer(int dés) {
        
    }
    
    public void payer(int prix) {
        setArgent(argent-prix);
    }
    
    public void gagnerArgent(int gain) {
        setArgent(argent+gain);
    }
    
    public Carreau getPositionCourante() {
        return null;
    }
    
    public void addPropriété(Propriete P) {
        
    }
    
    public void addGare(Gare G) {
        
    }
    
    public void addCompagnie(Gare G) {
        
    }
    
    public int getNbGare() {
       return 0; 
    }
    
    public int getNbCompagnie() {
        return 0; 
    }
    
    public void setNomJoueur(int argent) {
	this.argent = argent;
    }
    
    public int getNomJoueur() {
        return argent;
    }
    
    public void setArgent(int argent) {
	this.argent = argent;
    }
    
    public int getArgent() {
        return argent;
    }
    
}