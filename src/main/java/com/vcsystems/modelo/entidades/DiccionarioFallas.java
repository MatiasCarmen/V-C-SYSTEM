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

/**
 *
 * @author mathi
 */
@Entity
@Table(name = "diccionario_fallas")
public class DiccionarioFallas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_falla")
    private Long id_falla;

    @Column(name = "codigo_falla")
    private Long codigo_falla;
    
    @Column(name = "descripcion")
    private String descripcion;
    
 

    public DiccionarioFallas() {
    }
    public DiccionarioFallas( Long id_falla, Long codigo_falla,String descripcion) {
        this.id_falla = id_falla;
        this.codigo_falla = id_falla;
        this.descripcion= descripcion;
    }
   
    public Long getId_falla() {
        return id_falla;
    }

    public void setId_falla(Long id_falla) {
        this.id_falla = id_falla;
    }

    public Long getCodigo_falla() {
        return codigo_falla;
    }

    public void setCodigo_falla(Long codigo_falla) {
        this.codigo_falla=codigo_falla;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion =descripcion;
    }
}
   