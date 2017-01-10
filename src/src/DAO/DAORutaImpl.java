/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interfaces.DAOruta;
import Mapeo.Ruta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Univalle_F
 */
public class DAORutaImpl extends Conexion implements DAOruta {
    
    public DAORutaImpl(){}

    @Override
    public String registrar(String ruta_id, String ruta_nombre, String ruta_descrip) throws Exception {
        
        String mensaje = "";

        if (this.existe(ruta_id)) {
            mensaje = "Falló insersión. La ruta que intenta insertar ya existe";
        } else {

            try {
                this.conectar();
                Statement st = this.conexion.createStatement();
                st.executeUpdate("INSERT INTO ruta(ruta_id,ruta_descrip) " + "VALUES ('" + ruta_id + "' , '" + ruta_nombre + "' , '" + ruta_descrip + "')");
                st.close();

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }

            mensaje = "Insersión exitosa";
        }

        return mensaje;
    }

    @Override
    public String modificar(String ruta_old, String ruta_id, String ruta_nombre, String ruta_descrip) throws Exception {
        
        String mensaje = "";

        if (!this.existe(ruta_old)) {//pareciera incoherente pero es necesario pues necesito saber si la ruta a cambiar existe
            mensaje = "Falló la actualización. La ruta que intenta actualizar no existe";
        }if (this.existe(ruta_id)) {// y aqui necesito saber si el id al que se va actualizar la ruta ya existe
            mensaje = "Falló la actualización. La ruta que intenta actualizar ya existe";
        }else {

            try {

                String sql_modificar = "UPDATE ruta SET ruta_id = '" + ruta_id + "' , ruta_nombre = '" + ruta_nombre + "' , ruta_descrip = '" + ruta_descrip + "' WHERE ruta_id = '"
                        + ruta_old + "'";

                this.conectar();
                Statement st = this.conexion.createStatement();
                st.executeUpdate(sql_modificar);
                st.close();

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }

            mensaje = "actualización exitosa";
        }

        return mensaje;

    }

    @Override
    public String eliminar(String ruta_id) throws Exception {
       
        String mensaje = "";
                
        if(!this.existe(ruta_id)){
            mensaje = "Falló la Eliminación. La ruta que intenta eliminar no existe";
        }else{
            
            String sql_eliminar = "DELETE FROM ruta WHERE ruta_id = '" + ruta_id + "'";
            
            try {
                this.conectar();
                Statement st = this.conexion.createStatement();
                st.executeUpdate(sql_eliminar);
                st.close();

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }
            
            mensaje = "Eliminación exitosa";
        }
        
        return mensaje;
    }
    
    @Override
    public List<String> listar() throws Exception {
        
        List<String> lista;
        String sql_listar = "SELECT * FROM ruta";
        
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_listar);
            
            lista = new ArrayList();
            ResultSet rs= st.executeQuery();
            
            while(rs.next()){
                
                if(!rs.getString("ruta_id").equals("")){
                    lista.add(rs.getString("ruta_id"));
                }
            }
            
            rs.close();
            st.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        
        return lista;
    }
    
    @Override
    public Ruta buscar(String id) throws Exception {
        
        String sql_buscar = "SELECT * FROM ruta WHERE ruta_id = '" + id + "'";
                        
        Ruta miruta = new Ruta();
        
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
            ResultSet rs= st.executeQuery();

            if (rs.next()){
                miruta.setRutaId(rs.getString("ruta_id"));
                miruta.setRutaDescrip(rs.getString("ruta_descrip"));
            }
            
            rs.close();
            st.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        
        return miruta;
    }
    
    @Override
    public boolean existe(String ruta_id) throws Exception {
        
        String existeBus = "SELECT * FROM ruta WHERE ruta_id = '" + ruta_id + "'";       
        boolean validar = false;

        
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(existeBus);
            ResultSet rs= st.executeQuery();

            if (rs.next()) {
                do {
                    validar = true;
                } while(rs.next());
            } else {
                    validar = false;
            }
            
            rs.close();
            st.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        
        return validar;
    }
}
