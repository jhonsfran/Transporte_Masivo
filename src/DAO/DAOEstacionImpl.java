/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interfaces.DAOEstacion;
import Mapeo.Administrador;
import Mapeo.Estacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Univalle_F
 */
public class DAOEstacionImpl extends Conexion implements DAOEstacion {
    
    public DAOEstacionImpl(){}

    @Override
    public String registrar(String estacion_nombre, String estacion_ubicacion, String estacion_admin) throws Exception {
        DAOEmpleadoImpl mi_empleado = new DAOEmpleadoImpl();
        String mensaje = "";
        int estacion_id = 0;

        if (!mi_empleado.existe(estacion_admin)) {
            mensaje = "Falló insersión. El administrador que intenta ingresar no existe";
        }else if (mi_empleado.buscarEmpleado(estacion_admin).getEmpleadoRol() != 3) {
            mensaje = "Falló insersión. El empleado que intenta ingresar no es un administrador";
        }else if (this.existeAdmin(estacion_admin)) {
            mensaje = "Falló insersión. El administrador que está ingresando ya administra una estación";
        } else {

        String sql_inserta = "INSERT INTO estacion (estacion_nombre,estacion_ubicacion,estacion_administrador) VALUES ('" + estacion_nombre + "' , '" + estacion_ubicacion + "' , '" + estacion_admin + "') returning estacion_id";

        //System.out.println(sql_venta);

            try {

                this.conectar();
                PreparedStatement st = this.conexion.prepareStatement(sql_inserta);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    estacion_id = rs.getInt("estacion_id");
                }

                mensaje = "Insersion exitosa, el id de la estacion es " + estacion_id;

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }
        }

        return mensaje;
    }

    @Override
    public String modificar(int estacion_id, String estacion_nombre, String estacion_ubicacion, String estacion_admin) throws Exception {
        
        String mensaje = "";

        if (!this.existe(estacion_id)) {
            mensaje = "Falló la actualización. La estación que intenta actualizar no existe";
        }else {

            try {

                String sql_modificar = "UPDATE estacion SET estacion_nombre = '" + estacion_nombre + "' , estacion_ubicacion = '" + estacion_ubicacion + "'  , estacion_administrador = '" + estacion_admin + "'"
                        + "WHERE estacion_id = '" + estacion_id + "'";

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
    public String eliminar(int estacion_id) throws Exception {
       
        String mensaje = "";
                
        if(!this.existe(estacion_id)){
            mensaje = "Falló la Eliminación. La estacion que intenta eliminar no existe";
        }else{
            
            String sql_eliminar = "DELETE FROM estacion WHERE estacion_id = '" + estacion_id + "'";
            
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
    public Estacion buscar(int estacion_id) throws Exception {
        
        String sql_buscar = "SELECT * FROM estacion WHERE estacion_id = '" + estacion_id + "'";
                        
        Estacion mi_estacion = new Estacion();
        Administrador mi_admin = new Administrador();
        

        try {
            
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
            ResultSet rs= st.executeQuery();

            if (rs.next()){
                mi_estacion.setEstacionId(rs.getInt("estacion_id"));
                mi_estacion.setEstacionNombre(rs.getString("estacion_nombre"));
                mi_estacion.setEstacionUbicacion(rs.getString("estacion_ubicacion"));
                
                mi_admin.setAdministradorEmpleadoId(rs.getString("estacion_administrador"));
                        
                mi_estacion.setEstacionAdministrador(mi_admin);
            }
            
            rs.close();
            st.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        return mi_estacion;
    }
    
    @Override
    public boolean existe(int estacion_id) throws Exception {
        
        String existeBus = "SELECT * FROM estacion WHERE estacion_id = '" + estacion_id + "'";       
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
    
    public boolean existeAdmin(String admin_id) throws Exception {

        String existeBus = "SELECT * FROM estacion WHERE estacion_administrador = '" + admin_id + "'";
        boolean validar = false;

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(existeBus);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                do {
                    validar = true;
                } while (rs.next());
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
    
    public List<String> listarEstaciones() throws Exception {

        String retun_data = "";

        String sql_buscar = "SELECT estacion_id,estacion_nombre,estacion_ubicacion FROM estacion";

        List<String> estaciones = new ArrayList<String>();

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
            ResultSet rs = st.executeQuery();

            //System.out.println(sql_buscar);
            if (rs != null) {

                while (rs.next()) {
                    //System.out.println(rs.getRow());
                    //System.out.println(rs.getString("empleado_id"));
                    
                    retun_data = rs.getString("estacion_id") + "-" +rs.getString("estacion_nombre")+ "-" +rs.getString("estacion_ubicacion");
                    estaciones.add(retun_data);
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
        return estaciones;
    }
    
    public boolean existeAsigna(String ruta_id, String recorrido_ruta) throws Exception {

        String existeBus = "SELECT * FROM transita WHERE transita_ruta = '" + ruta_id + "'";
        boolean validar = false;

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(existeBus);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                do {
                    validar = true;
                } while (rs.next());
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
    
    public String registrar_asignacion(String ruta_id, String recorrido_ruta) throws Exception {
        
        DAORutaImpl mi_ruta = new DAORutaImpl();
        String mensaje = "";

        if (!mi_ruta.existe(ruta_id)) {
            mensaje = "Falló insersión. La Ruta no existe";
        } else if (this.existeAsigna(ruta_id, recorrido_ruta)) {
            mensaje = "Falló insersión. La asignación de ruta ya se encuentra en la base de datos";
        } else {

            String sql_inserta = "INSERT INTO transita VALUES ('" + ruta_id + "' , '" + recorrido_ruta + "')";

            //System.out.println(sql_venta);
            try {

                this.conectar();
                
                Statement st = this.conexion.createStatement();
                st.executeUpdate(sql_inserta);
                mensaje = "Insersion exitosa";

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }
        }

        return mensaje;
    }
    
    public String get_asignacion(String ruta_id) throws Exception {
        
        DAORutaImpl mi_ruta = new DAORutaImpl();
        String recorrido_ruta = "";

        if (!mi_ruta.existe(ruta_id)) {
            recorrido_ruta = "";
        } else if (!this.existeAsigna(ruta_id, recorrido_ruta)) {
            recorrido_ruta = "";
        } else {

            String sql_buscar = "SELECT * FROM transita WHERE transita_ruta = '" + ruta_id + "'";
            

            //System.out.println(sql_venta);
            try {

                this.conectar();
                PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
                ResultSet rs = st.executeQuery();

                //System.out.println(sql_buscar);
                if (rs != null) {

                    while (rs.next()) {
                        //System.out.println(rs.getRow());
                        //System.out.println(rs.getString("empleado_id"));

                        recorrido_ruta = rs.getString("transita_recorrido");
                    }
                }

            rs.close();
            st.close();

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }
        }

        return recorrido_ruta;
    }
}