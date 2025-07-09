package com.mycompany.vcsystems.modelo.service;

import com.mycompany.vcsystems.modelo.dto.RegisterRequest;
import com.mycompany.vcsystems.modelo.entidades.Usuario;
import com.mycompany.vcsystems.modelo.repository.UsuarioRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional // ¡Excelente! Esto asegura que toda la operación sea atómica.
@Slf4j
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClienteService clienteService;
    // Inyectamos los otros servicios para tener la estructura completa
    // private final TecnicoService tecnicoService;
    // private final GerenteService gerenteService;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-zA-Z]).{8,}$");

    /**
     * Orquesta el registro completo de un nuevo usuario y su rol específico.
     *
     * @param request DTO con los datos de registro.
     * @param rol El rol a asignar al nuevo usuario.
     * @return El Usuario guardado en la base de datos.
     */
    public Usuario registrarUsuario(RegisterRequest request, Usuario.Rol rol) {
        // 1. Validar la contraseña
        validarContrasena(request.getContrasena());

        // 2. Crear la entidad Usuario genérica
        Usuario usuario = new Usuario();
        // El nombre en la entidad Usuario puede ser útil para logs o saludos genéricos
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(request.getContrasena()));
        usuario.setRol(rol);

        // 3. Guardar el usuario para obtener su ID y vincularlo
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        log.info("Entidad Usuario base creada con correo: {}", usuarioGuardado.getCorreo());

        // 4. Crear la entidad específica según el rol
        switch (rol) {
            case CLIENTE:
                // ¡AQUÍ ESTÁ LA CORRECCIÓN!
                // Llamamos al método correcto en ClienteService, pasando el request completo.
                clienteService.crearNuevoCliente(usuarioGuardado, request);
                log.info("Entidad Cliente vinculada para el usuario: {}", usuarioGuardado.getCorreo());
                break;
            case TECNICO:
                // Aquí iría la lógica para crear un técnico
                // tecnicoService.crearNuevoTecnico(usuarioGuardado, request);
                log.info("Entidad Tecnico vinculada para el usuario: {}", usuarioGuardado.getCorreo());
                break;
            case GERENTE:
                // Aquí iría la lógica para crear un gerente
                // gerenteService.crearNuevoGerente(usuarioGuardado, request);
                log.info("Entidad Gerente vinculada para el usuario: {}", usuarioGuardado.getCorreo());
                break;
            default:
                // Es una buena práctica manejar casos inesperados
                throw new IllegalStateException("Rol de usuario no soportado para registro: " + rol);
        }

        return usuarioGuardado;
    }

    /**
     * Valida el formato de la contraseña.
     * @param password La contraseña a validar.
     * @throws ValidationException si la contraseña no cumple con el formato requerido.
     */
    private void validarContrasena(String password) {
        if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
            throw new ValidationException("La contraseña debe tener al menos 8 caracteres, " +
                    "incluir un número y un símbolo (!@#$%^&*)");
        }
    }

    /**
     * Autentica a un usuario para el endpoint de login.
     * @param correo El correo del usuario.
     * @param rawPassword La contraseña sin encriptar.
     * @return Un Optional con el Usuario si la autenticación es exitosa.
     */
    public Optional<Usuario> autenticarUsuario(String correo, String rawPassword) {
        log.info("Intentando autenticación para el usuario: {}", correo);

        Optional<Usuario> userOpt = usuarioRepository.findByCorreo(correo);
        if (userOpt.isEmpty()) {
            log.warn("Intento de autenticación fallido. Usuario no encontrado: {}", correo);
            return Optional.empty();
        }

        boolean passwordMatches = passwordEncoder.matches(rawPassword, userOpt.get().getContrasena());
        log.info("Resultado de la comparación de contraseñas para {}: {}", correo, passwordMatches);

        if (passwordMatches) {
            log.info("Autenticación exitosa para el usuario: {}", correo);
            return userOpt;
        } else {
            log.warn("Intento de autenticación fallido. Contraseña incorrecta para: {}", correo);
            return Optional.empty();
        }
    }

    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    /**
     * Método principal para la integración con Spring Security.
     */
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + correo));

        return new User(
                usuario.getCorreo(),
                usuario.getContrasena(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name()))
        );
    }
}