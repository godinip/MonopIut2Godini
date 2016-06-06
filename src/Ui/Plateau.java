/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author godinip
 */
public class Plateau extends JPanel{
    public Plateau(){
        setDoubleBuffered(true);
    }
    
     public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect(25, 25, 500, 500);
    }
}
