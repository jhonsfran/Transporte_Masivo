package Mapeo;
// Generated 17/12/2016 12:40:10 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private String usuarioId;
     private String usuarioNombre;
     private String usuarioTelefono;
     private Set<Pqrs> pqrses = new HashSet<Pqrs>(0);
     private Set<Tarjeta> tarjetas = new HashSet<Tarjeta>(0);

    public Usuario() {
    }

	
    public Usuario(String usuarioId, String usuarioNombre, String usuarioTelefono) {
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.usuarioTelefono = usuarioTelefono;
    }
    public Usuario(String usuarioId, String usuarioNombre, String usuarioTelefono, Set<Pqrs> pqrses, Set<Tarjeta> tarjetas) {
       this.usuarioId = usuarioId;
       this.usuarioNombre = usuarioNombre;
       this.usuarioTelefono = usuarioTelefono;
       this.pqrses = pqrses;
       this.tarjetas = tarjetas;
    }
   
    public String getUsuarioId() {
        return this.usuarioId;
    }
    
    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getUsuarioNombre() {
        return this.usuarioNombre;
    }
    
    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }
    public String getUsuarioTelefono() {
        return this.usuarioTelefono;
    }
    
    public void setUsuarioTelefono(String usuarioTelefono) {
        this.usuarioTelefono = usuarioTelefono;
    }
    public Set<Pqrs> getPqrses() {
        return this.pqrses;
    }
    
    public void setPqrses(Set<Pqrs> pqrses) {
        this.pqrses = pqrses;
    }
    public Set<Tarjeta> getTarjetas() {
        return this.tarjetas;
    }
    
    public void setTarjetas(Set<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }




}


