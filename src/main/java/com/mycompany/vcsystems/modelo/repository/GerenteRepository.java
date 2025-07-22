package com.mycompany.vcsystems.modelo.repository;

import com.mycompany.vcsystems.modelo.entidades.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    Gerente findByUsuarioIdUsuario(Long idUsuario);
}