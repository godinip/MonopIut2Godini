package Ui;

import Jeu.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class PlateauIhm extends JFrame{
    
    private JPanel plateau,information,colorP,carreauSelecte,carreauInfo,bouton;
    private Controleur controleur;
    private ArrayList<JPanel> cases = new ArrayList<>();
    private HashMap<Integer,Carreau> Carreaux;
    private JLabel nom,prix,loyer;
    private JLabel gareLabel,departLabel,pfreeLabel,prisonLabel,gardienLabel,cCoLabel,chanceLabel,cElecLabel,cEauLabel;
    private int h;
    private BufferedImage gare,depart,pfree,prison,gardien,cCo,chance,cElec,cEau;
    private JButton lDe,passerT,achatM,achatH;
    public PlateauIhm(Controleur c) throws IOException{
        super("Monopoly");
        controleur = c;
        generationImage();
         initUIComponents();
    }
    
    public void initUIComponents() throws IOException {
       carreauSelecte = new JPanel(new BorderLayout());
       carreauInfo = new JPanel(new BorderLayout());
       bouton = new JPanel();
       bouton.setLayout(new BoxLayout(bouton, BoxLayout.PAGE_AXIS));
       lDe = new JButton("Lancer les dès");
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
       
       plateau = new JPanel();
       plateau();                                                                               //Generation plateau
       information = new JPanel(new BorderLayout());
       information.setBackground(Color.YELLOW);
       information.add(Box.createRigidArea(new Dimension(285, 0)),BorderLayout.SOUTH);
       information.add(carreauSelecte,BorderLayout.NORTH);
       information.add(bouton,BorderLayout.CENTER);
       carreauSelecte.setBackground(Color.white);
       carreauSelecte.setBorder(BorderFactory.createLineBorder(Color.black));
       carreauSelecte.add(colorP = new JPanel(),BorderLayout.NORTH);
       carreauSelecte.add(carreauInfo,BorderLayout.CENTER);
       
       bouton.add(lDe);
       bouton.add(passerT);
       bouton.add(achatM);
       bouton.add(achatH);
//       
       colorP.add(Box.createRigidArea(new Dimension(0, 35)));
       
       carreauInfo.add(nom = new JLabel(),BorderLayout.NORTH); // Information carreau survolé 
       carreauInfo.add(prix = new JLabel(),BorderLayout.CENTER);
       carreauInfo.setBackground(Color.white);
       
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
//                                                                                                                                          Gare Partie 1
            if (controleur.getCarreau(h)instanceof Gare) {
               Gare g = (Gare) controleur.getCarreau(h);
                 carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(Color.white);
                        colorP.add(gareLabel,BorderLayout.CENTER);
                        nom.setText(g.getNom());
                        prix.setText(g.getPrix()+"");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {
                        colorP.remove(gareLabel);
                    }
                });
            }
//                                                                                                                                          Propriete a Construire Partie 1           
            if (controleur.getCarreau(h) instanceof ProprieteAConstruire) {
                ProprieteAConstruire p = (ProprieteAConstruire)controleur.getCarreau(h);
                
                JPanel panelCouleur = new JPanel();
                if(i>10){
                    carreau.add(panelCouleur,BorderLayout.WEST);
                }else{
                    carreau.add(panelCouleur,BorderLayout.SOUTH);
                }     
                carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(colorCarreau(p.getCouleur().getCouleur().toString())); 
                        nom.setText(p.getNom());
                        prix.setText(p.getPrix()+" €");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {}
                });
                panelCouleur.setBackground(colorCarreau(p.getCouleur().getCouleur().toString())); 
            }
//                                                                                                                                          Compagnie Partie 1
            if (controleur.getCarreau(h)instanceof Compagnie) {
                Compagnie co = (Compagnie) controleur.getCarreau(h);
                  carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(Color.white);
                        colorP.add(cEauLabel,BorderLayout.CENTER);
                        nom.setText(co.getNom());
                        prix.setText(co.getPrix()+" €");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {colorP.remove(cEauLabel);}
                });
            }
//                                                                                                                                          Autre Carreau Partie 1
              if (controleur.getCarreau(h) instanceof AutreCarreau) {
                AutreCarreau a = (AutreCarreau) controleur.getCarreau(h);
               carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(Color.WHITE); 
                        nom.setText(a.getNom());
                        if(h==1){
                            prix.setText("+"+a.getMontant()+" €");
                        }else{
                            if (a.getMontant() == 0) {
                                prix.setText(" ");
                            }else{                                
                                prix.setText(a.getMontant()+" €");
                            }
                        }
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {}
                });
            }
