/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interfaces.DAOtarjeta;
import Interfaces.DAOusuario;
import Mapeo.Tarjeta;
import Mapeo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Univalle_F
 */
public class DAOTarjetaImpl extends Conexion implements DAOtarjeta {
       
    public String venderTarjeta(String auxiliar) throws Exception {

        String mensaje = "";
        String mi_user_default = "0";//usuario por defecto para las tarjetas nuevas
        int tarjeta_pin = 0;
        String sql_venta = "INSERT INTO tarjeta (tarjeta_saldo,tarjeta_estado,tarjeta_usuario,tarjeta_auxiliar) VALUES ('0' , 'true' , '" + mi_user_default + "','" + auxiliar + "') returning tarjeta_pin";

        DAOEmpleadoImpl mi_empleado = new DAOEmpleadoImpl();
        
        if(!mi_empleado.existe(auxiliar)){
            mensaje = "Falló la creación de la tarjeta. El empleado que está logeado en el sistema no existe";
        }else if(mi_empleado.buscarEmpleado(auxiliar).getEmpleadoRol() != 2){
            mensaje = "Falló la creación de la tarjeta. El empleado que está logeado en el sistema debe ser un auxiliar";
        }else{
            //System.out.println(sql_venta);

            try {

                this.conectar();
                PreparedStatement st = this.conexion.prepareStatement(sql_venta);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    tarjeta_pin = rs.getInt("tarjeta_pin");
                }

                mensaje = "Insersion exitosa, el id de la tarjeta es" + tarjeta_pin;

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }

        }
        

