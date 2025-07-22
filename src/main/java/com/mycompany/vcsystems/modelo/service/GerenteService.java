package com.mycompany.vcsystems.modelo.service;

import com.mycompany.vcsystems.modelo.entidades.Usuario;
import com.mycompany.vcsystems.modelo.entidades.Usuario.Rol;
import com.mycompany.vcsystems.modelo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GerenteService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> listarGerentes() {
        return usuarioRepository.findAll()
                .stream()
                .filter(usuario -> usuario.getRol() == Rol.GERENTE)
                .collect(Collectors.toList());
    }

    public Optional<Usuario> obtenerGerentePorId(Long id) {
        return usuarioRepository.findById(id)
                .filter(usuario -> usuario.getRol() == Rol.GERENTE);
    }

    public Usuario registrarGerente(Usuario usuario) {
        if (usuario.getRol() == Rol.GERENTE) {
            return usuarioRepository.save(usuario);
        } else {
            throw new IllegalArgumentException("El usuario no tiene rol GERENTE");
        }
    }

    public Optional<Usuario> actualizarGerente(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id)
                .filter(u -> u.getRol() == Rol.GERENTE)
                .map(u -> {
                    usuarioActualizado.setIdUsuario(id);
                    return usuarioRepository.save(usuarioActualizado);
                });
    }

    public boolean eliminarGerente(Long id) {
        return usuarioRepository.findById(id)
                .filter(u -> u.getRol() == Rol.GERENTE)
                .map(u -> {
                    usuarioRepository.deleteById(id);
                    return true;
                }).orElse(false);
    }
}
