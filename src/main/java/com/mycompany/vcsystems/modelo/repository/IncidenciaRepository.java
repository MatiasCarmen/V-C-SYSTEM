package com.mycompany.vcsystems.modelo.repository;

import com.mycompany.vcsystems.modelo.entidades.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 *
 * @author MatiasCarmen
 */
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    @Query("SELECT i FROM Incidencia i WHERE i.estado = :estado")
    List<Incidencia> findByEstado(@Param("estado") com.mycompany.vcsystems.modelo.entidades.Incidencia.Estado estado);

    @Query("SELECT i FROM Incidencia i WHERE i.tecnico.idUsuario = :idTecnico")
    List<Incidencia> findByTecnico_IdUsuario(@Param("idTecnico") Long idTecnico);

    @Query("SELECT i FROM Incidencia i WHERE i.cliente.idCliente = :idCliente")
    List<Incidencia> findByCliente_IdCliente(@Param("idCliente") Long idCliente);

    @Query("SELECT i FROM Incidencia i WHERE i.prioridad = :prioridad")
    List<Incidencia> findByPrioridad(
            @Param("prioridad") com.mycompany.vcsystems.modelo.entidades.Incidencia.Prioridad prioridad);
}
