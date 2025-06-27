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
@Table(name = "tecnicos")
public class Tecnico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tecnico")
    private Long id_tecnico;

    @Column(name = "id_usuario")
    private Long id_usuario;

    @Column(name = "especialidad")
    private String especialidad;

    public Tecnico() {
    }

    public Tecnico(Long id_usuario, String especialidad) {
        this.id_usuario = id_usuario;
        this.especialidad = especialidad;
    }

    public Long getId_tecnico() {
        return id_tecnico;
    }

    public void setIdTecnico(Long id_tecnico) {
        this.id_tecnico = id_tecnico;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}