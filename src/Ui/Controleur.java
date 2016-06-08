package Ui;
// Idee on va faire un switch qui selon le nombre affiche ou masque un truc selon les listner 
import Data.*;
import Jeu.*;
import java.io.*;
import Ui.*;
import java.util.*;
import javax.swing.*;

public class Controleur {
    
    private IHM  ihm;
    private Monopoly    monopoly;
    private boolean ok = false;
    private IhmGraph ihmGraph;
    private JoueurIhm joueurIhm;
    private int etat = 0;
    public Controleur(Monopoly monopoly){
        
        this.monopoly = monopoly;
        ihm = new IHM(this);
        initialiserPartie();
        
        etatPartie();
    }
    
    public void tourdejeu() {
        while (monopoly.getJoueurs().size() > 1) {
            for (Joueur joueur : monopoly.getJoueurs()) {
                joueur.setTourDeJeu(true);
                ihm.afficher("\nAu tour de " + joueur.getNomJoueur() + " de jouer");
                ihm.messageEtatJouer(joueur);
                jouerUnCoup(joueur);
            }
            if (monopoly.getJoueurs().size() == 1) {
                for (Joueur joueur : monopoly.getJoueurs()) {
                   IhmBoiteMessage.afficherBoiteDialogue(joueur.getNomJoueur()+", vous avez gagné !", 0);
                }
            }
        }
    }
    
    public void jouerUnCoup(Joueur joueur) {
        while (joueur.getTourDeJeu()){
            joueur.setTourDeJeu(false);
            if (joueur.getPrison() > 0) {
                IhmBoiteMessage.afficherBoiteDialogue("Vous êtes en prison pour encore "+joueur.getPrison()+" tours", 0);
                if (joueur.getCommunautePrison()) {
                   if (IhmBoiteMessage.afficherBoiteDialogue("Voulez-vous utiliser votre carte communauté Sortie de prison?", 1)) {
                       joueur.setPrison(0);
                       joueur.setTourDeJeu(true);
                       jouerUnCoup(joueur);
                   }
                }
                if (joueur.getChancePrison()) {
                   if (IhmBoiteMessage.afficherBoiteDialogue("Voulez-vous utiliser votre carte chance Sortie de prison?", 1)) {
                       joueur.setPrison(0);
                       joueur.setTourDeJeu(true);
                       jouerUnCoup(joueur);
                   }
                }
                int a,b;
                if (lancerDé() == lancerDé()) {
                    IhmBoiteMessage.afficherBoiteDialogue("Vous lancez les dés, faites un double et sortez de prison", 0);
                    joueur.setPrison(0);
                    joueur.setTourDeJeu(true);
                    jouerUnCoup(joueur);
                } else {
                    if (joueur.getPrison() == 1) {
                        joueur.payer(50);
                        if (joueur.getPerdu() != true) {
                            IhmBoiteMessage.afficherBoiteDialogue("Vous lancez les dés, ne faites pas de double et payez 50€ pour sortir de prison", 0);
                            joueur.setPrison(0);
                            joueur.setTourDeJeu(true);
                            jouerUnCoup(joueur);
                        }
                    } else {
                        IhmBoiteMessage.afficherBoiteDialogue("Vous lancez les dés, ne faites pas de double et restez en prison pour encore "+joueur.getPrison()+" tours", 0);
                        joueur.setPrison(joueur.getPrison()-1);
                    }
                }
            } else {
                Carreau c = lancerDésAvancer(joueur);
                Events e = c.action(joueur);
                if (e.getAction() == Actions.acheter) {
                    acheterPropriete(joueur,(Propriete) joueur.getPositionCourante());
                } else if (e.getAction() == Actions.carteChance) {
                    IhmBoiteMessage.afficherBoiteDialogue(e.message(), 0);
                    Carte chance = monopoly.getCarteChance();
                    ActionsCarte actionCarte = chance.getAction();
                    if (actionCarte == ActionsCarte.SP) {
                        IhmBoiteMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        joueur.setChancePrison(true);
                    } else if (actionCarte == ActionsCarte.RE) {
                        IhmBoiteMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        joueur.setPositionCourante(getCarreau(joueur.getPositionCourante().getNumero()-chance.getX()));
                        monopoly.addCarteChance(chance);
                    } else if (actionCarte == ActionsCarte.MH) {
                        IhmBoiteMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        joueur.payer(joueur.getNbMaisons()*chance.getX()+joueur.getNbHotels()*chance.getY());
                        monopoly.addCarteChance(chance);
                    } else if (actionCarte == ActionsCarte.GP) {
                        IhmBoiteMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        if (chance.getX() > 0) {
                            joueur.gagnerArgent(chance.getX());
                        } else {
                            joueur.payer(chance.getX());
                        }
                        monopoly.addCarteChance(chance);
                    } else if (actionCarte == ActionsCarte.AV) {
                        IhmBoiteMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        if (joueur.getPositionCourante().getNumero() > chance.getX()) {
                            joueur.gagnerArgent(200);
                        }
                        joueur.setPositionCourante(getCarreau(chance.getX()));
                        monopoly.addCarteChance(chance);
                    } else if (actionCarte == ActionsCarte.AP) {
                        IhmBoiteMessage.afficherBoiteDialogue(chance.getTexte(), 0);
                        joueur.setPositionCourante(getCarreau(11));
                        joueur.setPrison(3);
                        monopoly.addCarteChance(chance);
                    }
                }else if (e.getAction() == Actions.carteCommunaute) {
                    IhmBoiteMessage.afficherBoiteDialogue(e.message(), 0);
                    Carte communaute = monopoly.getCarteCommunaute();
                    ActionsCarte actionCarte = communaute.getAction();
                    if (communaute.getAction() == ActionsCarte.SP) {
                        IhmBoiteMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        joueur.setCommunautePrison(true);
                    } else if (communaute.getAction() == ActionsCarte.GP) {
                        IhmBoiteMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        if (communaute.getX() > 0) {
                            joueur.gagnerArgent(communaute.getX());
                        } else {
                            joueur.payer(communaute.getX());
                        }
                        monopoly.addCarteCommunaute(communaute);
                    } else if (communaute.getAction() == ActionsCarte.AN) {
                        IhmBoiteMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        int i = 0;
                        for (Object j : monopoly.getJoueurs().toArray()) {
                            if ((Joueur)j != joueur) {
                                ((Joueur)j).payer(communaute.getX());
                                i++;
                            }
                        }
                        joueur.gagnerArgent(i*communaute.getX());
                        monopoly.addCarteCommunaute(communaute);
                    } else if (communaute.getAction() == ActionsCarte.DE) {
                        IhmBoiteMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        joueur.setPositionCourante(getCarreau(communaute.getX()));
                    } else if (communaute.getAction() == ActionsCarte.AP) {
                        IhmBoiteMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        joueur.setPositionCourante(getCarreau(11));
                        joueur.setPrison(3);
                        monopoly.addCarteCommunaute(communaute);
                    } else if (communaute.getAction() == ActionsCarte.AV) {
                        IhmBoiteMessage.afficherBoiteDialogue(communaute.getTexte(), 0);
                        if (joueur.getPositionCourante().getNumero() > communaute.getX()) {
                            joueur.gagnerArgent(200);
                        }
                        joueur.setPositionCourante(getCarreau(communaute.getX()));
                        monopoly.addCarteCommunaute(communaute);
                    }
                } else { IhmBoiteMessage.afficherBoiteDialogue(e.message(),0);}
                if (joueur.getPerdu()) {
                    LinkedList<Joueur> joueurs = new LinkedList();
                    joueurs = monopoly.getJoueurs();
                    joueurs.remove(joueur);
                    monopoly.setJoueurs(joueurs);
                    joueur.setTourDeJeu(false);
                    IhmBoiteMessage.afficherBoiteDialogue(joueur.getNomJoueur()+", vous n'avez plus d'argent et perdez", 0);
                }
            }
        }
    }
    
