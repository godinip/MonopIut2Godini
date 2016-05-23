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
    
    public Joueur() {
        nomJoueur = "Joueur";
        argent = 1500;
        gares = new ArrayList();
        positionCourante = new Carreau();
        proprietes = new ArrayList();
        compagnies = new ArrayList();
    }
    
    public Joueur(String nomJoueur,int argent,ArrayList<Gare> gares,Carreau positionCourante,ArrayList<ProprieteAConstruire> proprietes,ArrayList<Compagnie> compagnies) {
        this.nomJoueur = nomJoueur;
        this.argent = argent;
        this.gares = gares;
        this.positionCourante = positionCourante;
        this.proprietes = proprietes;
        this.compagnies = compagnies;
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
    
    public void avancer(int dés) {
        Carreau carreau = new Carreau();
        for (carreau Carreau : carreau.getCarreaux()) {
            if (carreau.getNumero()+dés<=40 && carreau.getNumero() == positionCourante.getNumero()+dés) {
                setPositionCourante(carreau);
            } else if (carreau.getNumero()+dés>40 && carreau.getNumero() == positionCourante.getNumero()+dés-40) {
                setPositionCourante(carreau);
            }
        }
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