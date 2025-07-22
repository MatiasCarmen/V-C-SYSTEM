package com.mycompany.vcsystems.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tecnico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTecnico;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    private String especialidad;
    private String telefono;

    @Column(name = "creado_at", nullable = false, updatable = false)
    private LocalDateTime creadoAt;

    @Column(name = "actualizado_at", nullable = false)
    private LocalDateTime actualizadoAt;

    @PrePersist
    public void prePersist() {
        this.creadoAt = LocalDateTime.now();
        this.actualizadoAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.actualizadoAt = LocalDateTime.now();
    }
}