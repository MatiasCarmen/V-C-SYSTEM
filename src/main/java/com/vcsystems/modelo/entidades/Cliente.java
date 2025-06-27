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
@Table(name = "clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id_cliente;

    @Column(name = "id_usuario")
    private Long id_usuario;
    
    @Column(name = "nombre_empresa")
    private String nombre_empresa;
    
     @Column(name = "direccion_empresa")
    private String direccion_empresa;

    public Cliente() {
    }
    public Cliente( Long id_cliente, Long id_usuario, String nombre_empresa, String direccion_empresa) {
        this.id_cliente = id_cliente;
        this.id_usuario = id_usuario;
        this.nombre_empresa= nombre_empresa;
        this.direccion_empresa=direccion_empresa;
    }
  
    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario= id_usuario;
    }
    
      public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa= nombre_empresa;
    }
    
      public String getDireccion_empresa() {
        return direccion_empresa;
    }

    public void setDireccion_empresa(String direccion_empresa) {
        this.direccion_empresa= direccion_empresa;
    }
   

}
   