        return mensaje;
    }

    @Override
    public String modificar(int tarjetaPin, String usuario_id, String usuario_id_old,  String tarjetaSaldo, boolean tarjetaEstado) throws Exception {


        String mensaje = "";
        DAOUsuarioImpl user = new DAOUsuarioImpl();

        if (!user.existe(usuario_id)) {
            mensaje = "Falló la actualización. El usuario que intenta actualizar no existe";
        } else if (!this.existe(tarjetaPin)) {
            mensaje = "Falló la actualización. El id de la tarjeta que intenta actualizar no existe";
        } else if (!user.existe(usuario_id_old)) {
            mensaje = "Falló la actualización. El usuario no existe";
        } else if (this.existeUsuario(usuario_id) && usuario_id_old == usuario_id) {
            mensaje = "Falló la actualización. El usuario ya tiene asignado una tarjeta";
        }else {

            try {
                 
                String sql_modificar = "UPDATE tarjeta SET tarjeta_pin = '" + tarjetaPin + "' , tarjeta_saldo = '" + tarjetaSaldo + "' , tarjeta_estado = '" 
                        + tarjetaEstado + "' , tarjeta_usuario = '" + usuario_id + "' WHERE tarjeta_pin = '" + tarjetaPin + "'";
                
                //System.out.println(sql_modificar);

                this.conectar();
                try (Statement st = this.conexion.createStatement()) {
                    st.executeUpdate(sql_modificar);
                }

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }

            mensaje = "actualización exitosa";
        }

        return mensaje;

    }

    
    public String recarga(int tarjetaPin, String saldo, String auxiliar_id) throws Exception {

        String mensaje = "";
        DAOEmpleadoImpl mi_empleado = new DAOEmpleadoImpl();
        
        if (!(saldo).matches("[0-9]*")) {
            mensaje = "El saldo debe ser un número sin comas ni puntos";
        }else if(Integer.parseInt(saldo) <= 0){
            mensaje = "El saldo a recargar debe ser mayor a cero";
        }else if (!this.existe(tarjetaPin)) {
            mensaje = "Falló la recarga. La tarjeta no existe";
        }else if (!this.buscar(tarjetaPin).isTarjetaEstado()) {
            mensaje = "Falló la recarga. La tarjeta se encuentra bloqueada, debe ir a una estación para desbloquearla";
        }else if (!mi_empleado.existe(auxiliar_id)) {
            mensaje = "Falló la recarga. El empleado que intenta hacer la recarga no es un auxiliar";
        }else if (mi_empleado.existe(auxiliar_id) && mi_empleado.buscarEmpleado(auxiliar_id).getEmpleadoRol() != 2) {
            mensaje = "Falló la recarga. El empleado que intenta realizar la recarga no es un auxiliar del servicio al cliente";
        } else {

            try {
                
                int saldo_actual = 0;
                int saldo_final = 0;
                
                String sql_buscar = "SELECT tarjeta_saldo FROM tarjeta WHERE tarjeta_pin = '" + tarjetaPin + "'";
                String sql_insert_recarga = "INSERT INTO recarga VALUES('" + tarjetaPin + "','" + new java.util.Date() + "','"
                        + mi_empleado.buscar(auxiliar_id).getAuxServCliente().getEstacion().getEstacionId() + "','" + saldo + "')";

                //System.out.println(sql_insert_recarga);

                    this.conectar();
                    PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
                    ResultSet rs = st.executeQuery();
                    
                    if (rs.next()) {
                        
                        saldo_actual = Integer.parseInt(rs.getString("tarjeta_saldo"));
                        saldo_final = saldo_actual + Integer.parseInt(saldo);

                    }

                    String sql_modificar = "UPDATE tarjeta SET tarjeta_saldo = '" + saldo_final + "' WHERE tarjeta_pin = '" + tarjetaPin + "'";

                    try (Statement sn = this.conexion.createStatement()) {
                        sn.executeUpdate(sql_modificar);
                        sn.executeUpdate(sql_insert_recarga);
                    }
                    
                    mensaje = "Recarga exitosa, su saldo actual es: " + saldo_final;

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }

        }

        return mensaje;

    }
    
    public String descuenta(int tarjetaPin, String ruta_id) throws Exception {

        String mensaje = "";
        int saldo = 2000;//esta variable define el precio del pasaje
        
        DAORutaImpl mi_ruta = new DAORutaImpl();

        if (!this.existe(tarjetaPin)) {
            mensaje = "Falló el descuento del pasaje. La tarjeta no existe";
        }else if (!this.buscar(tarjetaPin).isTarjetaEstado()) {
            mensaje = "Falló el descuento del pasaje. La tarjeta se encuentra bloqueada, debe ir a una estación para desbloquearla";
        }else if (!mi_ruta.existe(ruta_id)) {
            mensaje = "Falló el descuento del pasaje. La ruta a la cual ingresa el usuario no existe.";
        }  else {

            try {
                
                int saldo_actual = 0;
                int saldo_final = 0;
                String sql_modificar  = "";
                String sql_aborda = "INSERT INTO aborda VALUES ('" + tarjetaPin + "','" + ruta_id + "','" + new java.util.Date() + "')";
                
                this.conectar();
                try (Statement sn = this.conexion.createStatement()) {
                
                    if(buscar(tarjetaPin).getUsuario().getUsuarioId().equals("0")){//si la tarjeta es genérica no se le puede dar crédito

                        saldo_actual = Integer.parseInt(buscar(tarjetaPin).getTarjetaSaldo());
                        saldo_final = saldo_actual - saldo;

                        if(saldo_final >= 0){
                            sql_modificar = "UPDATE tarjeta SET tarjeta_saldo = '" + saldo_final + "' WHERE tarjeta_pin = '" + tarjetaPin + "'";
                            sn.executeUpdate(sql_modificar);
                            
                            
                            mensaje = "Pasaje descontado, su saldo es: " + saldo_final;

                        }else{
                            mensaje = "Saldo insuficiente, debe recargar!";
                        }

                    }else{//tarjeta personalizada permite crédito de menos tres pasajes

                        saldo_actual = Integer.parseInt(buscar(tarjetaPin).getTarjetaSaldo());
                        saldo_final = saldo_actual - saldo;

                        if (saldo_final >= (-3*saldo)) {
                            sql_modificar = "UPDATE tarjeta SET tarjeta_saldo = '" + saldo_final + "' WHERE tarjeta_pin = '" + tarjetaPin + "'";
                            sn.executeUpdate(sql_modificar);
                            mensaje = "Pasaje descontado, su saldo es: " + saldo_final;

                        } else {
                            mensaje = "Saldo insuficiente, debe recargar!";
                        }
                    }
                    
                    sn.executeUpdate(sql_aborda);//luego de descontado el pasaje del saldo total, se registra en aborda
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
    public Tarjeta buscar(int tarjeta_pin) throws Exception {

        String sql_buscar = "SELECT * FROM tarjeta WHERE tarjeta_pin = '" + tarjeta_pin + "'";

        Tarjeta mi_tarjeta = new Tarjeta();

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                mi_tarjeta.setTarjetaPin(rs.getInt("tarjeta_pin"));
                mi_tarjeta.setTarjetaSaldo(rs.getString("tarjeta_saldo"));
                mi_tarjeta.setTarjetaEstado(rs.getBoolean("tarjeta_estado"));
                
                
                DAOUsuarioImpl user = new DAOUsuarioImpl();
                Usuario mi_usuario = user.buscar(rs.getString("tarjeta_usuario"));
                mi_tarjeta.setUsuario(mi_usuario);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        return mi_tarjeta;
    }

    @Override
    public boolean existe(int tarjeta_pin) throws Exception {

        String existeBus = "SELECT * FROM tarjeta WHERE tarjeta_pin = '" + tarjeta_pin + "'";
        boolean validar = false;

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(existeBus);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                validar = true;
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
    
    public boolean existeUsuario(String usuario_id) throws Exception {

        String existeBus = "SELECT * FROM tarjeta WHERE tarjeta_usuario = '" + usuario_id + "'";
        boolean validar = false;

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(existeBus);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                validar = true;
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

    @Override
    public String eliminar(int tarjeta_pin) throws Exception {

        String mensaje = "";       


        if (!this.existe(tarjeta_pin)) {
            mensaje = "Falló la Eliminación. La tarjeta que intenta eliminar no existe";
        } else {
            
            String sql_eliminar = "DELETE FROM tarjeta WHERE tarjeta_pin = '" + tarjeta_pin + "'";

            try {

                this.conectar();
                try (Statement st = this.conexion.createStatement()) {
                    st.executeUpdate(sql_eliminar);
                }

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }

            mensaje = "Eliminación exitosa";
        }

        return mensaje;

    }

}
