package com.mycompany.vcsystems.modelo.service;

import com.mycompany.vcsystems.modelo.entidades.Tecnico;
import com.mycompany.vcsystems.modelo.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public List<Tecnico> listarTecnicos() {
        return tecnicoRepository.findAll();
    }

    public Optional<Tecnico> obtenerTecnicoPorId(Long id) {
        return tecnicoRepository.findById(id);
    }

    public Tecnico registrarTecnico(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    public Optional<Tecnico> actualizarTecnico(Long id, Tecnico tecnicoActualizado) {
        return tecnicoRepository.findById(id).map(t -> {
            tecnicoActualizado.setIdTecnico(id);
            return tecnicoRepository.save(tecnicoActualizado);
        });
    }

    public boolean eliminarTecnico(Long id) {
        return tecnicoRepository.findById(id).map(t -> {
            tecnicoRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
