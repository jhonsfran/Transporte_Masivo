/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class BgPanelAsigna extends JPanel {
    Image bg = new ImageIcon("mapa_cali.jpg").getImage();
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        
    }
}

class ChecksPanel extends JPanel {
    ChecksPanel() {
        JRadioButton radioButton = new JRadioButton("prueba");
        //radioButton.setBounds(Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1]), 10, 10);
        //this.setBounds(400, 400, 10, 10);
        //radioButton.setLocation(400, 400);
        add(radioButton);
        setOpaque(false);
    }
} 

public class MapaAsigna extends JFrame {

}