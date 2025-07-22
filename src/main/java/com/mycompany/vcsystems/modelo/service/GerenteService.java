package com.mycompany.vcsystems.modelo.service;

import com.mycompany.vcsystems.modelo.entidades.Gerente;
import com.mycompany.vcsystems.modelo.repository.GerenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GerenteService {
    private final GerenteRepository gerenteRepository;

    public List<Gerente> findAll() {
        return gerenteRepository.findAll();
    }

    public Optional<Gerente> findById(Long id) {
        return gerenteRepository.findById(id);
    }

    public Gerente save(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    public void deleteById(Long id) {
        gerenteRepository.deleteById(id);
    }
}