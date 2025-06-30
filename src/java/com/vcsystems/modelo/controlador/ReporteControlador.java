package com.vcsystems.controlador;

import com.vcsystems.modelo.dao.IncidenciaDAO;
import com.vcsystems.modelo.dao.SeguimientoIncidenciaDAO;
import com.vcsystems.modelo.dao.InformeTecnicoDAO;
import com.vcsystems.modelo.entidades.Incidencia;
import com.vcsystems.modelo.entidades.SeguimientoIncidencia;
import com.vcsystems.modelo.entidades.InformeTecnico;
import java.util.List;

public class ReporteControlador {

    private final IncidenciaDAO incidenciaDAO;
    private final SeguimientoIncidenciaDAO seguimientoDAO;
    private final InformeTecnicoDAO informeDAO;

    public ReporteControlador() {
        this.incidenciaDAO = new IncidenciaDAO();
        this.seguimientoDAO = new SeguimientoIncidenciaDAO();
        this.informeDAO = new InformeTecnicoDAO();
    }

    // ===================== INCIDENCIAS =====================
    public List<Incidencia> listarIncidencias() {
        return incidenciaDAO.listar();
    }

    public Incidencia buscarIncidenciaPorId(long id) {
        return incidenciaDAO.buscarPorId(id);
    }

    // ===================== SEGUIMIENTOS =====================
    public List<SeguimientoIncidencia> listarSeguimientos() {
        return seguimientoDAO.listar();
    }

    public SeguimientoIncidencia buscarSeguimientoPorId(Long id) {
        return seguimientoDAO.buscarPorId(id);
    }

    // ===================== INFORMES TÉCNICOS =====================
    public List<InformeTecnico> listarInformes() {
        return informeDAO.listar();
    }

    public InformeTecnico buscarInformePorId(Long id) {
        return informeDAO.buscarPorId(id);
    }

    public boolean registrarInforme(InformeTecnico informe) {
        int resultado = informeDAO.agregar(informe);
        return resultado > 0;
    }

    public boolean actualizarInforme(InformeTecnico informe) {
        int resultado = informeDAO.actualizar(informe);
        return resultado > 0;
    }

    public void eliminarInforme(Long id) {
        informeDAO.eliminar(id);
    }
}
