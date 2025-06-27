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
@Table(name = "solicitudes_repuesto")
public class SolicitudRepuesto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long id_solicitud;

    @Column(name = "id_incidencia")
    private Long id_incidencia;

    @Column(name = "id_proveedor")
    private Long id_proveedor;

    @Column(name = "descripcion_repuesto")
    private String descripcion_repuesto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "fecha_solicitud")
    private LocalDate fecha_solicitud;

    @Column(name = "estado")
    private String estado ;

    public SolicitudRepuesto() {
    }

    public SolicitudRepuesto(Long id_incidencia, Long id_proveedor, String descripcion_repuesto, Integer cantidad, LocalDate fecha_solicitud, String estado) {
        this.id_incidencia = id_incidencia;
        this.id_proveedor = id_proveedor;
        this.descripcion_repuesto = descripcion_repuesto;
        this.cantidad = cantidad;
        this.fecha_solicitud = fecha_solicitud;
        this.estado = estado;
    }

    public Long getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(Long id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public Long getId_incidencia() {
        return id_incidencia;
    }

    public void setId_incidencia(Long id_incidencia) {
        this.id_incidencia = id_incidencia;
    }

    public Long getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Long id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getDescripcion_repuesto() {
        return descripcion_repuesto;
    }

    public void setDescripcion_repuesto(String descripcion_repuesto) {
        this.descripcion_repuesto = descripcion_repuesto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(LocalDate fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}