package Jeu;

import Data.*;
import java.io.*;
import java.util.*;

public class Monopoly {
    
    private HashMap<Integer,Carreau> carreaux = new HashMap<>();
    private LinkedList<Joueur> joueurs = new LinkedList();
    private HashMap<String,Groupe> groupes = new HashMap<>();
    private int nbMaisons = 32;
    private int nbHotels = 12;
    private LinkedList<Carte> cartesChance = new LinkedList();
    private LinkedList<Carte> cartesCommunaute = new LinkedList();
    
    
    
    public Monopoly() {
        CreerPlateau();
    }
    
    //CREATION PLATEAU
        private void CreerPlateau() {
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
                        if (!groupes.containsKey(data.get(i)[3])) {
                            Groupe nouvGroupe = new Groupe(CouleurPropriete.valueOf(data.get(i)[3]));
                            getGroupes().put(data.get(i)[3], nouvGroupe);
                        }
                        ProprieteAConstruire nouvellePropriete = new ProprieteAConstruire(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[4]),getGroupes().get(data.get(i)[3]),loyer,Integer.parseInt(data.get(i)[11]));
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
            try {
                ArrayList<String[]> data = readDataFile("./src/Data/Chance.txt", ";");
                for (int i=0; i<data.size(); ++i) {
                    String ActionCarteType = data.get(i)[0];
                    if (ActionCarteType.compareTo("SP") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],0,0);
                        cartesChance.add(nouvelleCarte);
                    } else if (ActionCarteType.compareTo("RE") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                        cartesChance.add(nouvelleCarte);
                    } else if (ActionCarteType.compareTo("MH") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),Integer.parseInt(data.get(i)[3]));
                        cartesChance.add(nouvelleCarte);
                    }else if (ActionCarteType.compareTo("GA") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                        cartesChance.add(nouvelleCarte);
                    }else if (ActionCarteType.compareTo("PA") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                        cartesChance.add(nouvelleCarte);
                    }else if (ActionCarteType.compareTo("AV") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                        cartesChance.add(nouvelleCarte);
                    }else if (ActionCarteType.compareTo("AP") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],0,0);
                        cartesChance.add(nouvelleCarte);
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
            try {
                ArrayList<String[]> data = readDataFile("./src/Data/Communaute.txt", ";");
                for (int i=0; i<data.size(); ++i) {
                    String ActionCarteType = data.get(i)[0];
                    if (ActionCarteType.compareTo("SP") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],0,0);
                        cartesCommunaute.add(nouvelleCarte);
                    } else if (ActionCarteType.compareTo("GA") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                        cartesCommunaute.add(nouvelleCarte);
                    }else if (ActionCarteType.compareTo("PA") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                        cartesCommunaute.add(nouvelleCarte);
                    } else if (ActionCarteType.compareTo("AN") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                        cartesCommunaute.add(nouvelleCarte);
                    } else if (ActionCarteType.compareTo("DE") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                        cartesCommunaute.add(nouvelleCarte);
                    } else if (ActionCarteType.compareTo("AP") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],0,0);
                        cartesCommunaute.add(nouvelleCarte);
                    } else if (ActionCarteType.compareTo("AV") == 0) {
                        Carte nouvelleCarte = new Carte(ActionCarteType,data.get(i)[1],Integer.parseInt(data.get(i)[2]),0);
                        cartesCommunaute.add(nouvelleCarte);
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
    
    //GESTION CARREAUX
        public HashMap<Integer,Carreau> getCarreaux() {
        return carreaux;
    }
    
    //GESTION JOUEURS
        public LinkedList<Joueur> getJoueurs() {
            return joueurs;
        }

        public void setJoueurs(LinkedList<Joueur> joueurs) {
            this.joueurs = joueurs;
        }

        public void addJoueur(Joueur joueur){
            getJoueurs().add(joueur);
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
        
        public Joueur joueurSuivant(){
            Joueur j = joueurs.pollFirst();
            joueurs.offerLast(j);
            return j;
        }
    
    //GESTION GROUPES
        public HashMap<String,Groupe> getGroupes() {
        return groupes;
    }
    
    //GESTION MAISONS
        public int getNbMaisons() {
            return nbMaisons;
        }

        public void rendreMaisons(int i){
            nbMaisons += i;
        }

        public void prendreMaison(){
            nbMaisons -= 1;
        }
    
    //GESTION HOTELS
        public int getNbHotels() {
            return nbHotels;
        }

        public void rendreHotels(int i){
            nbHotels += i;
        }

        public void prendreHotel(){
            nbHotels -= 1;
        }
    
    //GESTION CARTES CHANCE
        public Carte getCarteChance() {
            Carte chance = cartesChance.pollFirst();
            return chance;
        }

        public void addCarteChance(Carte chance) {
            cartesChance.offerLast(chance);
        }
            
    //GESTION CARTES COMMUNAUTE
        public Carte getCarteCommunaute() {
            Carte communaute = cartesCommunaute.pollFirst();
            return communaute;
        }

        public void addCarteCommunaute(Carte commmunaute) {
            cartesCommunaute.offerLast(commmunaute);
        }
        
}
