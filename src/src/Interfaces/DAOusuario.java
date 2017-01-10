/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Mapeo.Usuario;
import java.util.List;

public interface DAOusuario {
    public String registrar(String usuario_id, String usuario_nombre, String usuario_telefono) throws Exception;
    public String modificar(String usuario_id_old, String usuario_id, String usuario_nombre, String usuario_telefono) throws Exception;
    public String eliminar(String usuario_id) throws Exception;
    public Usuario buscar(String usuario_id) throws Exception;
    public boolean existe(String usuario_id) throws Exception;
}