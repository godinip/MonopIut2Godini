package Jeu;

import Jeu.Gare;
import Jeu.Compagnie;
import Jeu.Carreau;
import java.util.*;

public class Joueur {
    
    private String                              nomJoueur;
    private int                                 argent;
    private Carreau                             positionCourante;
    private ArrayList<ProprieteAConstruire>     proprietes;
    private ArrayList<Gare>                     gares;
    private ArrayList<Compagnie>                compagnies;
    
    public Joueur(String nomJoueur,Carreau positionCourante) {
        this.nomJoueur = nomJoueur;
        this.argent = 1500;
        this.gares = new ArrayList();
        this.positionCourante = positionCourante;
        this.proprietes = new ArrayList();
        this.compagnies = new ArrayList();
    }
    
    public void payer(int prix) {
        if (argent>=prix) {
            setArgent(argent-prix);
        } else {
            argent = 0;
        }
    }
    
    public void gagnerArgent(int gain) {
        setArgent(argent+gain);
    }
    
    public void setNomJoueur(String nomJoueur) {
	this.nomJoueur = nomJoueur;
    }
    
    public String getNomJoueur() {
        return nomJoueur;
    }
    
    public void setArgent(int argent) {
	this.argent = argent;
    }
    
    public int getArgent() {
        return argent;
    }
    
    public void setPositionCourante(Carreau carreau) {
        positionCourante = carreau;
    }
    
    public Carreau getPositionCourante() {
        return positionCourante;
    }
    
    public void addPropriete(ProprieteAConstruire propriete) {
        proprietes.add(propriete);
    }
    
    public ArrayList getProprietes(){
        return proprietes;
    }
    
    public void addGare(Gare gare) {
        gares.add(gare);
    }
    
    public int getNbGare() {
        return gares.size();
    }
    
    public void addCompagnie(Compagnie compagnie) {
        compagnies.add(compagnie);
    }
    
    public int getNbCompagnie() {
        return compagnies.size();
    }
    
}
