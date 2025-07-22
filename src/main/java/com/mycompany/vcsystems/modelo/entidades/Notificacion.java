package com.mycompany.vcsystems.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private String destinatario; // puede ser correo o ID

    private boolean leido = false;

    private LocalDateTime fechaEnvio = LocalDateTime.now();
}
