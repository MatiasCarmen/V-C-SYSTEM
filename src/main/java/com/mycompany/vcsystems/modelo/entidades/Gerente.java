package com.mycompany.vcsystems.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "gerentes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Gerente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gerente")
    private Long idGerente;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "area_asignada")
    private String areaAsignada;

    @Column(name = "nivel_acceso")
    private String nivelAcceso;

    @CreatedDate
    @Column(name = "creado_at", updatable = false)
    private LocalDateTime creadoAt;

    @LastModifiedDate
    @Column(name = "actualizado_at")
    private LocalDateTime actualizadoAt;
}