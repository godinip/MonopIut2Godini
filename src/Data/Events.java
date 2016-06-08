package Data;

import Jeu.*;

public class Events {
    
    private Actions acte;
    private int montant;
    private Joueur jjoue,jreceveur;
    
    public Events(){
        acte = Actions.neRienFaire;
    }
    
    public Events(Actions action, Joueur j1){
        acte = action;
        jjoue = j1;
    }
    
    public Events(Actions action, Joueur j1, int cout){
        acte = action;
        jjoue = j1;
        montant = cout;
    }
    
    public Events(Actions action, Joueur j1, Joueur j2, int prix){
        acte = action;
        jjoue = j1;
        jreceveur = j2;
        montant = prix;
    }
    
    public String message(){
        String message = "";
        switch(acte.toString()){
            case "gain":
                message = jjoue.getNomJoueur() + " a gagné " + montant + "€";
                break;
            case "payerLoyer":
                message = jjoue.getNomJoueur() + " est arrivé sur la propriete de " + jreceveur.getNomJoueur() + " et lui a versé" + montant + "€";
                break;
            case "acheter":
                message = jjoue.getNomJoueur() + " a acheté la propriete " + jjoue.getPositionCourante().getNom() + " pour " + montant + "€";
                break;
            case "payer":
                message = jjoue.getNomJoueur() + " paye " + montant + "€";
                break;
            case "neRienFaire":
                message = "Il ne se passe rien...";
                break;
            case "carteChance":
                message = jjoue.getNomJoueur() + " tire une carte Chance";
                break;
            case "carteCommunaute":
                message = jjoue.getNomJoueur() + " tire une carte Caisse de Communauté";
                break;
            case "prison":
                message = jjoue.getNomJoueur() + " va tout droit à la prison. Eat shit m*therf*cking nigga";
                break;
            default:
                message = "ce cas n'est pas géré";
        }
        return message;
    }
    
    public Actions getAction(){
        return acte;
    }
    
}