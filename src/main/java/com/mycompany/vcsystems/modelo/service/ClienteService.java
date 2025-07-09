package com.mycompany.vcsystems.modelo.service;

import com.mycompany.vcsystems.modelo.dto.RegisterRequest;
import com.mycompany.vcsystems.modelo.entidades.Cliente;
import com.mycompany.vcsystems.modelo.entidades.Usuario;
import com.mycompany.vcsystems.modelo.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Crea la entidad específica del Cliente y la asocia con un Usuario ya creado.
     * Este método es el que debe ser llamado por UsuarioService durante el registro de un cliente.
     *
     * @param usuario El objeto Usuario que ya fue creado y guardado.
     * @param request El DTO con todos los datos del formulario de registro.
     */
    @Transactional
    public void crearNuevoCliente(Usuario usuario, RegisterRequest request) {
        // NOTA: Asegúrate de que tu entidad 'Cliente' y tu DTO 'RegisterRequest'
        // tengan los campos y métodos get/set para:
        // nombre, nombreEmpresa, direccionEmpresa.

        Cliente nuevoCliente = new Cliente();

        // 1. Vinculamos este cliente con su cuenta de usuario correspondiente.
        nuevoCliente.setUsuario(usuario);

        // 2. Poblamos los datos del cliente desde el objeto request, usando los campos del formulario.
        // El campo 'nombre' en el form es "Nombre completo".
        nuevoCliente.setNombre(request.getNombre());

        // El campo 'nombreEmpresa' en el form es "Nombre de la Empresa".
        nuevoCliente.setNombreEmpresa(request.getNombreEmpresa());

        // El campo 'direccionEmpresa' en el form es "Dirección de la Empresa".
        nuevoCliente.setDireccionEmpresa(request.getDireccionEmpresa());

        // 3. Guardamos la entidad Cliente en la base de datos.
        // Gracias a @Transactional, si esto falla, la creación del Usuario se revertirá.
        clienteRepository.save(nuevoCliente);
    }
}