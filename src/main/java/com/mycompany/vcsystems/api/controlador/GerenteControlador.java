package com.mycompany.vcsystems.api.controlador;

import com.mycompany.vcsystems.modelo.entidades.Gerente;
import com.mycompany.vcsystems.modelo.entidades.Usuario;
import com.mycompany.vcsystems.modelo.service.GerenteService;
import com.mycompany.vcsystems.modelo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gerentes")
@RequiredArgsConstructor
public class GerenteControlador {
    private final GerenteService gerenteService;
    private final UsuarioService usuarioService;

    @GetMapping
    public List<Gerente> getAll() {
        return gerenteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gerente> getById(@PathVariable Long id) {
        return gerenteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gerente create(@RequestBody Gerente gerente) {
        // Buscar el usuario completo por ID
        Long idUsuario = gerente.getUsuario().getIdUsuario();
        com.mycompany.vcsystems.modelo.entidades.Usuario usuarioCompleto = usuarioService.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        gerente.setUsuario(usuarioCompleto);
        return gerenteService.save(gerente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gerente> update(@PathVariable Long id, @RequestBody Gerente gerente) {
        return gerenteService.findById(id)
                .map(existing -> {
                    gerente.setIdGerente(id);
                    return ResponseEntity.ok(gerenteService.save(gerente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (gerenteService.findById(id).isPresent()) {
            gerenteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Gestión de usuarios (listar todos)
    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }
}