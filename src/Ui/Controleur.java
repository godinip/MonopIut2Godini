package Ui;

import Data.*;
import Jeu.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Controleur {
    
    private Monopoly monopoly;
    private Observateur observateur;
    private IhmGraph ihmGraph;
    
    public Controleur(Monopoly monopoly){
        this.monopoly = monopoly;
        ihmGraph = new IhmGraph(this);
        ihmGraph.affiche();
    }
    
    public void partie() {
        while (monopoly.getJoueurs().size() > 1) {
            tour();
        }
        if (monopoly.getJoueurs().size() == 1) {
            IhmMessage.afficherBoiteDialogue(monopoly.getJoueurs().getFirst().getNomJoueur()+" a gagné",0);
        }
    }
    
    public void tour() {
        for (Joueur joueur : monopoly.getJoueurs()) {
            if (monopoly.getJoueurs().toArray().length == 1) {
                IhmMessage.afficherBoiteDialogue(joueur.getNomJoueur()+" a gagné",0);
            }else {
                IhmMessage.afficherBoiteDialogue("\nAu tour de " + joueur.getNomJoueur() + " de jouer",0);
                IhmMessage.afficherBoiteJoueur(joueur);
                joueur.setNbDouble(0);
                joueur.setJouer(true);
                jouerUnCoup(joueur);
                if (joueur.getPerdu()) {
                    IhmMessage.afficherBoiteDialogue(joueur.getNomJoueur()+" a perdu",0);
                    monopoly.suppJoueur(joueur);
                } else {
                    IhmMessage.afficherBoiteJoueur(joueur);
                }
            }
        }
    }
    
    public void jouerUnCoup(Joueur joueur) {
        while (joueur.getJouer()) {
            joueur.setJouer(false);
            if (joueur.getPrison() > 0) {//CAS PRISON
                IhmMessage.afficherBoiteDialogue("Vous êtes en prison pour encore "+joueur.getPrison()+" tours", 0);
                if ((joueur.getCommunautePrison() || joueur.getChancePrison()) && IhmMessage.afficherBoiteDialogue("Voulez-vous utiliser votre carte Sortie de Prison?", 1)) {
                        joueur.setPrison(0);
                        if (joueur.getChancePrison()) {
                            joueur.setChancePrison(false);
                        } else if (joueur.getCommunautePrison()) {
                            joueur.setCommunautePrison(false);
                        }
                        jouerUnCoup(joueur);
                } else {
                    if (lancerDé() == lancerDé()) {
                        IhmMessage.afficherBoiteDialogue("Vous lancez les dés, faites un double et sortez de prison", 0);
                        joueur.setPrison(0);
                        jouerUnCoup(joueur);
                    } else {
                        if (joueur.getPrison() == 1) {
                            joueur.payer(50);
                            if (joueur.getPerdu() != true) {
                                IhmMessage.afficherBoiteDialogue("Vous lancez les dés, ne faites pas de double et payez 50€ pour sortir de prison", 0);
                                joueur.setPrison(0);
                                jouerUnCoup(joueur);
                            }
                        } else {
                            IhmMessage.afficherBoiteDialogue("Vous lancez les dés, ne faites pas de double et restez en prison pour encore "+joueur.getPrison()+" tours", 0);
                            joueur.setPrison(joueur.getPrison()-1);
                        }
                    }
                }
            } else {//CAS NON PRISON
                Carreau c = lancerDésAvancer(joueur);
                Events e = c.action(joueur);
                if (e.getAction() == Actions.acheter) {//CAS ACHETER
                    acheterPropriete(joueur,(Propriete) joueur.getPositionCourante());
                } else if (e.getAction() == Actions.carteChance) {//CAS CARTE CHANCE
                    IhmMessage.afficherBoiteDialogue(e.message(), 0);
                    Carte chance = monopoly.getCarteChance();
                    ActionsCarte actionCarte = chance.getAction();
                    if (actionCarte == ActionsCarte.SP) {
                        IhmMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        joueur.setChancePrison(true);
                    } else if (actionCarte == ActionsCarte.RE) {
                        IhmMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        joueur.setPositionCourante(getCarreau(joueur.getPositionCourante().getNumero()-chance.getX()));
                        monopoly.addCarteChance(chance);
                    } else if (actionCarte == ActionsCarte.MH) {
                        IhmMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        joueur.payer(joueur.getNbMaisons()*chance.getX()+joueur.getNbHotels()*chance.getY());
                        monopoly.addCarteChance(chance);
                    } else if (chance.getAction() == ActionsCarte.GA) {
                        IhmMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        joueur.gagnerArgent(chance.getX());
                        monopoly.addCarteChance(chance);
                    } else if (chance.getAction() == ActionsCarte.PA) {
                        IhmMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        joueur.payer(chance.getX());
                        monopoly.addCarteChance(chance);
                    } else if (actionCarte == ActionsCarte.AV) {
                        IhmMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        if (joueur.getPositionCourante().getNumero() > chance.getX()) {
                            joueur.gagnerArgent(200);
                        }
                        joueur.setPositionCourante(getCarreau(chance.getX()));
                        monopoly.addCarteChance(chance);
                    } else if (actionCarte == ActionsCarte.AP) {
                        IhmMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        joueur.setPositionCourante(getCarreau(11));
                        joueur.setPrison(3);
                        monopoly.addCarteChance(chance);
                    }
                } else if (e.getAction() == Actions.carteCommunaute) {//CAS CARTE COMMMUNAUTE
                    IhmMessage.afficherBoiteDialogue(e.message(), 0);
                    Carte communaute = monopoly.getCarteCommunaute();
                    ActionsCarte actionCarte = communaute.getAction();
                    if (communaute.getAction() == ActionsCarte.SP) {
                        IhmMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        joueur.setCommunautePrison(true);
                    } else if (communaute.getAction() == ActionsCarte.GA) {
                        IhmMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        joueur.gagnerArgent(communaute.getX());
                        monopoly.addCarteCommunaute(communaute);
                    } else if (communaute.getAction() == ActionsCarte.PA) {
                        IhmMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        joueur.payer(communaute.getX());
                        monopoly.addCarteCommunaute(communaute);
                    } else if (communaute.getAction() == ActionsCarte.AN) {
                        IhmMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        int i = 0;
                        for (Joueur j : monopoly.getJoueurs()) {
                            if (j != joueur) {
                                j.payer(communaute.getX());
                                i++;
                            }
                        }
                        joueur.gagnerArgent(i*communaute.getX());
                        monopoly.addCarteCommunaute(communaute);
                    } else if (communaute.getAction() == ActionsCarte.DE) {
                        IhmMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        joueur.setPositionCourante(getCarreau(communaute.getX()));
                    } else if (communaute.getAction() == ActionsCarte.AP) {
                        IhmMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        joueur.setPositionCourante(getCarreau(11));
                        joueur.setPrison(3);
                        monopoly.addCarteCommunaute(communaute);
                    } else if (communaute.getAction() == ActionsCarte.AV) {
                        IhmMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        if (joueur.getPositionCourante().getNumero() > communaute.getX()) {
                            joueur.gagnerArgent(200);
                        }
                        joueur.setPositionCourante(getCarreau(communaute.getX()));
                        monopoly.addCarteCommunaute(communaute);
                    }
                } else {//AUTRE CAS
                    IhmMessage.afficherBoiteDialogue(e.message(),0);
                }
                //ACHETER MAISONS
                //achatMaison(,);
                //ACHETER HOTELS
                //achatHotel(,);
            }
        }
    }
    
    private Carreau lancerDésAvancer(Joueur joueur) {
        int position = joueur.getPositionCourante().getNumero();
        int n = lancerDé();
        int m = lancerDé();
        IhmMessage.afficherBoiteDialogue("Vous avez fait " + n + " et "+ m + " avec les dés",0);
        if (n == m ) {
            joueur.setNbDouble(joueur.getNbDouble()+1);
            if (joueur.getNbDouble() == 3) {
                joueur.setPositionCourante(getCarreau(11));
                joueur.setPrison(3);
                IhmMessage.afficherBoiteDialogue("Vous avez fait 3 doubles et êtes envoyé en prison",0);
            }
            joueur.setJouer(true);
        }
        joueur.setDernierLancé(n+m);
        if (position+n+m>40) {
            joueur.setPositionCourante(getCarreau(position+n+m-40));
            joueur.gagnerArgent(200);
            IhmMessage.afficherBoiteDialogue("Vous passez par la case départet gagnez 200€",0);
            IhmMessage.afficherBoiteDialogue("Vous arrivez " + joueur.getPositionCourante().getNom(),0);
            IhmMessage.afficherBoiteDialogue("Votre argent actuel est de " + joueur.getArgent() + "€",0);
            return joueur.getPositionCourante();
        } else {
            joueur.setPositionCourante(getCarreau(position+n+m));
            IhmMessage.afficherBoiteDialogue("Vous arrivez " + joueur.getPositionCourante().getNom(),0);
            IhmMessage.afficherBoiteDialogue("Votre argent actuel est de " + joueur.getArgent() + "€",0);
            return joueur.getPositionCourante();
        }
    }
    
    private int lancerDé() {
        return (int) (Math.random()*(6)+1);
    }
    
    public Carreau getCarreau(int numero) {
        return monopoly.getCarreaux().get(numero);
    }
    
    public void acheterPropriete(Joueur joueur, Propriete achat) {
        if (IhmMessage.afficherBoiteDialogue("Voulez-vous acheter "+achat.getNom()+" pour "+achat.getPrix()+"€ ?", 1)) {
            achat.achatPropriete(joueur);
        }
    }
    
    public void achatMaison(ProprieteAConstruire p){
        if (p.ajouterMaison()){
            IhmMessage.afficherBoiteDialogue("Une maison a été construite sur " + p.getNom(), 0);
        } else { IhmMessage.afficherBoiteDialogue("Il est impossible de construire sur maison sur " + p.getNom(), 0);}
    }
    
    public void achatHotel(ProprieteAConstruire p){
        if (p.ajouterHotel()){
            IhmMessage.afficherBoiteDialogue("Un Hotel a été construit sur " + p.getNom(), 0);
        } else {
            IhmMessage.afficherBoiteDialogue("Il est imposible de construire un hotel sur " + p.getNom(), 0);
        }
    }
    
    public int getNbJoueurs() {
        return monopoly.getJoueurs().size();
    }
    
    public void setJoueurs(LinkedList<Joueur> joueurs) {
        monopoly.setJoueurs(joueurs);
    }
    
}
