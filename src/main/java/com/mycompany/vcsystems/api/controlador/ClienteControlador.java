package com.mycompany.vcsystems.api.controlador;

import com.mycompany.vcsystems.modelo.entidades.Cliente;
import com.mycompany.vcsystems.modelo.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {
    private final ClienteRepository clienteRepository;

    public ClienteControlador(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
}