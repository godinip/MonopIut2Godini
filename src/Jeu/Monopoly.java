package Jeu;

import Data.CouleurPropriete;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Monopoly {
    
    private HashMap<Integer,Carreau> carreaux = new HashMap<>();
    private HashMap<String,Groupe> groupes = new HashMap<String,Groupe>();
    private LinkedList<Joueur> joueurs = new LinkedList<>();
    private int nbMaisons = 32;
    private int nbHotels = 12;
    private ArrayList<CarteChance> cartesChance = new ArrayList();
    private ArrayList<CarteCommunaute> cartesCommunaute = new ArrayList();
    
    public Monopoly() {
        
    }
    
    public void CreerPlateau(String dataFilename) {
        buildGamePlateau(dataFilename);
    }
    
    public void CreerCartes(String chance,String communaute) {
        buildCartesChance(chance);
        buildCartesCommunaute(communaute);
                
    }
    
    private void buildGamePlateau(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");
            for (int i=0; i<data.size(); ++i) {
                String caseType = data.get(i)[0];
                if (caseType.compareTo("P") == 0) {
                    int[] loyer = new int[6];
                    loyer[0] = Integer.parseInt(data.get(i)[5]);
                    loyer[1] = Integer.parseInt(data.get(i)[6]);
                    loyer[2] = Integer.parseInt(data.get(i)[7]);
                    loyer[3] = Integer.parseInt(data.get(i)[8]);
                    loyer[4] = Integer.parseInt(data.get(i)[9]);
                    loyer[5] = Integer.parseInt(data.get(i)[10]);
                    if (!groupes.containsValue(data.get(i)[3])) {
                        Groupe nouvGroupe = new Groupe(CouleurPropriete.valueOf(data.get(i)[3]));
                        getGroupes().put(data.get(i)[3], nouvGroupe);
                    }
                    ProprieteAConstruire nouvellePropriete = new ProprieteAConstruire(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[4]),getGroupes().get(data.get(i)[3]),loyer);
                    carreaux.put(nouvellePropriete.getNumero(), nouvellePropriete);
                }
                else if (caseType.compareTo("G") == 0) {
                    Gare nouvelleGare = new Gare(Integer.parseInt(data.get(i)[1]),data.get(i)[2],Integer.parseInt(data.get(i)[3]));
                    carreaux.put(nouvelleGare.getNumero(), nouvelleGare);
                }
                else if (caseType.compareTo("C") == 0) {
                    Compagnie nouvelleCompagnie = new Compagnie(Integer.parseInt(data.get(i)[1]),data.get(i)[2],Integer.parseInt(data.get(i)[3]));
                    carreaux.put(nouvelleCompagnie.getNumero(), nouvelleCompagnie);
                }
                else if (caseType.compareTo("AU") == 0) {
                    AutreCarreau nouveauCarreau = new AutreCarreau(data.get(i)[2],Integer.parseInt(data.get(i)[1]),Integer.parseInt(data.get(i)[3]));
                    carreaux.put(nouveauCarreau.getNumero(), nouveauCarreau);
                }
                else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
		}
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        }
        catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
    
    private void buildCartesChance(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, "|");
            for (int i=0; i<data.size(); ++i) {
                String caseType = data.get(i)[0];
                CarteChance c;
                if (caseType.compareTo("SP") == 0) {
                    cartesChance.add(c = new CarteChance(data.get(i)[1]));
                }
                else if (caseType.compareTo("RE") == 0) {
                    cartesChance.add(c = new CarteChance(data.get(i)[1]));
                }
                else if (caseType.compareTo("PC") == 0) {
                    cartesChance.add(c = new CarteChance(data.get(i)[1]));
                }
                else if (caseType.compareTo("GP") == 0) {
                    cartesChance.add(c = new CarteChance(data.get(i)[1]));
                }
                else if (caseType.compareTo("AV") == 0) {
                    cartesChance.add(c = new CarteChance(data.get(i)[1]));
                }
                else if (caseType.compareTo("AP") == 0) {
                    cartesChance.add(c = new CarteChance(data.get(i)[1]));
                }
                else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
		}
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        }
        catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
    
    private void buildCartesCommunaute(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, "|");
            for (int i=0; i<data.size(); ++i) {
                String caseType = data.get(i)[0];
                CarteCommunaute c;
                if (caseType.compareTo("SP") == 0) {
                    cartesCommunaute.add(c = new CarteCommunaute(data.get(i)[1])));
                }
                else if (caseType.compareTo("GP") == 0) {
                    cartesCommunaute.add(c = new CarteCommunaute(data.get(i)[1])));
                }
                else if (caseType.compareTo("AN") == 0) {
                    cartesCommunaute.add(c = new CarteCommunaute(data.get(i)[1])));
                }
                else if (caseType.compareTo("DE") == 0) {
                    cartesCommunaute.add(c = new CarteCommunaute(data.get(i)[1])));
                }
                else if (caseType.compareTo("AP") == 0) {
                    cartesCommunaute.add(c = new CarteCommunaute(data.get(i)[1])));
                }
                else if (caseType.compareTo("AV") == 0) {
                    
                }
                else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
		}
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        }
        catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
    
    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();
        BufferedReader reader  = new BufferedReader(new FileReader(filename));
        String line = null;
        while((line = reader.readLine()) != null){
            data.add(line.split(token));
        }
        reader.close();
        return data;
    }
    
    public HashMap<Integer,Carreau> getCarreaux() {
        return carreaux;
    }
    
    public LinkedList<Joueur> getJoueurs() {
        return joueurs;
    }
    
    public void setJoueurs(LinkedList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
    
    public void addJoueur(Joueur j){
        this.joueurs.add(j);
    }
    
    public HashMap<String,Groupe> getGroupes() {
        return groupes;
    }
    
    public void setnbMaisons(int nbMaisons) {
        this.nbMaisons=nbMaisons;
    }
    
    public int getnbMaisons() {
        return nbMaisons;
    }
    
    public void setnbHotels(int nbHotels) {
        this.nbHotels=nbHotels;
    }
    
    public int getnbHotels() {
        return nbHotels;
    }
    
}