    public void initialiserPartie() {
        this.monopoly.CreerPlateau();
    }    
    
    public void acheterPropriete(Joueur joueur, Propriete achat) {
        if (IhmBoiteMessage.afficherBoiteDialogue("Voulez-vous acheter "+achat.getNom()+" pour "+achat.getPrix()+"€ ?", 1)) {
            achat.achatPropriete(joueur);
        }
    }
    
    private Carreau lancerDésAvancer(Joueur joueur) {
        int position = joueur.getPositionCourante().getNumero();
        int n = lancerDé();
        int m = lancerDé();
        ihm.afficher("Vous avez fait " + n + " et "+ m + " avec les dés");
        if (n == m ) {
            joueur.setTourDeJeu(true);
        }
        joueur.setDernierLancé(n+m);
        if (position+n+m>40) {
            joueur.setPositionCourante(getCarreau(position+n+m-40));
            joueur.gagnerArgent(200);
            ihm.afficher("Vous êtes passé par la case départ. Vous avez donc gagné 200€");
            ihm.afficher("Vous êtes arrivé sur : " + joueur.getPositionCourante().getNom());
            ihm.afficher("Argent actuel :" + joueur.getArgent() + "€");
            return joueur.getPositionCourante();
        } else {
            joueur.setPositionCourante(getCarreau(position+n+m));
            ihm.afficher("Vous êtes arrivé sur : " + joueur.getPositionCourante().getNom());
            ihm.afficher("Argent actuel :" + joueur.getArgent() + "€");
            return joueur.getPositionCourante();
        }
    }
    
    public int lancerDé() {
        return (int) (Math.random()*(6) +1);
    }
    
    public Carreau getCarreau(int numero) {
        return monopoly.getCarreaux().get(numero);
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
    public void achatMaison(ProprieteAConstruire p){
        if (p.ajouterMaison()){
            ihm.afficherBoiteDialogue("Une maison a été construite sur " + p.getNom(), 0);
        } else { ihm.afficherBoiteDialogue("Il est impossible de construire sur maison sur " + p.getNom(), 0);}
    }
    
    public void achatHotel(ProprieteAConstruire p){
        if (p.ajouterHotel()){
            ihm.afficherBoiteDialogue("Un Hotel a été construit sur " + p.getNom(), 0);
        } else {
            ihm.afficherBoiteDialogue("Il est imposible de construire un hotel sur " + p.getNom(), 0);
        }
    }
    public  int getNbJoueur(){
        return ihmGraph.getNbJoueur();
    }
    public  void etatPartie(){
        
        switch(etat){
            case 0:
                 if(!IhmBoiteMessage.afficherBoiteDialogue("ihm?", 1)){
                       monopoly.setJoueurs(ihm.CreationJoueur());
                       setEtat(3);
                       etatPartie();
                }else{
                    ihmGraph = new IhmGraph(this);
                    ihmGraph.affiche();
                }
                break;
            case 1:
                    joueurIhm = new JoueurIhm(getNbJoueur(), this);
                    joueurIhm.affiche();
                break;
            case 2:
                   monopoly.setJoueurs(joueurIhm.getJoueurs());
                   setEtat(3);
                   etatPartie();
                break;
            case 3:
                tourdejeu();
                break;
            default:
                
                break;
        }
    }

    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    public HashMap<Integer, Carreau> getCarreaux() {
        return monopoly.getCarreaux();
    }
    
    public HashMap<String, Groupe> getGroupes() {
        return monopoly.getGroupes();
    }
    
}
