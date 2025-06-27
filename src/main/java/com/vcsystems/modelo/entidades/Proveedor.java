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
@Table(name = "proveedores")
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long id_proveedor;

    @Column(name = "nombre_empresa")
    private String nombre_empresa;
    
    @Column(name = "telefono")
    private Long telefono;
    
    
    @Column(name = "correo")
    private String correo;
    
    
    public Proveedor() {
    }
    public Proveedor(Long id_proveedor,String nombre_empresa,Long telefono, String correo) {
        this.id_proveedor =id_proveedor;
        this.nombre_empresa = nombre_empresa;
        this.telefono=telefono;
        this.correo= correo;
       
    }
    
    public Long getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Long id_proveedor) {
        this.id_proveedor= id_proveedor;
    }
    
    
    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }
    
    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this. correo= correo;
    }
    
    
    
}