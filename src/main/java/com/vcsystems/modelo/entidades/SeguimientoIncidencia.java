/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vcsystems.modelo.entidades;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author mathi
 */
@Entity
@Table(name = "seguimiento_incidencias")
public class SeguimientoIncidencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguimiento")
    private Long id_seguimiento;

    @Column(name = "id_incidencia")
    private Long id_incidencia;
    
    @Column(name = "fecha")
    private LocalDate fecha ;
    
    @Column(name = "estado")
    private String estado;
    
     @Column(name = "observacion")
    private String observacion;
    
    
    public SeguimientoIncidencia() {
    }
    public SeguimientoIncidencia(Long id_seguimiento,Long id_incidencia,LocalDate fecha,String estado,String observacion) {
        this.id_seguimiento =id_seguimiento;
        this.id_incidencia = id_incidencia;
        this.fecha=fecha;
        this.estado= estado;
        this.observacion=observacion;

    }
    
    public Long getId_seguimiento() {
        return id_seguimiento;
    }

    public void setId_seguimiento(Long id_seguimiento) {
        this.id_seguimiento= id_seguimiento;
    }
    
    
    public  Long getId_incidencia() {
        return id_incidencia;
    }

    public void setId_incidencia(Long id_incidencia) {
        this.id_incidencia = id_incidencia;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this. estado= estado;
    }
    
       public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this. observacion= observacion;
    }
    
    
    
}