//                                                                                                                                              Chance Partie 1
              if (controleur.getCarreau(h) instanceof Chance) {
                Chance ch = (Chance) controleur.getCarreau(h);
               carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(Color.white);
                        colorP.add(chanceLabel,BorderLayout.CENTER);
                        nom.setText(ch.getNom());
                        prix.setText(" ");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {
                    colorP.remove(chanceLabel);
                    }
                });

            }
              //                                                                                                                                           Communaute Partie 1
              if (controleur.getCarreau(h) instanceof Communaute) {
                Communaute com = (Communaute) controleur.getCarreau(h);
                carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(Color.white);
                        colorP.add(cCoLabel,BorderLayout.CENTER);    
                        colorP.setBackground(Color.WHITE); 
                        nom.setText(com.getNom());
                        prix.setText(" ");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {
                    colorP.remove(cCoLabel);
                    }
                });
            }
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
//                                                                                                                                          Prporiete a Construire  Partie 2            
            if (controleur.getCarreau(h) instanceof ProprieteAConstruire) {
                ProprieteAConstruire p = (ProprieteAConstruire)controleur.getCarreau(h);
                JPanel panelCouleur = new JPanel();
                if(i>10){
                    carreau.add(panelCouleur,BorderLayout.NORTH);
                }else{
                    carreau.add(panelCouleur,BorderLayout.EAST);
                }
                carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(colorCarreau(p.getCouleur().getCouleur().toString())); 
                        nom.setText(p.getNom());
                        prix.setText(p.getPrix()+" €");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {}
                });
                panelCouleur.setBackground(colorCarreau(p.getCouleur().getCouleur().toString()));
            }
//                                                                                                                                          Gare Partie 2
            if (controleur.getCarreau(h)instanceof Gare) {
                Gare g = (Gare) controleur.getCarreau(h);
                
                 carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(Color.white);
                        colorP.add(gareLabel,BorderLayout.CENTER);
                        nom.setText(g.getNom());
                        prix.setText(g.getPrix()+" €");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {
                        colorP.remove(gareLabel);
                    }
                });
            }
//                                                                                                                                          Compagnie Partie 2
            if (controleur.getCarreau(h)instanceof Compagnie) {
                Compagnie co = (Compagnie) controleur.getCarreau(h);
                carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(Color.white);
                        colorP.add(cElecLabel,BorderLayout.CENTER);  
                        colorP.setBackground(Color.WHITE); 
                        nom.setText(co.getNom());
                        prix.setText(co.getPrix()+" €");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {colorP.remove(cElecLabel);}
                });
           }
//                                                                                                                                           Autre Carreau Partie 2
              if (controleur.getCarreau(h) instanceof AutreCarreau) {
                AutreCarreau a = (AutreCarreau) controleur.getCarreau(h);
                carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(Color.WHITE); 
                        nom.setText(a.getNom());
                        if(h==1){
                            prix.setText("+"+a.getMontant()+" €");
                        }else{
                            if (a.getMontant() == 0) {
                                prix.setText(" ");
                            }else{                                
                                prix.setText(a.getMontant()+" €");
                            }
                        }
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {}
                });
            }
              //                                                                                                                                           Chance Partie 2
              if (controleur.getCarreau(h) instanceof Chance) {
                Chance ch = (Chance) controleur.getCarreau(h);
                carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(Color.white);
                        colorP.add(chanceLabel,BorderLayout.CENTER);  
                        nom.setText(ch.getNom());
                        prix.setText(" ");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {colorP.remove(chanceLabel);}
                });
            }
              //                                                                                                                                           Communaute Partie 2
              if (controleur.getCarreau(h) instanceof Communaute) {
                Communaute com = (Communaute) controleur.getCarreau(h);
                carreau.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        colorP.setBackground(Color.white);
                        colorP.add(cCoLabel,BorderLayout.CENTER);  
                        colorP.setBackground(Color.WHITE); 
                        nom.setText(com.getNom());
                        prix.setText(" ");
                    }@Override
                    public void mouseClicked(MouseEvent e) {}@Override
                    public void mousePressed(MouseEvent e) {}@Override
                    public void mouseReleased(MouseEvent e) {}@Override
                    public void mouseExited(MouseEvent e) {colorP.remove(cCoLabel);}
                });
            }
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

    private void generationImage() throws IOException {
        gare = ImageIO.read(new File("./src/Image/Train.png"));
        depart = ImageIO.read(new File("./src/Image/FlecheD.png"));
        pfree = ImageIO.read(new File("./src/Image/pfree.png"));
        prison = ImageIO.read(new File("./src/Image/prison.png"));
        gardien = ImageIO.read(new File("./src/Image/garde.png"));
        cCo = ImageIO.read(new File("./src/Image/CaisseCommunaute.png"));
        chance = ImageIO.read(new File("./src/Image/Chance.png"));
        cElec = ImageIO.read(new File("./src/Image/Compagnie eau.png"));
        cEau = ImageIO.read(new File("./src/Image/Compagnie elec.png"));
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
    }
