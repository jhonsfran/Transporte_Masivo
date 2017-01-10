/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Conexion;
import DAO.DAOEmpleadoImpl;

import DAO.DAObusImpl;
import Vista.CrudAsigna;
import Vista.CrudEmpleado;
import Vista.CrudEstacion;
import Vista.Login;
import Vista.CrudTurno;
import Vista.MuestraRuta;
import Vista.PQRS;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;


/**
 *
 * @author Univalle_F
 */
public class Prueba extends Conexion {
    
    DefaultTableModel tableModel;

    
    public static void main(String args[]){
        
        /*DAObusImpl mi_dao_bus = new DAObusImpl();*/
        
        /*CrudEmpleado ni_crud = new CrudEmpleado();
        ni_crud.setLocationRelativeTo(null);
        ni_crud.setVisible(true);//muestro la interfaz de reserva*/
        
        Login mi_login = new Login();
        mi_login.setLocationRelativeTo(null);
        mi_login.setVisible(true);

        try {
            /*System.out.println(mi_dao_bus.registrar("NNN 918",1,"T31"));
            System.out.println(mi_dao_bus.modificar("NNN 918","NNN 900", 2, "T47A"));
            System.out.println(mi_dao_bus.eliminar("NNN 900"));*/
            
            
            
            
            
            /*
            
            Conexion conect = new Conexion();
            conect.conectar();
            String path = null;
            try {
            path = new java.io.File(".").getCanonicalPath();
            } catch (IOException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
            
            System.out.println(path + "\\src\\Vista\\Buses.jasper");

            JasperReport reporte = JasperCompileManager.compileReport(path + "\\src\\Vista\\Buses.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, conect.getConexion());

            JasperViewer jasperViewer = new JasperViewer(print);
            jasperViewer.setVisible(true);

            conect.cerrar();
            
            } catch (JRException ex) {
            ex.printStackTrace();
            }*/
        } catch (Exception ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
}
