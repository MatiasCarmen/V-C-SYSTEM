package com.mycompany.vcsystems.api.controlador;

import com.mycompany.vcsystems.modelo.entidades.Tecnico;
import com.mycompany.vcsystems.modelo.service.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.mycompany.vcsystems.modelo.service.UsuarioService;

@RestController
@RequestMapping("/api/tecnicos")
@RequiredArgsConstructor
public class TecnicoControlador {
    private final TecnicoService tecnicoService;
    private final UsuarioService usuarioService;

    @GetMapping
    public List<Tecnico> getAll() {
        return tecnicoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> getById(@PathVariable Long id) {
        return tecnicoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tecnico create(@RequestBody Tecnico tecnico) {
        // Buscar el usuario completo por ID
        Long idUsuario = tecnico.getUsuario().getIdUsuario();
        com.mycompany.vcsystems.modelo.entidades.Usuario usuarioCompleto = usuarioService.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        tecnico.setUsuario(usuarioCompleto);
        return tecnicoService.save(tecnico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tecnico> update(@PathVariable Long id, @RequestBody Tecnico tecnico) {
        return tecnicoService.findById(id)
                .map(existing -> {
                    tecnico.setIdTecnico(id);
                    return ResponseEntity.ok(tecnicoService.save(tecnico));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tecnicoService.findById(id).isPresent()) {
            tecnicoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}