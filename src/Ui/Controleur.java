package Ui;

import Data.*;
import Jeu.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Controleur {
    
    private Monopoly monopoly;
    private IhmGraph ihmGraph;
    private Observateur observateur;
    private Joueur joueur;
    
    public Controleur(Monopoly monopoly){
        this.monopoly = monopoly;
        ihmGraph = new IhmGraph(this);
        ihmGraph.affiche();
    }
    
    public void tour() {
            Message message = new Message();
            joueur = monopoly.joueurSuivant();
            message.type = Message.Types.EPASSER;
            observateur.notifier(message);
            message.type = Message.Types.JOUEUR;
            observateur.notifier(message);
            if (monopoly.getJoueurs().toArray().length == 1) {
                IhmMessage.afficherBoiteDialogue(joueur.getNomJoueur()+" a gagné",0);
            }else {
                IhmMessage.afficherBoiteDialogue("\nAu tour de " + joueur.getNomJoueur() + " de jouer",0);
                message.type = Message.Types.JOUEUR;
                observateur.notifier(message);
                joueur.setNbDouble(0);
                joueur.setJouer(true);
            }
            message.type = Message.Types.ALANCER;
            observateur.notifier(message);
    }
    
    public void jouerUnCoup() {
        Message message = new Message();
            message.type = Message.Types.ELANCER;
            observateur.notifier(message);
            joueur.setJouer(false);
            if (joueur.getPrison() > 0) {//CAS PRISON
                IhmMessage.afficherBoiteDialogue("Vous êtes en prison pour encore "+joueur.getPrison()+" tours", 0);
                if ((joueur.getCommunautePrison() || joueur.getChancePrison()) && IhmMessage.afficherBoiteDialogue("Voulez-vous utiliser votre carte Sortie de Prison?", 1)) {
                    joueur.setPrison(0);
                    if (joueur.getChancePrison()) {
                        monopoly.addCarteChance(joueur.getCarteChancePrison());
                    } else if (joueur.getCommunautePrison()) {
                        monopoly.addCarteCommunaute(joueur.getCarteCommunautePrison());
                    }
                    joueur.setJouer(true);
                } else {
                    if (lancerDé() == lancerDé()) {
                        IhmMessage.afficherBoiteDialogue("Vous lancez les dés, faites un double et sortez de prison", 0);
                        joueur.setPrison(0);
                        joueur.setJouer(true);
                    } else {
                        if (joueur.getPrison() == 1) {
                            joueur.payer(50);
                            if (joueur.getPerdu() != true) {
                            IhmMessage.afficherBoiteDialogue("Vous lancez les dés, ne faites pas de double et payez 50€ pour sortir de prison", 0);
                            joueur.setPrison(0);
                            joueur.setJouer(true);
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
            gestionEvents(e);
            }
            if (joueur.getJouer()) {
                message.type = Message.Types.ALANCER;
                observateur.notifier(message);
            } else {
                message.type = Message.Types.APASSER;
                observateur.notifier(message);
            }
        }
    
    private Carreau lancerDésAvancer(Joueur joueur) {
        int position = joueur.getPositionCourante().getNumero();
        int n = lancerDé();
        int m = lancerDé();
        if (n == m ) {
            joueur.setNbDouble(joueur.getNbDouble()+1);
            joueur.setJouer(true);
        }
        IhmMessage.afficherBoiteDialogue("Vous faites "+n+" et "+m,0);
        if (joueur.getNbDouble() == 3) {
            joueur.setPositionCourante(getCarreau(11));
            joueur.setPrison(3);
            IhmMessage.afficherBoiteDialogue("C'est votre troisième double, vous êtes envoyé en prison",0);
            joueur.setJouer(false);
        } else {
            joueur.setDernierLancé(n+m);
            if (position+n+m>40) {
                joueur.setPositionCourante(getCarreau(position+n+m-40));
                joueur.gagnerArgent(200);
                IhmMessage.afficherBoiteDialogue("Vous passez par la case départ et gagnez 200€",0);
                String np = joueur.getPositionCourante().getNom();
                IhmMessage.afficherBoiteDialogue("Vous arrivez sur " + joueur.getPositionCourante().getNom(),0);
            } else {
                joueur.setPositionCourante(getCarreau(position+n+m));
                IhmMessage.afficherBoiteDialogue("Vous arrivez sur " + joueur.getPositionCourante().getNom(),0);
            }
        }
        return joueur.getPositionCourante();
    }
    
    private void gestionEvents(Events e) {
        Message message = new Message();
        if (e.getAction() == Actions.acheter) {//CAS ACHETER
            acheterPropriete(joueur,(Propriete) joueur.getPositionCourante());
        } else if (e.getAction() == Actions.carteChance) {//CAS CARTE CHANCE
            IhmMessage.afficherBoiteDialogue(e.message(), 0);
            Carte chance = monopoly.getCarteChance();
            ActionsCarte actionCarte = chance.getAction();
            if (actionCarte == ActionsCarte.SP) {
                IhmMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                joueur.setCarteChancePrison(chance);
            } else if (actionCarte == ActionsCarte.RE) {
                IhmMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                joueur.setPositionCourante(getCarreau(joueur.getPositionCourante().getNumero()-chance.getX()));
                monopoly.addCarteChance(chance);
                joueur.getPositionCourante().action(joueur);
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
                    joueur.setPositionCourante(monopoly.getCarreaux().get(chance.getX()));
                    joueur.gagnerArgent(200);
                e = joueur.getPositionCourante().action(joueur);
                gestionEvents(e);
                }
                joueur.setPositionCourante(getCarreau(chance.getX()));
                monopoly.addCarteChance(chance);
            } else if (actionCarte == ActionsCarte.AP) {
                IhmMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                joueur.setPositionCourante(getCarreau(11));
                joueur.setPrison(3);
                joueur.setJouer(false);
                monopoly.addCarteChance(chance);
            }
        } else if (e.getAction() == Actions.carteCommunaute) {//CAS CARTE COMMUNAUTE
            IhmMessage.afficherBoiteDialogue(e.message(), 0);
            Carte communaute = monopoly.getCarteCommunaute();
            ActionsCarte actionCarte = communaute.getAction();
            if (communaute.getAction() == ActionsCarte.SP) {
                IhmMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                joueur.setCarteCommunautePrison(communaute);
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
                e = joueur.getPositionCourante().action(joueur);
                gestionEvents(e);
            } else if (communaute.getAction() == ActionsCarte.AP) {
                IhmMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                joueur.setPositionCourante(getCarreau(11));
                joueur.setPrison(3);
                joueur.setJouer(false);
                monopoly.addCarteCommunaute(communaute);
            } else if (communaute.getAction() == ActionsCarte.AV) {
                IhmMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                if (joueur.getPositionCourante().getNumero() > communaute.getX()) {
                    joueur.setPositionCourante(monopoly.getCarreaux().get(communaute.getX()));
                    joueur.gagnerArgent(200);
                }
                joueur.setPositionCourante(getCarreau(communaute.getX()));
                monopoly.addCarteCommunaute(communaute);
                e = joueur.getPositionCourante().action(joueur);
                gestionEvents(e);
            }
        } else if (e.getAction() == Actions.prison) {//CAS ENVOYE EN PRISON
            IhmMessage.afficherBoiteDialogue("Vous êtes envoyé en prison",0);
            joueur.setPositionCourante(getCarreau(11));
            joueur.setPrison(3);
            joueur.setJouer(false);
        } else {//AUTRE CAS
            IhmMessage.afficherBoiteDialogue(e.message(),0);
        }
        if (joueur.getPerdu()) {
            IhmMessage.afficherBoiteDialogue("Vous avez perdu",0);
            monopoly.suppJoueur(joueur);
        } else {
            message.type = Message.Types.JOUEUR;
            observateur.notifier(message);
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
    
    public Joueur getJoueur() {
        return joueur;
    }
    
    public void setObservateur(Observateur observateur) {
        this.observateur = observateur;
    }
    
    public ArrayList<ProprieteAConstruire> getListMaisons(){
        ArrayList<ProprieteAConstruire> A = new ArrayList();
        for (ProprieteAConstruire p : joueur.getProprietes()){
            if (p.verificationAjoutMaisons()){
                A.add(p);
            }
        }
        return A;
    }
    
    public ArrayList<ProprieteAConstruire> getListHotels(){
        ArrayList<ProprieteAConstruire> A = new ArrayList();
        for (ProprieteAConstruire p : joueur.getProprietes()){
            if (p.verificationAjoutHotels()){
                A.add(p);
            }
        }
        return A;
    }
    
}