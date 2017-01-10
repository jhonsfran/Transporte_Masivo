
package Interfaces;

import Mapeo.Estacion;
import java.util.List;

public interface DAOEstacion {
    public String registrar(String estacion_nombre, String estacion_ubicacion, String estacion_admin) throws Exception;
    public String modificar(int estacion_id, String estacion_nombre, String estacion_ubicacion, String estacion_admin) throws Exception;
    public String eliminar(int estacion_id) throws Exception;
    public Estacion buscar(int estacion_id) throws Exception;
    public boolean existe(int estacion_id) throws Exception;
}
