/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author PORTATIL
 */

public class VentanaConfirmacion extends JDialog{
    
    private final Container contenedor;
    private int validador;
    
    public VentanaConfirmacion(){
        
        contenedor = getContentPane();
        contenedor.setLayout(null);
        
        contenedor.add(new JLabel("En realidad desea asignar la ubicación para la estación?"));
     
        JButton asignar = new JButton("Asignar");//creo un boton
        asignar.setSize(80, 29);
        asignar.reshape(110, 20, 100, 27);// x,y,largo,ancho
        contenedor.add(asignar);
        
        JButton cancelar = new JButton("Cancelar");//creo un boton
        cancelar.setSize(80, 29);
        cancelar.reshape(110, 30, 100, 27);// x,y,largo,ancho
        contenedor.add(cancelar);
        

                
        //Asigna un titulo a la barra de titulo
        setTitle("Mensaje de Confirmacion");
        //tamaño de la ventana
        setSize(300,200);
        //pone la ventana en el Centro de la pantalla
        setLocation(0,0);
        setVisible(true);

        asignar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setValidador(1);
                setVisible(false);
            }
        });
        
        asignar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setValidador(0);
                setVisible(false);
            }
        });

    }
    
    public void setValidador(int x) {
        this.validador = x;
    }

    public int getValidador() {
        return this.validador;
    }
}
