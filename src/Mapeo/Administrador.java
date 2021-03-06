package Mapeo;
// Generated 17/12/2016 12:40:10 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Administrador generated by hbm2java
 */
public class Administrador  implements java.io.Serializable {


     private String administradorEmpleadoId;
     private Empleado empleado;
     private Set<Pqrs> pqrses = new HashSet<Pqrs>(0);

    public Administrador() {
    }

	
    public Administrador(Empleado empleado) {
        this.empleado = empleado;
    }
    public Administrador(Empleado empleado, Set<Pqrs> pqrses) {
       this.empleado = empleado;
       this.pqrses = pqrses;
    }
   
    public String getAdministradorEmpleadoId() {
        return this.administradorEmpleadoId;
    }
    
    public void setAdministradorEmpleadoId(String administradorEmpleadoId) {
        this.administradorEmpleadoId = administradorEmpleadoId;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Set<Pqrs> getPqrses() {
        return this.pqrses;
    }
    
    public void setPqrses(Set<Pqrs> pqrses) {
        this.pqrses = pqrses;
    }




}


