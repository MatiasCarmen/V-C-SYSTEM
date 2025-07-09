package com.mycompany.vcsystems.modelo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterTecnicoRequest {
    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @Email(message = "Formato de correo inválido")
    @NotBlank(message = "El correo es requerido")
    private String correo;

    @NotBlank(message = "La contraseña es requerida")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, un número y un símbolo"
    )
    private String contrasena;
}
