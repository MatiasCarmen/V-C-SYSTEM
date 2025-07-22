package com.mycompany.vcsystems.modelo.service;

import com.mycompany.vcsystems.modelo.entidades.Tecnico;
import com.mycompany.vcsystems.modelo.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Optional<Tecnico> findById(Long id) {
        return tecnicoRepository.findById(id);
    }

    public Tecnico save(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    public void deleteById(Long id) {
        tecnicoRepository.deleteById(id);
    }
}