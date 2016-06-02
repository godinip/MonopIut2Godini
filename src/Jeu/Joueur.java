package Jeu;

import Jeu.Gare;
import Jeu.Compagnie;
import Jeu.Carreau;
import java.util.*;

public class Joueur {
    
    private String                              nomJoueur;
    private int                                 argent;
    private Carreau                             positionCourante;
    private int                                 dernierLancé;
    private ArrayList<ProprieteAConstruire>     proprietes;
    private ArrayList<Gare>                     gares;
    private ArrayList<Compagnie>                compagnies;
    private Boolean                             tourDeJeu;
    private Boolean                             perdu = false;
    
    public Joueur(String nomJoueur,Carreau positionCourante) {
        this.nomJoueur = nomJoueur;
        argent = 1500;
        this.positionCourante = positionCourante;
        dernierLancé = 0;
        proprietes = new ArrayList<>();
        gares = new ArrayList<>();
        compagnies = new ArrayList<>();
    }
    
    public void payer(int prix) {
        if (argent>=prix) {
            setArgent(argent-prix);
        } else {
            perdu = true;
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
    
    public void setDernierLancé(int lancé) {
        dernierLancé = lancé;
    }
    
    public int getDernierLancé() {
        return dernierLancé;
    }
    
    public void addPropriete(Propriete propriete) {
        if (propriete instanceof ProprieteAConstruire){
            this.getProprietes().add((ProprieteAConstruire)propriete);
        }else if(propriete instanceof Gare){
            this.getGares().add((Gare)propriete);
        }else{
            this.getCompagnies().add((Compagnie)propriete);
        }
    }
    
    public ArrayList<ProprieteAConstruire> getProprietes(){
        return proprietes;
    }
    
    
    public int getNbGare() {
        return getGares().size();
    }
    
    
    public int getNbCompagnie() {
        return getCompagnies().size();
    }

    /**
     * @return the gares
     */
    public ArrayList<Gare> getGares() {
        return gares;
    }

    /**
     * @return the compagnies
     */
    public ArrayList<Compagnie> getCompagnies() {
        return compagnies;
    }
    
    public boolean getTourDeJeu(){
        return tourDeJeu;
    }
    
    public void setTourDeJeu(boolean tdj){
        tourDeJeu = tdj;
    }
    
    public boolean getPerdu() {
        return perdu;
    }
       
}
