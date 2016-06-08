package Jeu;

import Data.*;
import java.io.*;
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
                        Chance nouvelleChance =  new Chance(data.get(i)[2],Integer.parseInt(data.get(i)[1]));
                        getCarreaux().put(nouvelleChance.getNumero(), nouvelleChance);
                    } else if (data.get(i)[2].compareTo("Caisse de Communauté") == 0) {
                        Communaute nouvelleCommunaute = new Communaute(data.get(i)[2],Integer.parseInt(data.get(i)[1]));
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
        System.out.println("buildCartesChance");
        try {
            ArrayList<String[]> data = readDataFile("./src/Data/Chance.txt", ";");
            for (int i=0; i<data.size(); ++i) {
                String ActionCarteType = data.get(i)[0];
                if (ActionCarteType.compareTo("SP") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],0,0);
                    cartesChance.add(nouvelleCarte);
                } else if (ActionCarteType.compareTo("RE") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]+","+data.get(i)[2]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                    cartesChance.add(nouvelleCarte);
                } else if (ActionCarteType.compareTo("MH") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]+","+data.get(i)[2]+","+data.get(i)[3]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),Integer.parseInt(data.get(i)[3]));
                    cartesChance.add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("GP") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]+","+data.get(i)[2]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                    cartesChance.add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("AV") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]+","+data.get(i)[2]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                    cartesChance.add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("AP") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],0,0);
                    cartesChance.add(nouvelleCarte);
                } else {
                    System.err.println("caseType not found at "+(i+1)+" avec ActionCarteType = "+ActionCarteType);
                }
            }
            Collections.shuffle(cartesChance);
        }
        catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        }
        catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
    
    private void buildCartesCommunaute() {
        System.out.println("buildCartesCommunaute");
        try {
            ArrayList<String[]> data = readDataFile("./src/Data/Communaute.txt", ";");
            for (int i=1; i<data.size(); ++i) {
                String ActionCarteType = data.get(i)[0];
                if (ActionCarteType.compareTo("SP") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],0,0);
                    cartesChance.add(nouvelleCarte);
                } else if (ActionCarteType.compareTo("GP") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]+","+data.get(i)[2]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                    cartesChance.add(nouvelleCarte);
                } else if (ActionCarteType.compareTo("AN") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]+","+data.get(i)[2]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                    cartesChance.add(nouvelleCarte);
                } else if (ActionCarteType.compareTo("DE") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]+","+data.get(i)[2]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                    cartesChance.add(nouvelleCarte);
                } else if (ActionCarteType.compareTo("AP") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]+","+data.get(i)[2]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],0,0);
                    cartesChance.add(nouvelleCarte);
                } else if (ActionCarteType.compareTo("AV") == 0) {
                    System.out.println(data.get(i)[0]+","+data.get(i)[1]);/**/
                    Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[1]),0);
                    cartesChance.add(nouvelleCarte);
                } else {
                    System.err.println("caseType not found at "+(i+1));
                }
            }
            Collections.shuffle(cartesCommunaute);
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
    
    public int getNbHotels() {
        return nbHotels;
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
    
    public void rendreMaisons(int i){
        nbMaisons += i;
    }
    
    public void prendreMaison(){
        nbMaisons -= 1;
    }
    
    public void rendreHotels(int i){
        nbHotels += i;
    }
    
    public void prendreHotel(){
        nbHotels -= 1;
    }
    
    public void suppJoueur(Joueur j){
        int m = 0;
        int h = 0;
        for(Compagnie c : j.getCompagnies()){
            c.clean();
        }
        for (Gare g : j.getGares()){
            g.clean();
        }
        for (ProprieteAConstruire p : j.getProprietes()){
            int a = p.getMaisons();
            if (a < 5){
                m += a;
            } else {
                h += 1;
            }
            p.clean();
        }
        rendreMaisons(m);
        rendreHotels(h);
        
        joueurs.remove(j);
        
    }
    
}
