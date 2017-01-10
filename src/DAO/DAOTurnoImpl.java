/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Interfaces.DAOturno;
import Mapeo.Turno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Univalle_F
 */
public class DAOTurnoImpl extends Conexion implements DAOturno {
    
    public DAOTurnoImpl() {}

    @Override
    public String registrar(String dia_inicio, String dia_fin, String hora_inicio, String hora_fin) throws Exception {
        
        String mensaje = "";
        int id_turno = 0;
        String sql_registrar = "INSERT INTO turno(turno_dia_inicio,turno_dia_fin,turno_hora_inicio,turno_hora_fin) VALUES ('" + dia_inicio + "' , '" + dia_fin + "' ,"
                    + " '" + hora_inicio + "' , '" + hora_fin + "') returning turno_id";

        //System.out.println(sql_registrar);
        try {

            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_registrar);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                id_turno = rs.getInt("turno_id");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        mensaje = "Insersion exitosa, el id del turno es " + id_turno;

        return mensaje;
    }

    @Override
    public String modificar(int turno_id, String dia_inicio, String dia_fin, String hora_inicio, String hora_fin) throws Exception {
        
        String mensaje = "";
        
        if (!this.existe(turno_id)) {
            mensaje = "Falló la actualización. El turno no existe";
        } else {

            String sql_modificar = "UPDATE turno SET turno_dia_inicio = '" + dia_inicio + "' , turno_dia_fin = '" + dia_fin + "' , turno_hora_inicio = '" + hora_inicio + "' ,"
                    + "turno_hora_fin = '" + hora_fin + "' WHERE turno_id = '" + turno_id + "'";
            try {


                this.conectar();
                Statement st = this.conexion.createStatement();
                st.executeUpdate(sql_modificar);


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
    public String eliminar(int id) throws Exception {
        
        String mensaje = "";

        if (!this.existe(id)) {
            mensaje = "Falló la eliminación. El turno no existe";
        } else {

            String sql_eliminar = "DELETE FROM turno WHERE turno_id = '" + id + "'";
            String sql_buscar_turno_asignado = "SELECT * FROM turno_asignado JOIN turno ON tasignado_turno = turno_id JOIN empleado ON empleado_id = tasignado_conductor JOIN conductor ON turno_id = '" + id + "'";

            try {
                this.conectar();                
                PreparedStatement st = this.conexion.prepareStatement(sql_buscar_turno_asignado);
                ResultSet rs = st.executeQuery();
                
                if (rs.next()) {
                    mensaje = "Falló la eliminación. El turno que desea eliminar, se encuentra asignado, antes de borrarlo debe desasignarlo de los conductores";
                } else {
                    Statement sn = this.conexion.createStatement();
                    sn.executeUpdate(sql_eliminar);
                    mensaje = "eliminación exitosa";
                }

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }

        }

        return mensaje;
    }

    @Override
    public Turno buscar(int id_turno) throws Exception {

        String sql_busqueda = "SELECT * FROM turno WHERE turno_id = '" + id_turno + "'";

        Turno miturno = new Turno();

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_busqueda);
            ResultSet rs = st.executeQuery();

            if (rs.next()){
                
                miturno.setTurnoId(rs.getInt("turno_id"));
                miturno.setTurnoDiaInicio(rs.getString("turno_dia_inicio"));
                miturno.setTurnoDiaFin(rs.getString("turno_dia_fin"));
                miturno.setTurnoHoraInicio(rs.getString("turno_hora_inicio"));
                miturno.setTurnoHoraFin(rs.getString("turno_hora_fin"));
            }
            
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        return miturno;
    }
    
    @Override
    public boolean existe(int id) throws Exception {
        
        String existeBus = "SELECT * FROM turno WHERE turno_id = '" + id + "'";       
        boolean validar = false;
        
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(existeBus);
            ResultSet rs = st.executeQuery();

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
