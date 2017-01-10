/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Mapeo.Bus;
import Interfaces.DAObus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Univalle_F
 */
public class DAObusImpl extends Conexion implements DAObus{

    @Override
    public String registrar(String placa, int tipo_bus, String ruta_id) throws Exception {
        
        DAORutaImpl miruta_bus = new DAORutaImpl();
        DAOTipobusImpl mitipo_bus = new DAOTipobusImpl();
        String mensaje = "";
            
            if(!mitipo_bus.existe(tipo_bus)){
                mensaje = "Falló insersión. No existe el tipo bus";
            }else if(!miruta_bus.existe(ruta_id)){
                mensaje = "Falló insersión. No existe la ruta";
            }else if(this.existe(placa)){
                mensaje = "Falló insersión. El bus que intenta insertar ya existe";
            }else{
            
                try {
                    this.conectar();
                    Statement st = this.conexion.createStatement();
                    st.executeUpdate("INSERT INTO bus(bus_placa,bus_tipo,bus_ruta) " + "VALUES ('" + placa + "' , '" + tipo_bus + "' , '" + ruta_id + "')");
                    st.close();
                
                }catch(Exception e){
                    throw e;
                }finally{
                    this.cerrar();
                }
                
                mensaje = "Insersión exitosa";
            }
            
            return mensaje;
    }
    
    @Override
    public String modificar(String bus_a_modificar, String placa_bus_update, int tipo_bus_update, String ruta_id_bus_update) throws Exception {

        DAORutaImpl miruta_bus = new DAORutaImpl();
        DAOTipobusImpl mitipo_bus = new DAOTipobusImpl();

        String mensaje = "";

        if (!mitipo_bus.existe(tipo_bus_update)) {
            mensaje = "Falló la actualización. No existe el tipo bus";
        } else if (!miruta_bus.existe(ruta_id_bus_update)) {
            mensaje = "Falló la actualización. No existe la ruta";
        } else if (!this.existe(bus_a_modificar)) {
            mensaje = "Falló la actualización. El bus que intenta actualizar no existe";
        } else {

            try {

                String sql_modificar = "UPDATE bus SET bus_placa = '" + placa_bus_update + "' , bus_tipo = '" + tipo_bus_update + "' , bus_ruta = '" + ruta_id_bus_update + "' WHERE bus_placa = '"
                        + bus_a_modificar + "'";

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
    public Bus buscar(String placa) throws Exception {
        
        String sql_buscar = "SELECT * FROM bus JOIN tipo_bus ON bus_tipo = tpbus_id JOIN ruta ON bus_ruta = ruta_id WHERE bus_placa = '" + placa + "'";
                                
        Bus mibus = new Bus();
        DAORutaImpl miruta_bus = new DAORutaImpl();
        DAOTipobusImpl mitipo_bus = new DAOTipobusImpl();

        
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
            ResultSet rs= st.executeQuery();

            if (rs.next()){
                mibus.setBusPlaca(rs.getString("bus_placa"));     
                mibus.setTipoBus(mitipo_bus.buscar(rs.getInt("tpbus_id")));
                mibus.setRuta(miruta_bus.buscar(rs.getString("ruta_id")));
            }
            
            rs.close();
            st.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        
        return mibus;
    }
    
    @Override
    public boolean existe(String placa) throws Exception {
        
        
        String existeBus = "SELECT bus_placa FROM bus WHERE bus_placa = '" + placa + "'";
        boolean validar = false;
        
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(existeBus);
            ResultSet rs = st.executeQuery();
           
            if (rs.next()) {validar = true;}else {validar = false;}
            
            rs.close();
            st.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        
        return validar;
    }


    @Override
    public String eliminar(String bus_a_eliminar) throws Exception {
        
        String mensaje = "";
                
        if(!this.existe(bus_a_eliminar)){
            mensaje = "Falló la Eliminación. El bus que intenta eliminar no existe";
        }else{
            
            String sql_eliminar = "DELETE FROM bus WHERE bus_placa = '" + bus_a_eliminar + "'";
            
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
    public List<Bus> listar() throws Exception {
        
        List<Bus> lista = null;
        String sql_listar = "SELECT * FROM bus";
        
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_listar);
            
            lista = new ArrayList();
            ResultSet rs= st.executeQuery();
            
            while(rs.next()){
                Bus mibus = new Bus();
                DAORutaImpl daoruta  = new DAORutaImpl();

                mibus.setBusPlaca(rs.getString("bus_placa"));
                mibus.setRuta(daoruta.buscar((rs.getString("bus_ruta_id"))));
                
                mibus.setBusPlaca(rs.getString("bus_ruta_id"));
                lista.add(mibus);
            }
            
            rs.close();
            st.close();
            
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        
        return lista;
    }
}
