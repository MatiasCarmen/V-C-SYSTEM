package com.mycompany.vcsystems;

import com.mycompany.vcsystems.modelo.service.IncidenciaService;
import com.mycompany.vcsystems.modelo.service.IncidenciaOperationResult;
import com.mycompany.vcsystems.modelo.repository.IncidenciaRepository;
import com.mycompany.vcsystems.modelo.repository.ClienteRepository;
import com.mycompany.vcsystems.modelo.service.UsuarioService;
import com.mycompany.vcsystems.modelo.service.NotificacionService;
import com.mycompany.vcsystems.modelo.entidades.Incidencia;
import com.mycompany.vcsystems.modelo.entidades.Usuario;
import com.mycompany.vcsystems.modelo.entidades.Cliente;
import com.mycompany.vcsystems.modelo.entidades.Usuario.Rol;
import com.mycompany.vcsystems.modelo.entidades.Incidencia.Estado;
import com.mycompany.vcsystems.modelo.dto.RegisterRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;
import org.mockito.Mock;

import java.util.Optional;
/**
 *
 * @author MatiasCarmen
 */
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VcsystemsApplication.class)
@Transactional
@Rollback
public class IncidenciaServiceTest {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Mock
    private NotificacionService notificacionService; // Mockear el servicio de notificaciones

    private Usuario clienteUser;
    private Cliente cliente;
    private Usuario tecnicoUser;

    @BeforeEach
    public void setup() {
        String securePassword = generateSecurePassword();

        RegisterRequest clienteRequest = new RegisterRequest();
        clienteRequest.setCorreo("cli@example.com");
        clienteRequest.setContrasena(securePassword);
        clienteRequest.setNombre("Cliente");
        clienteRequest.setNombreEmpresa("Empresa X");
        clienteRequest.setDireccionEmpresa("Dir X");
        clienteUser = usuarioService.registrarUsuario(clienteRequest, Usuario.Rol.CLIENTE);

        cliente = new Cliente();
        cliente.setUsuario(clienteUser);
        cliente.setNombreEmpresa("Empresa X");
        cliente.setDireccionEmpresa("Dir X");
        cliente = clienteRepository.save(cliente);

        RegisterRequest tecnicoRequest = new RegisterRequest();
        tecnicoRequest.setCorreo("tec@example.com");
        tecnicoRequest.setContrasena(generateSecurePassword());
        tecnicoRequest.setNombre("Tecnico");
        tecnicoRequest.setNombreEmpresa("Empresa T");
        tecnicoRequest.setDireccionEmpresa("Dir T");
        tecnicoUser = usuarioService.registrarUsuario(tecnicoRequest, Usuario.Rol.TECNICO);
    }

    @Test
    public void testCrearAsignarYCambiarEstado() {
        Incidencia inc = new Incidencia();
        inc.setCliente(cliente);
        inc.setDescripcion("Desc");
        inc.setEstado(Estado.PENDIENTE);

        Incidencia saved = incidenciaService.crearIncidencia(inc);
        assertNotNull(saved.getIdIncidencia(), "La incidencia no se guardó correctamente.");

        IncidenciaOperationResult assignResult = incidenciaService.asignarTecnico(saved.getIdIncidencia(), tecnicoUser);
        assertTrue(assignResult.isSuccess(), "No se pudo asignar el técnico.");
        assertNotNull(assignResult.getIncidencia().getTecnico(), "El técnico asignado es nulo.");
        assertEquals(tecnicoUser.getIdUsuario(), assignResult.getIncidencia().getTecnico().getIdUsuario(), "El técnico asignado no es el esperado.");

        IncidenciaOperationResult stateResult = incidenciaService.cambiarEstado(saved.getIdIncidencia(), Estado.RESUELTA);
        assertTrue(stateResult.isSuccess(), "No se pudo cambiar el estado de la incidencia.");
        assertEquals(Estado.RESUELTA, stateResult.getIncidencia().getEstado(), "El estado de la incidencia no se cambió correctamente.");
    }

    private String generateSecurePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        StringBuilder password = new StringBuilder();
        password.append("A"); // Mayúscula
        password.append("a"); // Minúscula
        password.append("1"); // Número
        password.append("!"); // Símbolo

        java.util.Random random = new java.util.Random();
        for (int i = 4; i < 12; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        return shuffleString(password.toString());
    }

    private String shuffleString(String input) {
        java.util.List<Character> characters = new java.util.ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        java.util.Collections.shuffle(characters);

        StringBuilder result = new StringBuilder();
        for (char c : characters) {
            result.append(c);
        }
        return result.toString();
    }
}
