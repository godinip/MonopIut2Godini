package Ui;

import Jeu.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class PlateauIhm extends JFrame implements Observateur{
    
    private JPanel plateau,information,colorP,carreauSelecte,carreauInfo,bouton,infoJoueur,autreInfo;
    private Controleur controleur;
    private ArrayList<JPanel> cases = new ArrayList<>();
    private HashMap<Integer,Carreau> Carreaux;
    private JLabel nom,prix,nbMaison,prixM,prixL1,prixL2,prixL3,prixL4,prixL5,prixL6;
    private JLabel gareLabel,departLabel,pfreeLabel,prisonLabel,gardienLabel,cCoLabel,chanceLabel,cElecLabel,cEauLabel;
    private JLabel nomJ,argentJ,posJT,posJ;
    private int h;
    private BufferedImage gare,depart,pfree,prison,gardien,cCo,chance,cElec,cEau;
    private JButton lDe,passerT,achatM,achatH,abandon,exit;
    
    public PlateauIhm(Controleur c) throws IOException{
        super("Monopoly");
        controleur = c;
        controleur.setObservateur(this);
        generationImage();
         initUIComponents();
         resetLabel();
    }
    
    public void initUIComponents() throws IOException {

       lDe = new JButton("Lancer les dés");
       lDe.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controleur.jouerUnCoup();
                }
            }
        );
       lDe.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.SIZE));
       
       passerT = new JButton("Fin du tour");
       passerT.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controleur.tour();
                }
            }
        );
       passerT.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.SIZE));
       achatM = new JButton("Acheter Maison");
       achatM.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.SIZE));
       achatH = new JButton("Acheter Hotel");
       achatH.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.SIZE));
       abandon = new JButton("Abandonner");
       abandon.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.SIZE));
       
       exit = new JButton("Quitter");
       exit.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              System.exit(0);
           }
       });
       exit.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.SIZE));
       

       bouton = new JPanel();
       bouton.setLayout(new BoxLayout(bouton, BoxLayout.PAGE_AXIS));
       bouton.add(lDe);
       bouton.add(passerT);
       bouton.add(achatM);
       bouton.add(achatH);
       bouton.add(abandon);
       bouton.add(exit);
       
       colorP = new JPanel();
       colorP.add(Box.createRigidArea(new Dimension(0, 35)));
       
       carreauInfo = new JPanel();
       carreauInfo.setLayout(new BoxLayout(carreauInfo, BoxLayout.PAGE_AXIS));
       carreauInfo.add(nom = new JLabel()); // Information carreau survolé 
       carreauInfo.add(prix = new JLabel());
       carreauInfo.add(nbMaison = new JLabel());
       carreauInfo.add(prixM = new JLabel());
       carreauInfo.add(prixL1 = new JLabel());
       carreauInfo.add(prixL2 = new JLabel());
       carreauInfo.add(prixL3 = new JLabel());
       carreauInfo.add(prixL4 = new JLabel());
       carreauInfo.add(prixL5 = new JLabel());
       carreauInfo.add(prixL6 = new JLabel());
       carreauInfo.setBackground(Color.white);
       
       carreauSelecte = new JPanel(new BorderLayout());
       carreauSelecte.setBackground(Color.white);
       carreauSelecte.setBorder(BorderFactory.createLineBorder(Color.black));
       carreauSelecte.add(colorP,BorderLayout.NORTH);
       carreauSelecte.add(carreauInfo,BorderLayout.CENTER);
       
       
       infoJoueur = new JPanel();
        infoJoueur.setBorder(BorderFactory.createTitledBorder("Information joueur"));
        infoJoueur.setBackground(Color.WHITE);
        infoJoueur.setLayout(new BoxLayout(infoJoueur, BoxLayout.PAGE_AXIS));
        infoJoueur.add(new JLabel(" "));
        infoJoueur.add(nomJ = new JLabel("Joueur "));
        infoJoueur.add(new JLabel(" "));
        infoJoueur.add(posJT =new JLabel("Position courante: "));
        infoJoueur.add(posJ = new JLabel(" "));
        infoJoueur.add(new JLabel(" "));
        infoJoueur.add(argentJ = new JLabel("Argent: "));
       
       carreauInfo.setBackground(Color.white);
       
       autreInfo = new JPanel(new  BorderLayout());
       autreInfo.add(infoJoueur,BorderLayout.CENTER);
       autreInfo.add(bouton,BorderLayout.NORTH);
       
       information = new JPanel(new BorderLayout());
       information.setBackground(Color.YELLOW);
       information.add(Box.createRigidArea(new Dimension(300, 0)),BorderLayout.SOUTH);
       information.add(carreauSelecte,BorderLayout.NORTH);
       information.add(autreInfo,BorderLayout.CENTER);
       
       plateau = new JPanel();
       plateau();    
       add(plateau,BorderLayout.CENTER);
       add(information,BorderLayout.EAST);
    }
    
    public void affiche(){
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(1250,900);
        setVisible(true);
    }
    
    public void plateau() throws IOException{
        plateau.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.ipady = gc.anchor = GridBagConstraints.CENTER;
        gc.weightx =11;
        gc.weighty = 11;
         h = 1;

            for (int i = 0; i < 20; i++) {
                if(i<=10){
                    gc.gridx = i;
                    gc.gridy = 0;
                }else{
                    gc.gridy = i-10;
                    gc.gridx = 10;
                }
                  JPanel carreau = new  JPanel(new BorderLayout());
                        if((i==0)||(i==10)){
                                gc.ipadx = 40;
                                gc.ipady = 40;  
                        }else{
                                gc.ipadx = 0;
                                gc.ipady = 0;
                        }
                        generationCarreau(carreau, i,1);
                        carreau.setBackground(Color.white);
                        carreau.setBorder(BorderFactory.createLineBorder(Color.black));
                        plateau.add(carreau,gc);
                        cases.add(carreau);
                        h++;
            }  
//                                                                                                                                                             Partie 2
            for (int i = 20; i > 0; i--) {
                if (i>=10) {
                    gc.gridx = i-10;
                    gc.gridy = 10;              
                }else{
                    gc.gridy = i;
                    gc.gridx = 0;                  
                }
            JPanel carreau = new  JPanel(new BorderLayout());
            if((i==10)){
                gc.ipadx = 40;
                gc.ipady = 40;
            }else{
                gc.ipadx = 0;
                gc.ipady = 0;
            }
                generationCarreau(carreau, i,2);
            carreau.setBackground(Color.white);
            carreau.setBorder(BorderFactory.createLineBorder(Color.black));
            plateau.add(carreau,gc);
            cases.add(carreau);
            h++;
            }  
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 9;
        gc.gridheight = 9;
        JPanel test = new  JPanel();
        test.setBorder(BorderFactory.createLineBorder(Color.black));
        plateau.add(test,gc);
    }
    private void generationCarreau(JPanel carreau,int i,int j){
        //                                                                                                                                          Gare Partie 
            if (controleur.getCarreau(h)instanceof Gare) {
                Gare g = (Gare) controleur.getCarreau(h);
                 carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        resetLabel();
                        colorP.setBackground(Color.white);
                        colorP.add(gareLabel,BorderLayout.CENTER);
                        nom.setText(g.getNom());
                        prix.setText(g.getPrix()+" €");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {}
                });
            }
