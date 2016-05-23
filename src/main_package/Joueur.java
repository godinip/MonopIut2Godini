package main_package;

import java.util.*;
import Diagramme_du_domaine.*;

public class Joueur {

	Collection<Gare> gares;
	private Carreau positionCourante;
	private ArrayList<ProprieteAConstruire> proprietes;
	private ArrayList<Compagnie> compagnies;
	private String nomJoueur;
	private int argent = 1500;

	/**
	 * 
	 * @param l
	 */
	public void payer(int prix) {
		// TODO - implement Joueur.payerLoyer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param l
	 */
	public void recevoirLoyer(int l) {
		// TODO - implement Joueur.recevoirLoyer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dés
	 */
	public int avancer(int dés) {
		// TODO - implement Joueur.avancer
		throw new UnsupportedOperationException();
	}

	public int getNbGare() {
		// TODO - implement Joueur.getNbGare
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param G
	 */
	public void addGare(Gare G) {
		// TODO - implement Joueur.addGare
		throw new UnsupportedOperationException();
	}

	public int getCash() {
		return this.cash;
	}

	public Carreau getPositionsCourante() {
		// TODO - implement Joueur.getPositionsCourante
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param P
	 */
	public void addPropriété(Propriete P) {
		// TODO - implement Joueur.addPropriété
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param money
	 */
	public void setCash(int money) {
		this.cash = money;
	}

}