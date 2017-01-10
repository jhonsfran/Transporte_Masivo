/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Mapeo.Empleado;
import java.util.List;

/**
 *
 * @author Univalle_F
 */
public interface DAOempleado {
    public String registrar(String empleado_id, String empleado_nombre, String empleado_telefono, String empleado_sueldo, int tipo, String placa_bus, int estacion_id) throws Exception;
    public String modificar(String empleado_id_old ,String empleado_id, String empleado_nombre, String empleado_telefono, String empleado_sueldo, int tipo, String placa_bus, int estacion_id) throws Exception;
    public String eliminar(String empleado_id) throws Exception;
    //public List<Empleado> listar() throws Exception;
    public Empleado buscar(String empleado_id) throws Exception;
    public boolean existe(String empleado_id) throws Exception;
}