//                                                                                                                                          Propriete a Construire Partie       
            if (controleur.getCarreau(h) instanceof ProprieteAConstruire) {
                ProprieteAConstruire p = (ProprieteAConstruire)controleur.getCarreau(h);
                JPanel panelCouleur = new JPanel();
                if(i>10){
                    if(j == 1){
                        carreau.add(panelCouleur,BorderLayout.WEST);
                    }else{
                        carreau.add(panelCouleur,BorderLayout.NORTH);
                    }
                    
                }else{
                    if(j == 1){
                        carreau.add(panelCouleur,BorderLayout.SOUTH);
                    }else{
                         carreau.add(panelCouleur,BorderLayout.EAST);
                    }
                   
                }     
                carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        resetLabel();
                        colorP.setBackground(colorCarreau(p.getCouleur().getCouleur().toString())); 
                        nom.setText(p.getNom());
                        prix.setText(p.getPrix()+" €");
                        nbMaison.setText("Nombre Maison: "+p.getMaisons());
                        prixM.setText("Prix d'une Maison: "+p.getCoutmaison());
                        prixL1.setText("Loyer 1: "+p.getLoyers(0));
                        prixL2.setText("Loyer 2: "+p.getLoyers(1));
                        prixL3.setText("Loyer 3: "+p.getLoyers(2));
                        prixL4.setText("Loyer 4: "+p.getLoyers(3));
                        prixL5.setText("Loyer 5: "+p.getLoyers(4));
                        prixL6.setText("Loyer 6: "+p.getLoyers(5));
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {}
                });
                panelCouleur.setBackground(colorCarreau(p.getCouleur().getCouleur().toString())); 
            }
