package com.mycompany.vcsystems.modelo.repository;

import com.mycompany.vcsystems.modelo.entidades.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByDestinatarioAndLeidoFalse(String destinatario);
}
