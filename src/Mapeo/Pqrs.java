package Mapeo;
// Generated 17/12/2016 12:40:10 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Pqrs generated by hbm2java
 */
public class Pqrs  implements java.io.Serializable {


     private int pqrsTicket;
     private Administrador administrador;
     private Estacion estacion;
     private Usuario usuario;
     private String pqrsMotivo;
     private String pqrsDescrip;
     private Date pqrsFechaInicio;
     private String pqrsEstado;
     private Date pqrsFechaResuelta;
     private String pqrsRespuesta;
     private Set<PqrsMedida> pqrsMedidas = new HashSet<PqrsMedida>(0);

    public Pqrs() {
    }

	
    public Pqrs(int pqrsTicket, Estacion estacion, Usuario usuario, String pqrsMotivo, String pqrsDescrip, Date pqrsFechaInicio, String pqrsEstado) {
        this.pqrsTicket = pqrsTicket;
        this.estacion = estacion;
        this.usuario = usuario;
        this.pqrsMotivo = pqrsMotivo;
        this.pqrsDescrip = pqrsDescrip;
        this.pqrsFechaInicio = pqrsFechaInicio;
        this.pqrsEstado = pqrsEstado;
    }
    public Pqrs(int pqrsTicket, Administrador administrador, Estacion estacion, Usuario usuario, String pqrsMotivo, String pqrsDescrip, Date pqrsFechaInicio, String pqrsEstado, Date pqrsFechaResuelta, String pqrsRespuesta, Set<PqrsMedida> pqrsMedidas) {
       this.pqrsTicket = pqrsTicket;
       this.administrador = administrador;
       this.estacion = estacion;
       this.usuario = usuario;
       this.pqrsMotivo = pqrsMotivo;
       this.pqrsDescrip = pqrsDescrip;
       this.pqrsFechaInicio = pqrsFechaInicio;
       this.pqrsEstado = pqrsEstado;
       this.pqrsFechaResuelta = pqrsFechaResuelta;
       this.pqrsRespuesta = pqrsRespuesta;
       this.pqrsMedidas = pqrsMedidas;
    }
   
    public int getPqrsTicket() {
        return this.pqrsTicket;
    }
    
    public void setPqrsTicket(int pqrsTicket) {
        this.pqrsTicket = pqrsTicket;
    }
    public Administrador getAdministrador() {
        return this.administrador;
    }
    
    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
    public Estacion getEstacion() {
        return this.estacion;
    }
    
    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getPqrsMotivo() {
        return this.pqrsMotivo;
    }
    
    public void setPqrsMotivo(String pqrsMotivo) {
        this.pqrsMotivo = pqrsMotivo;
    }
    public String getPqrsDescrip() {
        return this.pqrsDescrip;
    }
    
    public void setPqrsDescrip(String pqrsDescrip) {
        this.pqrsDescrip = pqrsDescrip;
    }
    public Date getPqrsFechaInicio() {
        return this.pqrsFechaInicio;
    }
    
    public void setPqrsFechaInicio(Date pqrsFechaInicio) {
        this.pqrsFechaInicio = pqrsFechaInicio;
    }
    public String getPqrsEstado() {
        return this.pqrsEstado;
    }
    
    public void setPqrsEstado(String pqrsEstado) {
        this.pqrsEstado = pqrsEstado;
    }
    public Date getPqrsFechaResuelta() {
        return this.pqrsFechaResuelta;
    }
    
    public void setPqrsFechaResuelta(Date pqrsFechaResuelta) {
        this.pqrsFechaResuelta = pqrsFechaResuelta;
    }
    public String getPqrsRespuesta() {
        return this.pqrsRespuesta;
    }
    
    public void setPqrsRespuesta(String pqrsRespuesta) {
        this.pqrsRespuesta = pqrsRespuesta;
    }
    public Set<PqrsMedida> getPqrsMedidas() {
        return this.pqrsMedidas;
    }
    
    public void setPqrsMedidas(Set<PqrsMedida> pqrsMedidas) {
        this.pqrsMedidas = pqrsMedidas;
    }




}

