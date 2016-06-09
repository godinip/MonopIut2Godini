package Ui;

public class Message {
    
    public enum Types {CHANGEMENT_JOUEUR,JEU_TERMINE};
    public Types type;  // type de message
    public String joueur;  // Champ utilisé pour le message CHANGEMENT_JOUEUR
    public String gagnant; // Champ utilisé pour le message JEU_TERMINE
    
}
