/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interfaces.DAOtipo_bus;
import Mapeo.TipoBus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Univalle_F
 */
public class DAOTipobusImpl extends Conexion implements DAOtipo_bus {
    
    public DAOTipobusImpl() {}

    /*@Override
    public void registrar(TipoBus ruta) throws Exception {

        String sql_registrar = "INSERT INTO tipo_bus(tpbus_id,tpbus_nombre) VALUES(?)";
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_registrar);
            st.setInt(1, ruta.getTpbusId());
            st.setString(2, ruta.getTpbusNombre());
            st.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }*/

    /*@Override
    public void modificar(int id_tp_a_modificar, TipoBus tp_a_guardar) throws Exception {

        String sql_modificar = "UPDATE ruta SET tpbus_id = (?) , tpbus_nombre = (?) WHERE bus_placa = '"
                + id_tp_a_modificar + "'";
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_modificar);
            st.setInt(1, tp_a_guardar.getTpbusId());
            st.setString(2, tp_a_guardar.getTpbusNombre());
            st.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }*/

    /*@Override
    public void eliminar(int id) throws Exception {
        String sql_eliminar = "DELETE FROM tipo_bus WHERE bus_tipo_id = (?)";
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_eliminar);
            st.setInt(1, tp.getTpbusId());
            st.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }*/

    /*@Override
    public List<TipoBus> listar() throws Exception {

        List<TipoBus> lista = null;
        String sql_listar = "SELECT * FROM tipo_bus";

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_listar);

            lista = new ArrayList();
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                TipoBus mitp = new TipoBus();

                mitp.setTpbusId(rs.getInt("bus_tipo_id"));
                mitp.setTpbusNombre(rs.getString("bus_nombre"));

                lista.add(mitp);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        return lista;
    }*/
    
    

    @Override
    public TipoBus buscar(int id) throws Exception {

        String sql_busqueda = "SELECT * FROM tipo_bus WHERE tpbus_id = '" + id + "'";

        TipoBus mitp = new TipoBus();

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_busqueda);
            ResultSet rs = st.executeQuery();

            if (rs.next()){
                mitp.setTpbusId(rs.getInt("tpbus_id"));
                mitp.setTpbusNombre(rs.getString("tpbus_nombre"));
            }
            
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        return mitp;
    }
    
    @Override
    public boolean existe(int id) throws Exception {
        
        String existeBus = "SELECT * FROM tipo_bus WHERE tpbus_id = '" + id + "'";       
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
