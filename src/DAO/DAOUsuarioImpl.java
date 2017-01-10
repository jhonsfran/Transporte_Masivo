/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interfaces.DAOusuario;
import Mapeo.Empleado;
import Mapeo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Univalle_F
 */
public class DAOUsuarioImpl extends Conexion implements DAOusuario {
    
    @Override
    public String registrar(String usuario_id, String usuario_nombre, String usuario_telefono) throws Exception {

        String mensaje = "";
        

        if (this.existe(usuario_id)) {
            mensaje = "Falló insersión. El usuario que intenta insertar ya existe";
        }else {
            try {
                this.conectar();
                Statement st = this.conexion.createStatement();
                st.executeUpdate("INSERT INTO usuario VALUES ('" + usuario_id + "' , '" + usuario_nombre + "' , '" + usuario_telefono + "' )");
                st.close();

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }

            mensaje = "Insersion exitosa";
        }

        return mensaje;
    }

    @Override
    public String modificar(String usuario_id_old, String usuario_id, String usuario_nombre, String usuario_telefono) throws Exception {


        String mensaje = "";

        if (!this.existe(usuario_id_old)) {
            mensaje = "Falló la actualización. El usuario que intenta actualizar no existe";
            
        } else if (!this.existe(usuario_id)) {
            mensaje = "Falló la actualización. El id del usuario que intenta actualizar no existe";
        } else {

            try {
                 
                String sql_modificar = "UPDATE usuario SET usuario_id = '" + usuario_id + "' , usuario_nombre = '" + usuario_nombre + "' , usuario_telefono = '" + usuario_telefono + "'"
                        + " WHERE usuario_id = '" + usuario_id + "'";
                
                //System.out.println(sql_modificar);
                //System.out.println("xxxxxxxxxxxxxxxxxx");

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

    @Override
    public Usuario buscar(String usuario_id) throws Exception {

        String sql_buscar = "SELECT * FROM usuario WHERE usuario_id = '" + usuario_id + "'";

        Usuario mi_usuario = new Usuario();

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                mi_usuario.setUsuarioId(rs.getString("usuario_id"));
                mi_usuario.setUsuarioNombre(rs.getString("usuario_nombre"));
                mi_usuario.setUsuarioTelefono(rs.getString("usuario_telefono"));
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        return mi_usuario;
    }

    @Override
    public boolean existe(String usuario_id) throws Exception {

        String existeBus = "SELECT * FROM usuario WHERE usuario_id = '" + usuario_id + "'";
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
    public String eliminar(String usuario_id) throws Exception {

        String mensaje = "";       


        if (!this.existe(usuario_id)) {
            mensaje = "Falló la Eliminación. El usuario que intenta eliminar no existe";
        } else {
            
            String sql_eliminar = "DELETE FROM usuario WHERE usuario_id = '" + usuario_id + "'";

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
    
    public boolean existePqrs(int ticket){
        
        String query = "SELECT * FROM pqrs WHERE pqrs_ticket = '" + ticket + "'";
        boolean validar = false;

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                validar = true;
            } else {
                validar = false;
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrar();
        }

        return validar;
        
    }
    
    public String registrarPQRS(String usuario_id, int motivo, String descrip, int estacion) throws Exception {

        String mensaje = "";
       
        if (!this.existe(usuario_id)) {
            mensaje = "Falló insersión. El usuario que intenta insertar no existe";
        } else {
            try {
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String today = df.format(new java.util.Date());
                
                this.conectar();
                Statement st = this.conexion.createStatement();
                st.executeUpdate("INSERT INTO pqrs (pqrs_motivo,pqrs_descrip,pqrs_fecha_inicio,pqrs_estado"
                        + ",pqrs_usuario,pqrs_estacion) "
                        + "VALUES ('" + motivo + "','" + descrip + "','" + today + "', 'INICIADO' , '" + usuario_id + "' , '" + estacion + "' )");
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
    
    public List<String> motivoPQRS() throws Exception {

        String retun_data = "";

        String sql_buscar = "SELECT * FROM tipo_motivo";

        List<String> motivosPqrs = new ArrayList<String>();

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
            ResultSet rs = st.executeQuery();

            //System.out.println(sql_buscar);
            if (rs != null) {

                while (rs.next()) {
                    //System.out.println(rs.getRow());
                    //System.out.println(rs.getString("empleado_id"));

                    retun_data = rs.getString("tpmotivo_id") + "-" + rs.getString("tpmotivo_nombre");
                    motivosPqrs.add(retun_data);
                }
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        /*if(empleados.isEmpty()){
            empleados.add("No hay ningún " + nombre + "");//seteo la posicion cero por si no hay datos
        }*/
        return motivosPqrs;
    }
    
    public String consultarPqrs(int ticket) throws Exception{
        
        String return_data = "";
        String sql_buscar = "SELECT * FROM pqrs "
                + "WHERE pqrs_ticket = '" + ticket +"'";
        
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
            ResultSet rs = st.executeQuery();

            //System.out.println(sql_buscar);
            if (rs != null) {

                while (rs.next()) {

                    if(rs.getString("pqrs_estado").equals("RESPONDIDO")) {
                    
                        String nombre_encargado = "SELECT empleado_nombre FROM empleado"
                                + "WHERE empleado_id = '"+ rs.getString("pqrs_resuelta_por") +"'";
                        
                        PreparedStatement st2 = this.conexion.prepareStatement(nombre_encargado);
                        ResultSet rs2 = st2.executeQuery();
                        
                        return_data = "Ticket: "+ rs.getString("pqrs_ticket") + "\nMotivo: "+ rs.getString("pqrs_motivo")
                            + "\nDescripción: "+ rs.getString("pqrs_descrip") + "\nFecha inicio: " + rs.getString("pqrs_fecha_inicio")
                            + "\nEstado: "+ rs.getString("pqrs_estado") + "\nEstación: "+ rs.getString("pqrs_estacion")
                            + "\nEncargado: "+rs2.getString("empleado_nombre") + "\nFecha Respuesta : "+rs.getString("pqrs_fecha_resuelta")
                            + "\nRespuesta: "+rs.getString("pqrs_respuesta")
                            + listarMedidasPqrs(ticket);
                    }
                    else {
                        
                        return_data = "Ticket: "+ rs.getString("pqrs_ticket") + "\nMotivo: "+ rs.getString("pqrs_motivo")
                            + "\nDescripción: "+ rs.getString("pqrs_descrip") + "\nFecha inicio: " + rs.getString("pqrs_fecha_inicio")
                            + "\nEstado: "+ rs.getString("pqrs_estado") + "\nEstación: "+ rs.getString("pqrs_estacion")
                            + "\nEncargado: Esperando respuesta" + "\nFecha Respuesta: Esperando respuesta"
                            + "\nRespuesta: Esperando respuesta";
                        
                    }
                }
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        
        return return_data;
        
    }

    public String listarMedidasPqrs(int ticket) {
        
       String return_data = "\n\n Medidas: ";

        String sql_buscar = "SELECT * FROM pqrs_medida";

        List<String> motivosPqrs = new ArrayList<String>();

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
            ResultSet rs = st.executeQuery();

            //System.out.println(sql_buscar);
            if (rs != null) {

                while (rs.next()) {

                    return_data = "\n\n * " +rs.getString("pqrs_medida_descrip")
                            + "\nEstado: " + rs.getString("pqrs_medida_estado");
                    motivosPqrs.add(return_data);
                }
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            return_data = "Error al consultar medidas tomadas";
        } finally {
            this.cerrar();
        }
        
        return return_data;
    }
    
}

