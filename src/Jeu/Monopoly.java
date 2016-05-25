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
	private HashSet<Joueur> joueurs = new HashSet<>();
        
        public Monopoly(){
        
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
                                        
                                        int[] loyer = new int[6];
                                        loyer[0] = Integer.parseInt(data.get(i)[5]); 
                                        loyer[1] = Integer.parseInt(data.get(i)[6]); 
                                        loyer[2] = Integer.parseInt(data.get(i)[7]); 
                                        loyer[3] = Integer.parseInt(data.get(i)[8]); 
                                        loyer[4] = Integer.parseInt(data.get(i)[9]); 
                                        loyer[5] = Integer.parseInt(data.get(i)[10]); 
                                        
                                        if(!groupes.containsValue(data.get(i)[3])){
                                            Groupe nouvGroupe = new Groupe(CouleurPropriete.valueOf(data.get(i)[3]));
                                            getGroupes().put(data.get(i)[3], nouvGroupe);
                                        }
                                       
                                        ProprieteAConstruire nouvellePropriete = new ProprieteAConstruire(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[4]),getGroupes().get(data.get(i)[3]),loyer);
                                        carreaux.put(Integer.parseInt(data.get(i)[1]), nouvellePropriete);
                                }
				else if(caseType.compareTo("G") == 0){
					System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                                        
                                        Gare nouvelleGare = new Gare(Integer.parseInt(data.get(i)[1]),data.get(i)[2],Integer.parseInt(data.get(i)[3]));
                                        carreaux.put(Integer.parseInt(data.get(i)[1]), nouvelleGare);
				}
				else if(caseType.compareTo("C") == 0){
					System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                                        
                                        Compagnie nouvelleCompagnie = new Compagnie(Integer.parseInt(data.get(i)[1]),data.get(i)[2],Integer.parseInt(data.get(i)[3]));
                                        carreaux.put(Integer.parseInt(data.get(i)[1]), nouvelleCompagnie);
				}
				else if(caseType.compareTo("AU") == 0){
					System.out.println("Case Autre :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                                        
                                        AutreCarreau nouvelleCompagnie = new AutreCarreau(data.get(i)[2],Integer.parseInt(data.get(i)[1]),0);
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

    /**
     * @param joueurs the joueurs to set
     */
    public void setJoueurs(HashSet<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
    public void addJoueur(Joueur J){
        this.joueurs.add(J);
    }

    /**
     * @return the groupes
     */
    public HashMap<String,Groupe> getGroupes() {
        return groupes;
    }

    
}