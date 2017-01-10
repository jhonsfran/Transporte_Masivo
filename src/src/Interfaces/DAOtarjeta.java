/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Mapeo.Tarjeta;

/**
 *
 * @author Univalle_F
 */
public interface DAOtarjeta {
    
    public Tarjeta buscar(int tarjetaPin) throws Exception;
    public String modificar(int tarjetaPin, String usuario_id, String usuario_id_old,  String tarjetaSaldo, boolean tarjetaEstado) throws Exception;
    public String eliminar(int tarjetaPin) throws Exception;
    public boolean existe(int tarjetaPin) throws Exception;
}
