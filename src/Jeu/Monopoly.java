package Jeu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Monopoly {

	private HashMap<Integer,Carreau> carreaux = new HashMap<>();
	private HashSet<Joueur> joueurs;
        
        public Monopoly(){
        
        }
        public void CreationJoueurs(){
            
//            Joueur j1;
//            j1 = new Joueur("Joueur 1", 1500, null, carreaux, proprietes, compagnies);
        }
        public void CreerPlateau(String dataFilename){
		buildGamePlateau(dataFilename);
	}
	
	private void buildGamePlateau(String dataFilename)
	{
            
		try{
			ArrayList<String[]> data = readDataFile(dataFilename, ",");
			for(int i=0; i<data.size(); ++i){
				String caseType = data.get(i)[0];
				if(caseType.compareTo("P") == 0){
					System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                                        
                                        ProprieteAConstruire nouvellePropriete = new ProprieteAConstruire(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[4]));
                                        carreaux.put(Integer.parseInt(data.get(i)[1]), nouvellePropriete);
                                }
				else if(caseType.compareTo("G") == 0){
					System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                                        
                                        Gare nouvelleGare = new Gare(data.get(i)[2],Integer.parseInt(data.get(i)[1]));
                                        carreaux.put(Integer.parseInt(data.get(i)[1]), nouvelleGare);
				}
				else if(caseType.compareTo("C") == 0){
					System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                                        
                                        Compagnie nouvelleCompagnie = new Compagnie(data.get(i)[2],Integer.parseInt(data.get(i)[1]));
                                        carreaux.put(Integer.parseInt(data.get(i)[1]), nouvelleCompagnie);
				}
				else if(caseType.compareTo("AU") == 0){
					System.out.println("Case Autre :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                                        
                                        AutreCarreau nouvelleCompagnie = new AutreCarreau(data.get(i)[2],Integer.parseInt(data.get(i)[1]));
                                        carreaux.put(Integer.parseInt(data.get(i)[1]), nouvelleCompagnie);
				}
				else
					System.err.println("[buildGamePleateau()] : Invalid Data type");
			}
			
		} 
		catch(FileNotFoundException e){
			System.err.println("[buildGamePlateau()] : File is not found!");
		}
		catch(IOException e){
			System.err.println("[buildGamePlateau()] : Error while reading file!");
		}
	}
	
	private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException
	{
		ArrayList<String[]> data = new ArrayList<String[]>();
		
		BufferedReader reader  = new BufferedReader(new FileReader(filename));
		String line = null;
		while((line = reader.readLine()) != null){
			data.add(line.split(token));
		}
		reader.close();
		
		return data;
	}

    /**
     * @return the carreaux
     */
    public HashMap<Integer,Carreau> getCarreaux() {
        return carreaux;
    }

    /**
     * @return the joueurs
     */
    public HashSet<Joueur> getJoueurs() {
        return joueurs;
    }

    
}