/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Jeu.*;

/**
 *
 * @author coulonv
 */
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
        montant = montant;
    }
    
    public String message(){
        return "pas encore fait";
    }
}
