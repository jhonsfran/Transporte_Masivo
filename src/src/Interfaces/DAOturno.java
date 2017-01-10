/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Mapeo.Turno;
import java.util.List;

/**
 *
 * @author Univalle_F
 */
public interface DAOturno {
    public String registrar(String dia_inicio, String dia_fin, String hora_inicio, String hora_fin) throws Exception;

    public String modificar(int turno_id, String dia_inicio, String dia_fin, String hora_inicio, String hora_fin) throws Exception;

    public String eliminar(int turno_id) throws Exception;
    
    public Turno buscar(int turno_id) throws Exception;

    public boolean existe(int turno_id) throws Exception;
}
