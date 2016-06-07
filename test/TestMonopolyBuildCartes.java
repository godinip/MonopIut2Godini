import Jeu.*;
import Data.*;
import java.io.*;
import java.util.*;

public class TestMonopolyBuildCartes {
    
    public static void main(String[] args) throws IOException {
        try {
            ArrayList<Carte> cartesChance = new ArrayList();
            ArrayList<String[]> fichier = new ArrayList();
            BufferedReader bf = new BufferedReader(new FileReader("./src/Data/Chance.txt"));
            String line = null;
            while((line = bf.readLine()) != null){
                fichier.add(line.split("|"));
            }
            System.out.println("Le fichier contient "+fichier.size()+" lignes");
            bf.close();
            for (int i=0; i<fichier.size(); ++i) {
                String ActionCarteType = fichier.get(i)[0];
                System.out.println("Ligne "+i+" de type "+fichier.get(i)[0]);
                if (ActionCarteType.compareTo("SP") == 0) {
                    Carte nouvelleCarte = new Carte(ActionCarteType,fichier.get(i)[1],0,0);
                    cartesChance.add(nouvelleCarte);
                } else if (ActionCarteType.compareTo("RE") == 0) {
                    Carte nouvelleCarte = new Carte(ActionCarteType,fichier.get(i)[1],Integer.parseInt(fichier.get(i)[2]),0);
                    cartesChance.add(nouvelleCarte);
                } else if (ActionCarteType.compareTo("MH") == 0) {
                    Carte nouvelleCarte = new Carte(ActionCarteType,fichier.get(i)[1],Integer.parseInt(fichier.get(i)[2]),Integer.parseInt(fichier.get(i)[3]));
                    cartesChance.add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("GP") == 0) {
                    Carte nouvelleCarte = new Carte(ActionCarteType,fichier.get(i)[1],Integer.parseInt(fichier.get(i)[2]),0);
                    cartesChance.add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("AV") == 0) {
                    Carte nouvelleCarte = new Carte(ActionCarteType,fichier.get(i)[1],Integer.parseInt(fichier.get(i)[2]),0);
                    cartesChance.add(nouvelleCarte);
                }else if (ActionCarteType.compareTo("AP") == 0) {
                    Carte nouvelleCarte = new Carte(ActionCarteType,fichier.get(i)[1],Integer.parseInt(fichier.get(i)[2]),0);
                    cartesChance.add(nouvelleCarte);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        }
        catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
    
}