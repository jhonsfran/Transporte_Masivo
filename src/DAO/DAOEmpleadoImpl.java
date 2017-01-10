/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interfaces.DAOempleado;
import Mapeo.Administrador;
import Mapeo.AuxServCliente;
import Mapeo.Bus;
import Mapeo.Conductor;
import Mapeo.DirOperativo;
import Mapeo.Empleado;
import Mapeo.Gerente;
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
public class DAOEmpleadoImpl extends Conexion implements DAOempleado {
    
    @Override
    public String registrar(String empleado_id, String empleado_nombre, String empleado_telefono, String empleado_sueldo, int tipo, String placa_bus, int estacion_id) throws Exception {

        String mensaje = "";
        DAObusImpl mi_dao_bus = new DAObusImpl();
        DAOEstacionImpl mi_dao_estacion = new DAOEstacionImpl();
        

        if (this.existe(empleado_id)) {
            mensaje = "Falló insersión. El empleado que intenta insertar ya existe";
        }else if(!mi_dao_bus.existe(placa_bus) && tipo == 1){
            mensaje = "Falló insersión. El bus asignado al empleado no existe";
        }else if(!mi_dao_estacion.existe(estacion_id) && tipo == 2){
            mensaje = "Falló insersión. La estacion asignada al empleado no existe";
        }else {
            try {
                this.conectar();
                Statement st = this.conexion.createStatement();
                st.executeUpdate("INSERT INTO empleado(empleado_id,empleado_nombre,empleado_telefono,empleado_sueldo,empleado_rol) VALUES ('" + empleado_id + "' , '" + empleado_nombre + "' , '" + empleado_telefono + "' "
                        + ", '" + empleado_sueldo + "' , '" + tipo + "' )");
                st.executeUpdate(this.insertaTipoEmpleado(empleado_id,placa_bus,estacion_id,tipo));
                
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
    public String modificar(String empleado_id_old ,String empleado_id, String empleado_nombre, String empleado_telefono, String empleado_sueldo, int tipo, String placa_bus, int estacion_id) throws Exception {

        DAObusImpl mi_bus = new DAObusImpl();
        DAOEstacionImpl mi_estacion = new DAOEstacionImpl();

        String mensaje = "";
        String sql_update_empleado_tipo = "";

        if (mi_estacion.existe(estacion_id) && tipo == 2) {
            mensaje = "Falló la actualización. La estación ya está asignada";
        } else if (!mi_bus.existe(placa_bus) && tipo == 1) {
            mensaje = "Falló la actualización. No existe el bus";
        } else if (validaBus(placa_bus) && tipo == 1) {
            mensaje = "Falló la actualización. El bus ya está asignado";
        } else if (validaEstacion(estacion_id) && tipo == 2) {
            mensaje = "El bus ya está asignado";
        } else if (!this.existe(empleado_id_old)) {
            mensaje = "Falló la actualización. El empleado que intenta actualizar no existe";
        } else if (this.existe(empleado_id) && !(empleado_id_old.equals(empleado_id))) {
            mensaje = "Falló la actualización. El id del empleado que intenta actualizar ya existe";
        } else {

            try {
                
                Empleado mi_old_empleado = this.buscar(empleado_id_old);
                int old_tipo = mi_old_empleado.getEmpleadoRol();
                
                switch (tipo) {
                    case 1:
                        //conductor
                        sql_update_empleado_tipo = "UPDATE conductor SET placa_bus = '" + placa_bus + "'  WHERE conductor_empleado_id = '" + empleado_id + "'  ";

                        break;
                    case 2:
                        //auxiliar servicio al cliente
                        sql_update_empleado_tipo = "UPDATE aux_serv_cliente SET aux_serv_cliente_estacion = '" + estacion_id + "' WHERE aux_serv_cliente_empleado_id = '" + empleado_id + "'";
                        break;
                    default:
                        break;
                }
                               

                String sql_modificar = "UPDATE empleado SET empleado_id = '" + empleado_id + "' , empleado_nombre = '" + empleado_nombre + "' , empleado_telefono = '" + empleado_telefono + 
                        "' , empleado_sueldo = '" + empleado_sueldo + "', empleado_rol = '" + tipo + "' WHERE empleado_id = '" + empleado_id_old + "'";

                this.conectar();
                try (Statement st = this.conexion.createStatement()) {
                    if (!(tipo == old_tipo) || !(empleado_id_old.equals(empleado_id))) {
                        
                        st.executeUpdate(this.eliminarTipoEmpleado(empleado_id_old));
                        st.executeUpdate(sql_modificar);
                        st.executeUpdate(this.insertaTipoEmpleado(empleado_id,placa_bus,estacion_id,tipo));
                        
                    }else{
                        
                        st.executeUpdate(sql_modificar);
                        
                        if(tipo == 1 || tipo ==2){
                            st.executeUpdate(sql_update_empleado_tipo);
                        }
                        
                    }
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
    public Empleado buscar(String empleado_id) throws Exception {
        
        Empleado mi_empleado = this.buscarEmpleado(empleado_id);

        try {
            
            
            int rol = mi_empleado.getEmpleadoRol();

            this.conectar();
            PreparedStatement st1 = this.conexion.prepareStatement(this.buscarTipoEmpleado(empleado_id,rol));
            ResultSet rs1 = st1.executeQuery();

            if (rs1.next()) {

                switch (rol) {

                    case 1://conductor

                        Conductor mi_conductor = new Conductor();
                        DAObusImpl mi_bus = new DAObusImpl();

                        mi_conductor.setBus(mi_bus.buscar(rs1.getString("conductor_bus")));
                        mi_conductor.setEmpleado(mi_empleado);
                        mi_empleado.setConductor(mi_conductor);

                        break;
                    case 2://auxiliar

                        AuxServCliente mi_auxiliar = new AuxServCliente();
                        DAOEstacionImpl mi_estacion = new DAOEstacionImpl();

                        mi_auxiliar.setEmpleado(mi_empleado);
                        mi_auxiliar.setEstacion(mi_estacion.buscar(rs1.getInt("aux_serv_cliente_estacion")));
                        mi_empleado.setAuxServCliente(mi_auxiliar);

                        break;
                    case 3://admin
                        Administrador mi_admin = new Administrador();

                        mi_admin.setAdministradorEmpleadoId(empleado_id);
                        mi_empleado.setAdministrador(mi_admin);

                        break;
                    case 4://dir operativo

                        DirOperativo mi_dir = new DirOperativo();

                        mi_dir.setDirOperativoEmpleadoId(empleado_id);
                        mi_empleado.setDirOperativo(mi_dir);

                        break;
                    case 5://gerente

                        Gerente mi_gerente = new Gerente();

                        mi_gerente.setGerenteEmpleadoId(empleado_id);
                        mi_empleado.setGerente(mi_gerente);

                        break;
                    default:
                        break;
                }
            }

            rs1.close();
            st1.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        return mi_empleado;
    }
    
    public Empleado buscarEmpleado(String empleado_id) throws Exception {


        String sql_buscar = "SELECT * FROM empleado WHERE empleado_id = '" + empleado_id + "'";

        Empleado mi_empleado = new Empleado();

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
            ResultSet rs = st.executeQuery();

            //JOptionPane.showMessageDialog(null, sql_buscar);
            if (rs.next()) {

                mi_empleado.setEmpleadoId(rs.getString("empleado_id"));
                mi_empleado.setEmpleadoNombre(rs.getString("empleado_nombre"));
                mi_empleado.setEmpleadoTelefono(rs.getString("empleado_telefono"));
                mi_empleado.setEmpleadoSueldo(rs.getString("empleado_sueldo"));

                int rol = rs.getInt("empleado_rol");
                mi_empleado.setEmpleadoRol(rol);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

        return mi_empleado;
    }
    
    public List<String> listarEmpleados(int rol) throws Exception {
        
        String condicion = "";
        String nombre = "";
        
        
        switch (rol) {
            case 1:
                condicion = "JOIN conductor ON empleado_id = conductor_empleado_id";
                nombre = "Conductor";
                break;
            case 2:
                condicion = "JOIN aux_serv_cliente ON empleado_id = aux_serv_cliente_empleado_id";
                nombre = "Auxiliar";
                break;
            case 3:
                condicion = "JOIN administrador ON empleado_id = administrador_empleado_id";
                nombre = "Administrador";
                break;
            case 4:
                condicion = "JOIN dir_operativo ON empleado_id = dir_operativo_empleado_id";
                nombre = "Dir operativo";
                break;
            case 5:
                condicion = "JOIN gerente ON empleado_id = gerente_empleado_id";
                nombre = "Gerente";
                break;
            default:
                break;
        }

        String sql_buscar = "SELECT empleado_id FROM empleado " + condicion + " WHERE empleado_rol = '" + rol + "'";
        
        List<String> empleados = new ArrayList<String>();
        
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
            ResultSet rs = st.executeQuery();

            
            //System.out.println(sql_buscar);

            if(rs != null){
                
                while (rs.next()) {
                    System.out.println(rs.getRow());
                    System.out.println(rs.getString("empleado_id"));
                    empleados.add(rs.getString("empleado_id"));
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

        return empleados;
    }

    public String consultarTurno(String empleado_id) throws Exception {
        
        String mensaje = "";
        
        if(!this.existe(empleado_id)){
            mensaje = "El empleado no existe";
        }else{

            String sql_buscar = "SELECT turno_id FROM turno_asignado JOIN turno ON tasignado_turno = turno_id JOIN empleado ON empleado_id = tasignado_conductor JOIN conductor ON conductor_empleado_id = tasignado_conductor AND tasignado_conductor = '" + empleado_id + "'";
            try {
                this.conectar();
                PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    mensaje += "******"+ " Turno asignado para " + rs.getString("empleado_nombre") + " es: *********\n";
                    mensaje += "Día de Inicio: " + rs.getString("turno_dia_inicio") + "\n";
                    mensaje += "Día de Fin: " + rs.getString("turno_dia_fin") + "\n";
                    mensaje += "Hora de Inicio: " + rs.getString("turno_hora_inicio") + "\n";
                    mensaje += "Hora de Fin: " + rs.getString("turno_hora_fin") + "\n";
                    mensaje += "*****************************************************\n";
                }else{
                    mensaje = "El empleado no tiene turno asignado";
                }

                rs.close();
                st.close();

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }
        }

        return mensaje;
    }
    
    public String asignarTurno(String empleado_id, int turno_id) throws Exception {

        String mensaje = "";
        DAOTurnoImpl mi_turnito = new DAOTurnoImpl();

        if (!this.existe(empleado_id)) {
            mensaje = "Falló la asignación. El empleado no existe";
        }else if (!mi_turnito.existe(turno_id)) {
            mensaje = "Falló la asignación. El turno no existe";
        } else {

            String sql_buscar = "SELECT turno_id FROM turno_asignado JOIN turno ON tasignado_turno = turno_id JOIN empleado ON empleado_id = tasignado_conductor JOIN conductor ON conductor_empleado_id = tasignado_conductor AND tasignado_conductor = '" + empleado_id + "'";
            String sql_insertar = "INSERT INTO turno_asignado VALUES ('" + turno_id + "','" + empleado_id + "')";
            String sql_update = "UPDATE turno_asignado SET tasignado_turno = '" + turno_id + "' WHERE tasignado_conductor = '" + empleado_id + "'";

            try {
                this.conectar();
                PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
                ResultSet rs = st.executeQuery();
                
                Statement sn = this.conexion.createStatement();
                //System.out.println(sql_buscar);

                if (rs.next() && rs.getInt("turno_id") != turno_id) {//si tiene turno asignado se debe notificar que el turno actual se cambió
                    
                    sn.executeUpdate(sql_update);
                    mensaje = "Asignacion exitosa. \nEl conductor tenía el turno asignado con id = " + rs.getString("turno_id") + " ,"
                            + "se asignó el nuevo turno con id " + turno_id; 

                } else if(rs.next() && rs.getInt("turno_id") == turno_id) {//si no se asigna el turno sin baselina
                    mensaje = "El conductor ya tiene asignado el turno ingresado";
                } else {//si no se asigna el turno sin baselina
                    
                    sn.executeUpdate(sql_insertar);
                    mensaje = "Asignacion exitosa";
                }

                rs.close();
                st.close();

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }
        }

        return mensaje;
    }
    
    public String eliminarTurno(String empleado_id, int turno_id) throws Exception {

        String mensaje = "";
        DAOTurnoImpl mi_turnito = new DAOTurnoImpl();

        if (!this.existe(empleado_id)) {
            mensaje = "Falló la asignació. El empleado no existe";
        } else if (!mi_turnito.existe(turno_id)) {
            mensaje = "Falló la asignació. El turno no existe";
        } else {

            String sql_buscar = "SELECT turno_id FROM turno_asignado JOIN turno ON tasignado_turno = turno_id JOIN empleado ON empleado_id = tasignado_conductor JOIN conductor ON conductor_empleado_id = tasignado_conductor AND tasignado_conductor = '" + empleado_id + "'";
            String sql_delete = "DELETE FROM turno_asignado WHERE tasignado_conductor = '" + empleado_id + "' AND tasignado_turno = "
                    + "'" + turno_id + "'";
            
            //System.out.println(sql_buscar);

            try {
                this.conectar();
                PreparedStatement st = this.conexion.prepareStatement(sql_buscar);
                ResultSet rs = st.executeQuery();
                
                //System.out.println(rs);

                Statement sn = this.conexion.createStatement();

                if (rs.next()) {//si tiene turno asignado se borra

                    sn.executeUpdate(sql_delete);
                    mensaje = "Eliminación exitosa";

                } else {//si no, el se envía el mensaje de que falló
                    mensaje = "Falló la Eliminación, no existe asignación de turno para el conductor ingresado";
                }
                
                rs.close();
                st.close();

            } catch (Exception e) {
                throw e;
            } finally {
                this.cerrar();
            }
        }

        return mensaje;
    }
    
    @Override
    public boolean existe(String empleado_id) throws Exception {

        String existeBus = "SELECT * FROM empleado WHERE empleado_id = '" + empleado_id + "'";
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
    
    public String eliminarTipoEmpleado(String empleado_id) throws Exception{

        String[] sql_delete_empleado_tipo = new String[5];
        String sql_delete = "";
        
        Empleado mi_empleado = this.buscar(empleado_id);

        //conductor
        sql_delete_empleado_tipo[0] = "DELETE FROM conductor WHERE conductor_empleado_id = '" + empleado_id + "'";
        //auxiliar servicio al cliente
        sql_delete_empleado_tipo[1] = "DELETE FROM aux_serv_cliente WHERE aux_serv_cliente_empleado_id = '" + empleado_id + "'";
        //administrador
        sql_delete_empleado_tipo[2] = "DELETE FROM administrador WHERE administrador_empleado_id = '" + empleado_id + "'";
        //dir_opertativo
        sql_delete_empleado_tipo[3] = "DELETE FROM dir_operativo WHERE dir_operativo_empleado_id = '" + empleado_id + "'";
        //gerente
        sql_delete_empleado_tipo[4] = "DELETE FROM gerente WHERE gerente_empleado_id = '" + empleado_id + "'";
        
        
        for (int i = 1; i <= 5; i++) {

            if (mi_empleado.getEmpleadoRol() == i) {
                sql_delete = sql_delete_empleado_tipo[i-1];
            }
            
        }

        return sql_delete;
    }
    
    public boolean validaBus(String bus_id) throws Exception{
        
        String existeBus = "SELECT * FROM conductor WHERE conductor_bus = '" + bus_id + "'";
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
    
    public boolean validaEstacion(int estacion_id) throws Exception {

        String existeBus = "SELECT * FROM aux_serv_cliente WHERE aux_serv_cliente_estacion = '" + estacion_id + "'";
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
    
    public String insertaTipoEmpleado(String empleado_id, String placa_bus, int estacion_id, int rol) throws Exception {

        String[] sql_inserta_empleado_tipo = new String[5];
        String sql_insert = "";

        //conductor
        sql_inserta_empleado_tipo[0] = "INSERT INTO conductor VALUES('" + empleado_id + "','" + placa_bus + "')";
        //auxiliar servicio al cliente
        sql_inserta_empleado_tipo[1] = "INSERT INTO aux_serv_cliente VALUES('" + empleado_id + "','" + estacion_id + "')";
        //administrador
        sql_inserta_empleado_tipo[2] = "INSERT INTO administrador VALUES('" + empleado_id + "')";
        //director operativo
        sql_inserta_empleado_tipo[3] = "INSERT INTO dir_operativo VALUES('" + empleado_id + "')";
        //gerente
        sql_inserta_empleado_tipo[4] = "INSERT INTO gerente VALUES('" + empleado_id + "')";

        for (int i = 1; i <= 5; i++) {

            if (rol == i) {
                sql_insert = sql_inserta_empleado_tipo[i - 1];
            }

        }

        return sql_insert;
    }
    
    public String buscarTipoEmpleado(String empleado_id, int rol) throws Exception {

        String[] sql_busca_empleado_tipo = new String[5];
        String sql_busca = "";

        //conductor
        sql_busca_empleado_tipo[0] = "SELECT * FROM conductor WHERE conductor_empleado_id = '" + empleado_id + "'";
        //auxiliar servicio al cliente
        sql_busca_empleado_tipo[1] = "SELECT * FROM aux_serv_cliente WHERE aux_serv_cliente_empleado_id = '" + empleado_id + "'";
        //administrador
        sql_busca_empleado_tipo[2] = "SELECT * FROM administrador WHERE administrador_empleado_id = '" + empleado_id + "'";
        //director operativo
        sql_busca_empleado_tipo[3] = "SELECT * FROM dir_operativo WHERE dir_operativo_empleado_id = '" + empleado_id + "'";
        //gerente
        sql_busca_empleado_tipo[4] = "SELECT * FROM gerente WHERE gerente_empleado_id = '" + empleado_id + "'";

        for (int i = 1; i <= 5; i++) {

            if (rol == i) {
                sql_busca = sql_busca_empleado_tipo[i - 1];
            }

        }

        return sql_busca;
    }

    @Override
    public String eliminar(String empleado_a_eliminar) throws Exception {

        String mensaje = "";       


        if (!this.existe(empleado_a_eliminar)) {
            mensaje = "Falló la Eliminación. El bus que intenta eliminar no existe";
        } else {

            String sql_eliminar = "DELETE FROM empleado WHERE empleado_id = '" + empleado_a_eliminar + "'";

            try {

                this.conectar();
                try (Statement st = this.conexion.createStatement()) {
                    st.executeUpdate(this.eliminarTipoEmpleado(empleado_a_eliminar));
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
