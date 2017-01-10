/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Mapeo.Estacion;
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class PanelPrincipal extends JPanel {
    Image bg = new ImageIcon("mapa_cali.jpg").getImage();
    java.util.List<Estacion> estaciones = new ArrayList<Estacion>();
    String ruta_id="";
    
    public PanelPrincipal(String ruta_id, java.util.List<Estacion> estaciones){
       this.estaciones = estaciones;
        this.ruta_id = ruta_id;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        g.setFont(Font.getFont(Font.SANS_SERIF));
        g.setColor(Color.red);
        
        Estacion mi_tmp_estacion = new Estacion();
        
        
        g.drawString("Recorrido de la ruta " + this.ruta_id, 300, 00);
        
        Iterator iter = this.estaciones.iterator();
        int contador = 0;
        int x_ant = 0;
        int y_ant = 0;
        int x = 0;
        int y = 0;
        
        while (iter.hasNext()) {
            
            mi_tmp_estacion = (Estacion) iter.next();
            String[] tmp = mi_tmp_estacion.getEstacionUbicacion().split("-");
            x = Integer.parseInt(tmp[0])-10;
            y = Integer.parseInt(tmp[1])-10;
            
            if(contador == 0){
                g.fillRect(x,y, 5, 5);
                g.drawString("Estación inicial: "+mi_tmp_estacion.getEstacionNombre(), x + 10, y + 10);
            }else{
                
                g.fillRect(x,y, 5, 5);
                g.drawLine(x_ant, y_ant, x, y);
                g.drawString(mi_tmp_estacion.getEstacionNombre(), x + 10, y + 10);
            }
            
            x_ant = x;
            y_ant = y;
            contador++;
        }
        
    }
}

public class MuestraRecorrido extends JFrame {

}