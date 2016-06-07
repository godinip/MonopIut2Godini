/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author godinip
 */
public class Plateau extends JPanel{
    private ArrayList<JPanel> Carreaus = new ArrayList<>();
    public Plateau(){
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.ipady = gc.anchor = GridBagConstraints.CENTER;
        gc.weightx =11;
        gc.weighty = 11;
        int h = 1;
            for (int i = 0; i < 11; i++) {
                gc.gridx = i;
                 gc.gridy = 0;
                 JPanel carreau = new  JPanel();

                        if((i==0)||(i==10)){
                            gc.ipadx = 40;
                            gc.ipady = 40;
                            carreau.add(new JLabel(h+""));
                        }else{
                            carreau.add(new JLabel(h+""));
                            gc.ipadx = 0;
                            gc.ipady = 0;
                        }
                        carreau.setBackground(Color.white);
                        carreau.setBorder(BorderFactory.createLineBorder(Color.black));
                        add(carreau,gc);
                        Carreaus.add(carreau);
                        h++;

            }
            for (int i = 1; i < 10; i++) {
                gc.gridy = i;
                 gc.gridx = 10;
                 JPanel carreau = new  JPanel();

                        if((i==0)||(i==10)){
                            gc.ipadx = 40;
                            gc.ipady = 40;
                            carreau.add(new JLabel(h+""));
                        }else{
                            carreau.add(new JLabel(h+""));
                            gc.ipadx = 0;
                            gc.ipady = 0;
                        }
                        carreau.setBackground(Color.white);
                        carreau.setBorder(BorderFactory.createLineBorder(Color.black));
                        add(carreau,gc);
                        Carreaus.add(carreau);
                        h++;
            }
            
            for (int i = 10; i > 0; i--) {
             gc.gridx = i;
            gc.gridy = 10;
            JPanel carreau = new  JPanel();

            if((i==0)||(i==10)){
                gc.ipadx = 40;
                gc.ipady = 40;
                carreau.add(new JLabel(h+""));
            }else{
                carreau.add(new JLabel(h+""));
                gc.ipadx = 0;
                gc.ipady = 0;
            }
            carreau.setBackground(Color.white);
            carreau.setBorder(BorderFactory.createLineBorder(Color.black));
            add(carreau,gc);
            Carreaus.add(carreau);
            h++;
            }
            
            for (int i = 10; i > 0; i--) {
                gc.gridy = i;
                gc.gridx = 0;
                 JPanel carreau = new  JPanel();

                        if((i==0)||(i==10)){
                            gc.ipadx = 40;
                            gc.ipady = 40;
                            carreau.add(new JLabel(h+""));
                        }else{
                            carreau.add(new JLabel(h+""));
                            gc.ipadx = 0;
                            gc.ipady = 0;
                        }
                        carreau.setBackground(Color.white);
                        carreau.setBorder(BorderFactory.createLineBorder(Color.black));
                        add(carreau,gc);
                        Carreaus.add(carreau);
                        h++;
            }
  
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 9;
        gc.gridheight = 9;
        JPanel test = new  JPanel();
        test.setBorder(BorderFactory.createLineBorder(Color.black));
        add(test,gc);
    }
    }