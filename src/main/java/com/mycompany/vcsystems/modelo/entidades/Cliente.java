package com.mycompany.vcsystems.modelo.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data // <-- ¡La magia de Lombok! Crea getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    // --- Relación con Usuario ---
    // Un Cliente está asociado a una única cuenta de Usuario.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    // --- Campos que vienen del formulario de registro ---
    @Column(name = "nombre_completo", nullable = false)
    private String nombre;

    @Column(name = "nombre_empresa", nullable = false)
    private String nombreEmpresa;

    @Column(name = "direccion_empresa", nullable = false)
    private String direccionEmpresa;

    // Puedes agregar más campos aquí si los necesitas en el futuro
    // Ejemplo:
    // @Column(name = "telefono")
    // private String telefono;
}