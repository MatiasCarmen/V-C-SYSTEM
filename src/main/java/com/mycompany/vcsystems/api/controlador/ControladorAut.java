package com.mycompany.vcsystems.api.controlador;

import com.mycompany.vcsystems.modelo.dto.RegisterRequest;
import com.mycompany.vcsystems.modelo.entidades.Usuario;
import com.mycompany.vcsystems.modelo.entidades.Usuario.Rol;
import com.mycompany.vcsystems.modelo.service.UsuarioService;
import com.mycompany.vcsystems.modelo.service.JwtUtil;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8081") // Asegúrate que el puerto es el de tu frontend (Vue, React, etc.)
@Slf4j
public class ControladorAut {

    private final UsuarioService usuarioService;

    public ControladorAut(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para autenticar usuarios (login).
     * @param request DTO con correo y contraseña.
     * @return Respuesta con datos del usuario y URL de redirección si es exitoso, o un error en caso contrario.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            return usuarioService.autenticarUsuario(request.getCorreo(), request.getContrasena())
                    .map(user -> {
                        Map<String, Object> response = new HashMap<>();
                        response.put("success", true);

                        Map<String, Object> usuario = new HashMap<>();
                        usuario.put("idUsuario", user.getIdUsuario());
                        usuario.put("nombre", user.getNombre() != null ? user.getNombre() : user.getCorreo());
                        usuario.put("correo", user.getCorreo());
                        usuario.put("rol", user.getRol().toString());
                        response.put("usuario", usuario);

                        // Generar token JWT
                        String token = JwtUtil.generateToken(user.getIdUsuario(), user.getCorreo(), user.getRol().toString());
                        response.put("token", token);

                        response.put("redirect", switch (user.getRol()) {
                            case GERENTE -> "/pages/gerente.html";
                            case TECNICO -> "/pages/tecnico.html";
                            case CLIENTE -> "/pages/cliente.html";
                            default -> throw new IllegalStateException("Rol de usuario no reconocido para redirección: " + user.getRol());
                        });
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(Map.of("success", false, "error", "Credenciales inválidas")));
        } catch (Exception e) {
            log.error("Error interno durante el login para usuario: {}", request.getCorreo(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "error", "Error interno del servidor"));
        }
    }

    // --- REGISTRO DE USUARIOS ---

    /**
     * Endpoint público para el auto-registro de CLIENTES.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        log.info("Intentando registrar un nuevo CLIENTE con correo: {}", request.getCorreo());
        return registerNewUser(request, Rol.CLIENTE);
    }

    /**
     * Endpoint protegido para que un GERENTE registre un nuevo TECNICO.
     */
    @PostMapping("/register/tecnico")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<?> registerTecnico(@Valid @RequestBody RegisterRequest request) {
        log.info("Un GERENTE está registrando un nuevo TECNICO con correo: {}", request.getCorreo());
        return registerNewUser(request, Rol.TECNICO);
    }

    /**
     * Endpoint protegido para que un GERENTE registre un nuevo GERENTE.
     */
    @PostMapping("/register/gerente")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<?> registerGerente(@Valid @RequestBody RegisterRequest request) {
        log.info("Un GERENTE está registrando un nuevo GERENTE con correo: {}", request.getCorreo());
        return registerNewUser(request, Rol.GERENTE);
    }

    /**
     * Lógica centralizada para crear un nuevo usuario con un rol específico.
     * Es llamado por los endpoints de registro públicos y protegidos.
     * @param request DTO con los datos del usuario a registrar.
     * @param rol El rol que se le asignará al nuevo usuario.
     * @return Una respuesta HTTP con el resultado de la operación.
     */
    private ResponseEntity<?> registerNewUser(RegisterRequest request, Rol rol) {
        try {
            Usuario usuarioRegistrado = usuarioService.registrarUsuario(request, rol);
            log.info("Usuario con rol {} registrado exitosamente: {}", rol, usuarioRegistrado.getCorreo());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Usuario registrado exitosamente como " + rol.name());
            response.put("usuarioId", usuarioRegistrado.getIdUsuario());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (ValidationException e) {
            log.warn("Error de validación al registrar {} con correo {}: {}", rol, request.getCorreo(), e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            log.warn("Intento de registro con correo duplicado para rol {}: {}", rol, request.getCorreo());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("success", false, "error", "El correo electrónico ya está en uso."));
        } catch (Exception e) {
            log.error("Error interno no esperado al registrar {} con correo {}: ", rol, request.getCorreo(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "error", "Ocurrió un error inesperado en el servidor."));
        }
    }

    // --- DTO interno para la petición de login ---
    @Data
    static class LoginRequest {
        @Email(message = "El formato del correo electrónico no es válido.")
        @NotBlank(message = "El correo es un campo obligatorio.")
        private String correo;

        @NotBlank(message = "La contraseña es un campo obligatorio.")
        private String contrasena;
    }
}