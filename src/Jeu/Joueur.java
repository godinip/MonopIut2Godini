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
    private int                                 prison = 0;
    private int                                 chancePrison = 0;
    private int                                 communautePrison = 0;
    
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
        if (getArgent()>=prix) {
            setArgent(getArgent()-prix);
        } else {
            setPerdu((Boolean) true);
        }
    }
    
    public void gagnerArgent(int gain) {
        setArgent(getArgent()+gain);
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
    
    public ArrayList<Gare> getGares() {
        return gares;
    }
    
    public ArrayList<Compagnie> getCompagnies() {
        return compagnies;
    }
    
    public boolean getTourDeJeu(){
        return tourDeJeu;
    }
    
    public void setTourDeJeu(boolean tdj){
        setTourDeJeu((Boolean) tdj);
    }
    
    public boolean getPerdu() {
        return perdu;
    }
    
    public void setPrison(int prison) {
        this.prison = prison;
    }
    
    public int getPrison() {
        return prison;
    }
    
    public int getChancePrison() {
        return chancePrison;
    }
    
    public void setChancePrison(int chancePrison) {
        this.chancePrison = chancePrison;
    }
    
    public int getCommunautePrison() {
        return communautePrison;
    }
    
    public void setCommunautePrison(int communautePrison) {
        this.communautePrison = communautePrison;
    }
    
}
