package Jeu;

import Data.ActionsCarte;
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
    private ArrayList<Carte> cartesChance = new ArrayList();
    private ArrayList<Carte> cartesCommunaute = new ArrayList();
    
    
    public Monopoly() {
        
    }
    
    public void CreerPlateau() {
        buildGamePlateau();
        buildCartesChance();
        buildCartesCommunaute();
    }
    
    private void buildGamePlateau() {
        try {
            ArrayList<String[]> data = readDataFile("./src/Data/data.txt", ",");
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
                    getCarreaux().put(nouvellePropriete.getNumero(), nouvellePropriete);
                }
                else if (caseType.compareTo("G") == 0) {
                    Gare nouvelleGare = new Gare(Integer.parseInt(data.get(i)[1]),data.get(i)[2],Integer.parseInt(data.get(i)[3]));
                    getCarreaux().put(nouvelleGare.getNumero(), nouvelleGare);
                }
                else if (caseType.compareTo("C") == 0) {
                    Compagnie nouvelleCompagnie = new Compagnie(Integer.parseInt(data.get(i)[1]),data.get(i)[2],Integer.parseInt(data.get(i)[3]));
                    getCarreaux().put(nouvelleCompagnie.getNumero(), nouvelleCompagnie);
                }
                else if (caseType.compareTo("AU") == 0) {
                    if (data.get(i)[2].compareTo("Chance") == 0) {
                        Chance nouvelleChance =  new Chance(data.get(i)[2],Integer.parseInt(data.get(i)[1]),Integer.parseInt(data.get(i)[3]));
                        getCarreaux().put(nouvelleChance.getNumero(), nouvelleChance);
                    } else if (data.get(i)[2].compareTo("Caisse de Communauté") == 0) {
                        Communaute nouvelleCommunaute = new Communaute(data.get(i)[2],Integer.parseInt(data.get(i)[1]),Integer.parseInt(data.get(i)[3]));
                        getCarreaux().put(nouvelleCommunaute.getNumero(), nouvelleCommunaute);
                    } else if (data.get(i)[2].compareTo("Allez en prison") == 0) {
                        AutreCarreau nouveauCarreau = new AutreCarreau(data.get(i)[2],Integer.parseInt(data.get(i)[1]),Integer.parseInt(data.get(i)[3]));
                        getCarreaux().put(nouveauCarreau.getNumero(), nouveauCarreau);
                    } else {
                        AutreCarreau nouveauCarreau = new AutreCarreau(data.get(i)[2],Integer.parseInt(data.get(i)[1]),Integer.parseInt(data.get(i)[3]));
                        getCarreaux().put(nouveauCarreau.getNumero(), nouveauCarreau);
                    }
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
    
    private void buildCartesChance() {
        try {
            ArrayList<String[]> data = readDataFile("./src/Data/Chance.txt", "|");
            for (int i=0; i<data.size(); ++i) {
                String ActionCarteType = data.get(i)[0];
                if (ActionCarteType.compareTo("SP") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.SP,data.get(i)[0],0,0);
                    getCartesChance().add(nouvelleCarte);
                } else if (ActionCarteType.compareTo("RE") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.RE,data.get(i)[0],Integer.parseInt(data.get(i)[2]),0);
                    getCartesChance().add(nouvelleCarte);
                } else if (ActionCarteType.compareTo("MH") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.MH,data.get(i)[0],Integer.parseInt(data.get(i)[2]),Integer.parseInt(data.get(i)[3]));
                    getCartesChance().add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("GP") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.GP,data.get(i)[0],Integer.parseInt(data.get(i)[2]),0);
                    getCartesChance().add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("AV") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.AV,data.get(i)[0],Integer.parseInt(data.get(i)[2]),0);
                    getCartesChance().add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("AP") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.AP,data.get(i)[0],Integer.parseInt(data.get(i)[2]),0);
                    getCartesChance().add(nouvelleCarte);
                }else {
                    System.err.println("[buildGamePlateau()] : unknown action at "+i);
                }
            }
            Collections.shuffle(getCartesChance());
        }
        catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        }
        catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
    
    private void buildCartesCommunaute() {
        try {
            ArrayList<String[]> data = readDataFile("./src/Data/Communaute.txt", "|");
            for (int i=0; i<data.size(); ++i) {
                String ActionCarteType = data.get(i)[0];
                if (ActionCarteType.compareTo("SP") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.SP,data.get(i)[0],0,0);
                    getCartesChance().add(nouvelleCarte);
                }
                else if (ActionCarteType.compareTo("GP") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.GP,data.get(i)[0],Integer.parseInt(data.get(i)[2]),0);
                    getCartesChance().add(nouvelleCarte);
                }
                else if (ActionCarteType.compareTo("AN") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.AN,data.get(i)[0],Integer.parseInt(data.get(i)[2]),0);
                    getCartesChance().add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("DE") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.DE,data.get(i)[0],Integer.parseInt(data.get(i)[2]),0);
                    getCartesChance().add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("AP") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.AP,data.get(i)[0],Integer.parseInt(data.get(i)[2]),0);
                    getCartesChance().add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("AV") == 0) {
                    Carte nouvelleCarte = new Carte(ActionsCarte.AV,data.get(i)[0],Integer.parseInt(data.get(i)[2]),0);
                    getCartesChance().add(nouvelleCarte);
                }else {
                    System.err.println("[buildGamePlateau()] : unknown action at "+i);
                }
            }
            Collections.shuffle(getCartesCommunaute());
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
        this.getJoueurs().add(j);
    }
    
    public HashMap<String,Groupe> getGroupes() {
        return groupes;
    }
    
    public int getNbMaisons() {
        return nbMaisons;
    }
    
    public void setNbMaisons(int nbMaisons) {
        this.nbMaisons = nbMaisons;
    }
    
    public int getNbHotels() {
        return nbHotels;
    }
    
    public void setNbHotels(int nbHotels) {
        this.nbHotels = nbHotels;
    }
    
    public Carte getCarteChance() {
        Carte chance = cartesChance.get(0);
        cartesChance.remove(0);
        return chance;
    }
    
    public void addCarteChance(Carte chance) {
        cartesChance.add(chance);
    }
    
    public Carte getCarteCommunaute() {
        Carte communaute = cartesCommunaute.get(0);
        cartesChance.remove(0);
        return communaute;
    }
    
    public void addCarteCommunaute(Carte commmunaute) {
        cartesCommunaute.add(commmunaute);
    }
    
}