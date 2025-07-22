package com.mycompany.vcsystems.modelo.repository;

import com.mycompany.vcsystems.modelo.entidades.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
    Tecnico findByUsuarioIdUsuario(Long idUsuario);
}