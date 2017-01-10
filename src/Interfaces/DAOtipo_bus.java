/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Mapeo.TipoBus;
import java.util.List;

/**
 *
 * @author Univalle_F
 */
public interface DAOtipo_bus {
    /*public void registrar(TipoBus tp) throws Exception;
    public void modificar(int id, TipoBus tp) throws Exception;
    public void eliminar(TipoBus tp) throws Exception;
    public List<TipoBus> listar() throws Exception;*/
    public TipoBus buscar(int id) throws Exception;
    public boolean existe(int id) throws Exception;
}
