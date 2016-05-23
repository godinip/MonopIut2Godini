package Data;

import Ui.Propriete;
import Ui.Compagnie;
import Ui.ProprieteAConstruire;
import java.util.*;

public class Joueur {
    
    private String  nomJoueur;
    private int     argent;
    private ArayList<Gare> gares;
    private Carreau positionCourante;
    private ArrayList<ProprieteAConstruire> proprietes;
    private ArrayList<Compagnie> compagnies;
    
    public Joueur() {
        this.argent = 1500;
    }
    
    public void avancer(int dés) {
        ;
    }
    
    public void payer(int prix) {
        setArgent(argent-prix);
    }
    
    public void gagnerArgent(int gain) {
        setArgent(argent+gain);
    }
    
    public void setArgent(int argent) {
	this.argent = argent;
    }
    
    public int getArgent() {
        return argent;
    }
    
    
    
    
/*	
        
	public int avancer(int dés) {
		// TODO - implement Joueur.avancer
		throw new UnsupportedOperationException();
	}
        
	public int getNbGare() {
		// TODO - implement Joueur.getNbGare
		throw new UnsupportedOperationException();
	}
        
	public void addGare(Gare G) {
		// TODO - implement Joueur.addGare
		throw new UnsupportedOperationException();
	}
        
	public Carreau getPositionsCourante() {
		// TODO - implement Joueur.getPositionsCourante
		throw new UnsupportedOperationException();
	}
        
	public void addPropriété(Propriete P) {
		// TODO - implement Joueur.addPropriété
		throw new UnsupportedOperationException();
	}
        
	*/
        
}