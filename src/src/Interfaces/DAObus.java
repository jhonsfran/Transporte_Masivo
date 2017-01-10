
package Interfaces;

import Mapeo.Bus;
import java.util.List;

public interface DAObus {
    public String registrar(String placa, int tipo_bus, String ruta_id) throws Exception;
    public String modificar(String bus_a_modificar ,String placa_bus_update, int tipo_bus_update, String ruta_id_bus_update) throws Exception;
    public String eliminar(String bus_a_eliminar) throws Exception;
    public List<Bus> listar() throws Exception;
    public Bus buscar(String id) throws Exception;
    public boolean existe(String placa) throws Exception;
}
