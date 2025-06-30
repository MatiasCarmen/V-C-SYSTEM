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
@Table(name = "incidencias")
public class Incidencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidencia")
    private Long id_incidencia;

    @Column(name = "id_cliente")
    private Long id_cliente;
    
    @Column(name = "id_tecnico")
    private Long id_tecnico;
    
    @Column(name = "id_falla")
    private Long id_falla;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "fecha_reporte")
    private LocalDate fecha_reporte ;
    
    @Column(name = "estado")
    private String estado;
    
    
 

    public Incidencia() {
    }
    public Incidencia( Long id_incidencia,Long id_cliente,Long id_tecnico,Long id_falla, String descripcion, LocalDate fecha_reporte, String estado) {
        this.id_incidencia = id_incidencia;
        this.id_cliente = id_cliente;
        this.id_tecnico=id_tecnico;
        this.id_falla=id_falla;
        this.descripcion= descripcion;
        this.fecha_reporte=fecha_reporte;
        this.estado=estado;
    }
    
    public Long getId_incidencia() {
        return id_incidencia;
    }

    public void setId_incidencia(Long id_incidencia) {
        this.id_incidencia = id_incidencia;
    }
    
    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    public Long getId_tecnico() {
        return id_tecnico;
    }

    public void setId_tecnico(Long id_tecnico) {
        this.id_tecnico = id_tecnico;
    }
    
    public Long getId_falla() {
        return id_falla;
    }

    public void setId_falla(Long id_falla) {
        this.id_falla = id_falla;
    }
   
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion =descripcion;
    }
    
    public LocalDate getFecha_reporte() {
        return fecha_reporte;
    }

    public void setFecha_reporte(LocalDate fecha_reporte) {
        this.fecha_reporte = fecha_reporte;
    }
    
     public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado =estado;
    }
    
    
}
   