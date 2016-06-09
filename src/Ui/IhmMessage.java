package Ui;

import Jeu.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class IhmMessage {
    
    public static boolean afficherBoiteDialogue(String message, Integer mode) {
        int response ;
        switch(mode) {
            case 0 :
                JOptionPane.showMessageDialog(null, message);
                return true ;
           case 1 :
                response = JOptionPane.showConfirmDialog(   null, 
                                                            message, 
                                                            "Veuillez confirmer l'opération",
                                                            JOptionPane.YES_NO_OPTION, 
                                                            JOptionPane.QUESTION_MESSAGE);
                return response == JOptionPane.YES_OPTION ;
        }
        return false;
    }
    
    public static void afficherBoiteJoueur(Joueur joueur) {
        String d = "\nPosition Joueur : "+joueur.getPositionCourante().getNom()
                +"\nArgent actuel : "+joueur.getArgent()+"€"
                +"\nPropriétés : ";
        if (joueur.getProprietes().isEmpty()){
            d = d+"Aucune Propriété constructible n'est possédée";
        } else {
            for (ProprieteAConstruire p : joueur.getProprietes()){
                d = d+"\n"+p.getNom();
            }
        }
        d = d+"\nGares : ";
        if (joueur.getGares().isEmpty()){
            d = d+"Aucune Gare n'est possédée";
        } else {
            for (Gare g : joueur.getGares()){
                d = d+"\n"+g.getNom();
            }
        }
        d = d+"\nCompagnies : ";
        if (joueur.getCompagnies().isEmpty()){
            d = d+"Aucune Compagnie n'est possédée";
        } else {
            for (Compagnie c : joueur.getCompagnies()){
                d = d+"\n"+c.getNom();
            }
        }
        afficherBoiteDialogue(d,0);
    }
    
}
