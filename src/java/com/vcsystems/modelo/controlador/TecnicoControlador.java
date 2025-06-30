package com.vcsystems.modelo.controlador;

import com.vcsystems.modelo.dao.TecnicoDAO;
import com.vcsystems.modelo.entidades.Tecnico;
import java.util.List;

public class TecnicoControlador {

    private TecnicoDAO tecnicoDAO;

    public TecnicoControlador() {
        this.tecnicoDAO = new TecnicoDAO();
    }

    // Registrar nuevo técnico
    public boolean registrarTecnico(Long idUsuario, String especialidad) {
        Tecnico tecnico = new Tecnico();
        tecnico.setId_usuario(idUsuario);
        tecnico.setEspecialidad(especialidad);
        int resultado = tecnicoDAO.agregar(tecnico);
        return resultado > 0;
    }

    // Listar todos los técnicos
    public List<Tecnico> listarTecnicos() {
        return tecnicoDAO.listar();
    }

    // Buscar técnico por ID
    public Tecnico obtenerTecnicoPorId(Long idTecnico) {
        return tecnicoDAO.buscarPorId(idTecnico);
    }

    // Actualizar datos de un técnico
    public boolean actualizarTecnico(Long idTecnico, Long idUsuario, String especialidad) {
        Tecnico tecnico = new Tecnico();
        tecnico.setIdTecnico(idTecnico);
        tecnico.setId_usuario(idUsuario);
        tecnico.setEspecialidad(especialidad);
        int resultado = tecnicoDAO.actualizar(tecnico);
        return resultado > 0;
    }

    // Eliminar técnico por ID
    public boolean eliminarTecnico(Long idTecnico) {
        try {
            tecnicoDAO.eliminar(idTecnico);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
