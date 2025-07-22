package com.mycompany.vcsystems.modelo.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tecnicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tecnico")
    private Long idTecnico;

    @NotBlank(message = "La especialidad es obligatorio")
    private String especialidad;

    @NotBlank(message = "El disponibilidad es obligatorio")
    private String disponibilidad;

    @CreatedDate
    @Column(name = "creado_at", updatable = false)
    private LocalDateTime creadoAt;

    @LastModifiedDate
    @Column(name = "actualizado_at")
    private LocalDateTime actualizadoAt;
}
