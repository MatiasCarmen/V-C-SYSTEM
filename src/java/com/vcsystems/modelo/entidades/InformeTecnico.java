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
@Table(name = "informes_tecnicos")
public class InformeTecnico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informe")
    private Long id_informe;

    @Column(name = "id_incidencia")
    private Long id_incidencia;
    
    @Column(name = "id_tecnico")
    private Long id_tecnico;
    
    
    @Column(name = "resumen")
    private String resumen;
    
    @Column(name = "fecha_informe")
    private LocalDate fecha_informe ;
   
  

    public InformeTecnico() {
    }
    public InformeTecnico(Long id_informe,Long id_incidencia,Long id_tecnico, String resumen, LocalDate fecha_informe) {
        this.id_informe =id_informe;
        this.id_incidencia = id_incidencia;
        this.id_tecnico=id_tecnico;
        this.resumen= resumen;
        this.fecha_informe=fecha_informe;
       
    }
    
    public Long getId_informe() {
        return id_informe;
    }

    public void setId_informe(Long id_informe) {
        this.id_informe= id_informe;
    }
    
    
    public Long getId_incidencia() {
        return id_incidencia;
    }

    public void setId_incidencia(Long id_incidencia) {
        this.id_incidencia = id_incidencia;
    }
    
    public Long getId_tecnico() {
        return id_tecnico;
    }

    public void setId_tecnico(Long id_tecnico) {
        this.id_tecnico = id_tecnico;
    }
    
    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this. resumen = resumen;
    }
   
    
    public LocalDate getFecha_informe() {
        return fecha_informe;
    }

    public void setFecha_informe(LocalDate Fecha_informe) {
        this.fecha_informe =fecha_informe;
    }
    
    
    
}