//                                                                                                                                          Compagnie Partie 
            if (controleur.getCarreau(h)instanceof Compagnie) {
                    Compagnie co = (Compagnie) controleur.getCarreau(h);
                    carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        resetLabel();
                        colorP.setBackground(Color.white);
                        if (co.getNumero() == 13) {
                            colorP.add(cEauLabel,BorderLayout.CENTER);
                        }else{
                            colorP.add(cElecLabel,BorderLayout.CENTER);
                        }
                        
                        nom.setText(co.getNom());
                        prix.setText(co.getPrix()+" €");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {}
                });
            }
//                                                                                                                                          Autre Carreau Partie 
              if (controleur.getCarreau(h) instanceof AutreCarreau) {
                    AutreCarreau a = (AutreCarreau) controleur.getCarreau(h);
                    carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        resetLabel();
                        nom.setText(a.getNom());
                        if(a.getNumero()==1){
                            colorP.add(departLabel);
                            prix.setText("+"+a.getMontant()+" €");
                        }else if(a.getNumero()== 11){
                            colorP.add(prisonLabel);
                        }else if(a.getNumero()== 21){
                            colorP.add(pfreeLabel);
                        }else if(a.getNumero()== 31){
                            colorP.add(gardienLabel);
                        }
                        colorP.setBackground(Color.WHITE); 
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {}
                });
            }
