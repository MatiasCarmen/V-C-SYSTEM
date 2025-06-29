package com.vcsystems.modelo.controlador;

import com.vcsystems.modelo.dao.SolicitudRepuestoDAO;
import com.vcsystems.modelo.entidades.SolicitudRepuesto;
import java.util.List;

public class SolicitudControlador {

    private SolicitudRepuestoDAO solicitudDAO;

    public SolicitudControlador() {
        this.solicitudDAO = new SolicitudRepuestoDAO();
    }

    // Registrar nueva solicitud de repuesto
    public boolean registrarSolicitud(SolicitudRepuesto sr) {
        int resultado = solicitudDAO.agregar(sr);
        return resultado > 0;
    }

    // Obtener todas las solicitudes
    public List<SolicitudRepuesto> listarSolicitudes() {
        return solicitudDAO.listar();
    }

    // Buscar solicitud por ID
    public SolicitudRepuesto buscarSolicitudPorId(Long id) {
        return solicitudDAO.buscarPorId(id);
    }

    // Actualizar solicitud
    public boolean actualizarSolicitud(SolicitudRepuesto sr) {
        int resultado = solicitudDAO.actualizar(sr);
        return resultado > 0;
    }

    // Eliminar solicitud por ID
    public void eliminarSolicitud(Long id) {
        solicitudDAO.eliminar(id);
    }
}
