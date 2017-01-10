/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Mapeo.Ruta;
import java.util.List;

/**
 *
 * @author Univalle_F
 */
public interface DAOruta {
    public String registrar(String ruta_id, String ruta_nombre, String ruta_descrip) throws Exception;
    public String modificar(String ruta_old, String ruta_id, String ruta_nombre, String ruta_descrip) throws Exception;
    public String eliminar(String ruta_id) throws Exception;
    public Ruta buscar(String id) throws Exception;
    public List<String> listar() throws Exception;
    public boolean existe(String ruta_id) throws Exception;
}
 