//                                                                                                                                              Chance Partie 
              if (controleur.getCarreau(h) instanceof Chance) {
                    Chance ch = (Chance) controleur.getCarreau(h);
                    carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        resetLabel();
                        colorP.setBackground(Color.white);
                        colorP.add(chanceLabel,BorderLayout.CENTER);
                        nom.setText(ch.getNom());
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {}
                });

            }
              //                                                                                                                                           Communaute Partie 1
              if (controleur.getCarreau(h) instanceof Communaute) {
                    Communaute com = (Communaute) controleur.getCarreau(h);
                    carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        resetLabel();
                        colorP.add(cCoLabel,BorderLayout.CENTER);    
                        colorP.setBackground(Color.WHITE); 
                        nom.setText(com.getNom());
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {}
                });
            }
    }
    
    private Color colorCarreau(String s){
        Color c = null;
        switch(s){
            case "rouge": c = Color.RED;
                break;
            case "jaune":c = Color.YELLOW;
                break;
            case "vert": c= Color.GREEN;
                break;
            case "bleuFonce":c = Color.BLUE;
                break;
            case "mauve":c =Color.pink;
                break;
            case "bleuCiel":c = Color.CYAN;
                break;
            case "orange":c =Color.ORANGE;
                break;
            case "violet":c =Color.magenta;
                break;
        }
        return c;
    }
    private void resetLabel(){
        nom.setText(" ");
        prix.setText(" ");
        nbMaison.setText(" ");
        prixM.setText(" ");
        prixL1.setText(" ");
        prixL2.setText(" ");
        prixL3.setText(" ");
        prixL4.setText(" ");
        prixL5.setText(" ");
        prixL6.setText(" ");
        resetImg();
    }
    private void resetImg(){
        colorP.removeAll();
        colorP.repaint();
        colorP.add(Box.createRigidArea(new Dimension(0, 35)));
       
    }
    private void generationImage() throws IOException {
        gare = ImageIO.read(new File("./src/Image/Train.png"));
        depart = ImageIO.read(new File("./src/Image/FlecheD.png"));
        pfree = ImageIO.read(new File("./src/Image/pfree.png"));
        prison = ImageIO.read(new File("./src/Image/prison.png"));
        gardien = ImageIO.read(new File("./src/Image/garde.png"));
        cCo = ImageIO.read(new File("./src/Image/CaisseCommunaute.png"));
        chance = ImageIO.read(new File("./src/Image/Chance.png"));
        cEau = ImageIO.read(new File("./src/Image/Compagnie eau.png"));
        cElec = ImageIO.read(new File("./src/Image/Compagnie elec.png"));
        gareLabel = new JLabel(new ImageIcon(gare));
        departLabel = new JLabel(new ImageIcon(depart));
        pfreeLabel = new JLabel(new ImageIcon(pfree));
        prisonLabel = new JLabel(new ImageIcon(prison));
        gardienLabel = new JLabel(new ImageIcon(gardien));
        cCoLabel = new JLabel(new ImageIcon(cCo));
        chanceLabel = new JLabel(new ImageIcon(chance));
        cElecLabel = new JLabel(new ImageIcon(cElec));
        cEauLabel = new JLabel(new ImageIcon(cEau));
    }
    
    @Override
    public void notifier(Message message) {
        switch(message.type) {
            case ELANCER:
                lDe.setEnabled(false);
                break;
            case ALANCER:
                lDe.setEnabled(true);
                break;
            case EPASSER:
                passerT.setEnabled(false);
                break;
            case APASSER:
                passerT.setEnabled(true);
                break;
            case EACHATM:
                achatM.setEnabled(false);
                break;
            case AACHATM:
                achatM.setEnabled(true);
                break;
            case EACHATH:
                achatH.setEnabled(false);
                break;
            case AACHATH:
                achatH.setEnabled(true);
                break;
            case JOUEUR:
                infoJoueur();
                break;
        }
    }

    private void infoJoueur() {
        infoJoueur.removeAll();
        infoJoueur.repaint();
        infoJoueur.setBorder(BorderFactory.createTitledBorder("Information joueur"));
        infoJoueur.setBackground(Color.WHITE);
        infoJoueur.setLayout(new BoxLayout(infoJoueur, BoxLayout.PAGE_AXIS));
         infoJoueur.add(new JLabel(" "));
        infoJoueur.add(nomJ = new JLabel("Joueur: "));
        infoJoueur.add(new JLabel(" "));
        infoJoueur.add(posJT =new JLabel("Position courante: "));
        infoJoueur.add(posJ = new JLabel(" "));
        infoJoueur.add(new JLabel(" "));
        infoJoueur.add(argentJ = new JLabel("Argent: "));
        
        Joueur j = controleur.getJoueur();
        
        nomJ.setText("Joueur: "+j.getNomJoueur());
        posJ.setText("- "+j.getPositionCourante().getNom());
        argentJ .setText("Argent: "+j.getArgent()+" €");
        
        //affichage Propo Joueur
        JPanel propoJ = new JPanel();
        propoJ.setLayout(new BoxLayout(propoJ,BoxLayout.PAGE_AXIS));
        infoJoueur.add(propoJ);
        propoJ.add(new JLabel(" "));
        propoJ.add(new JLabel("Propriete(s): "));
        if(!j.getProprietes().isEmpty()){
        
            for (ProprieteAConstruire prop : j.getProprietes()) {
                propoJ.add(new JLabel(" - "+prop.getNom()));
            }

        }else{  
            propoJ.add(new JLabel(" - Pas de Propriete A Construire"));       
        }
        // affichage Gare Joueur
        JPanel gareJ = new JPanel();
        gareJ.setLayout(new BoxLayout(gareJ,BoxLayout.PAGE_AXIS));
        infoJoueur.add(gareJ);
        gareJ.add(new JLabel(" "));
        gareJ.add(new JLabel("Gare(s)"));
        if(!j.getGares().isEmpty()){
            for (Gare gares : j.getGares()) {
                gareJ.add(new JLabel(" - "+gares.getNom()));
            }
        }else{
            gareJ.add(new JLabel(" - Pas de Gare "));       
        }

    }
        
}
