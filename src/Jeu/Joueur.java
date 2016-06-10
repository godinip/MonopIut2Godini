package Jeu;

import Jeu.Gare;
import Jeu.Compagnie;
import Jeu.Carreau;
import java.awt.Color;
import java.util.*;

public class Joueur {
    
    private String                              nomJoueur;
    private Color                               joueurCouleur;
    private int                                 argent;
    private Carreau                             positionCourante;
    private int                                 dernierLancé;
    private ArrayList<ProprieteAConstruire>     proprietes;
    private ArrayList<Gare>                     gares;
    private ArrayList<Compagnie>                compagnies;
    private Boolean                             perdu = false;
    private int                                 prison = 0;
    private Carte                               chancePrison;
    private Carte                               communautePrison;
    private boolean                             jouer = false;
    private int                                 nbDouble = 0;
    
    public Joueur(String nomJoueur,Carreau positionCourante) {
        this.setNomJoueur(nomJoueur);
        this.setArgent(1500);
        this.setPositionCourante(positionCourante);
        this.setDernierLancé(0);
        proprietes = new ArrayList<>();
        gares = new ArrayList<>();
        compagnies = new ArrayList<>();
    }
    
    public void payer(int prix) {
        if (getArgent()>=prix) {
            setArgent(getArgent()-prix);
        } else {
            setPerdu( true);
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
            ArrayList<ProprieteAConstruire> p = new ArrayList();
            proprietes.sort(new Comparator<ProprieteAConstruire>() {
        @Override
        public int compare(ProprieteAConstruire P2, ProprieteAConstruire P1)
        {
            if (P1.getNumero() == P2.getNumero()){
                return 0;
            } else { return 1;}
        }
    });
            for (ProprieteAConstruire prop : proprietes){
                
            }
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
    
    public void setPerdu(boolean perdu) {
        this.perdu = perdu;
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
    
    public boolean getChancePrison() {
        return (chancePrison != null);
    }
    
    public void setCarteChancePrison(Carte chancePrison) {
        this.chancePrison = chancePrison;
    }
    
    public Carte getCarteChancePrison(){
        Carte temp = chancePrison;
        chancePrison = null;
        return temp;
    }
    
    public boolean getCommunautePrison() {
        return (communautePrison != null);
    }
    
    public void setCarteCommunautePrison(Carte communautePrison) {
        this.communautePrison = communautePrison;
    }
    
    public Carte getCarteCommunautePrison(){
        Carte temp = communautePrison;
        communautePrison = null;
        return temp;
    }
    
    public int getNbMaisons(){
        int temp;
        int tot = 0;
        for (ProprieteAConstruire p : proprietes){
            temp = p.getMaisons();
            if(temp < 5){
                tot = tot + temp;
            }
        }
        return tot;
    }
    
    public int getNbHotels(){
        int tot = 0;
        for (ProprieteAConstruire p : proprietes){
            if(p.getMaisons() == 5){
                tot = tot + 1;
            }
        }
        return tot;
    }
    
    public void setJouer(boolean jouer) {
        this.jouer = jouer;
    }
    
    public boolean getJouer() {
        return jouer;
    }
    
    public void setNbDouble(int nbDouble) {
        this.nbDouble = nbDouble;
    }
    
    public int getNbDouble() {
        return nbDouble;
    }

    public Color getJoueurCouleur() {
        return joueurCouleur;
    }

    public void setJoueurCouleur(Color joueurCouleur) {
        this.joueurCouleur = joueurCouleur;
    }
    
}
