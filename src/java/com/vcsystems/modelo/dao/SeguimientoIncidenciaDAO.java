/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vcsystems.modelo.dao;

import com.vcsystems.modelo.entidades.SeguimientoIncidencia;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

public class SeguimientoIncidenciaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public List<SeguimientoIncidencia> listar() {
        List<SeguimientoIncidencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM seguimiento_incidencias";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SeguimientoIncidencia si = new SeguimientoIncidencia();
                si.setId_seguimiento(rs.getLong("id_seguimiento"));
                si.setId_incidencia(rs.getLong("id_incidencia"));
                si.setFecha(rs.getDate("fecha").toLocalDate());
                si.setEstado(rs.getString("estado"));
                si.setObservacion(rs.getString("observacion"));
                lista.add(si);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    
    public SeguimientoIncidencia buscarPorId(Long id) {
        SeguimientoIncidencia si = new SeguimientoIncidencia();
        String sql = "SELECT * FROM seguimiento_incidencias WHERE id_seguimiento = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                si.setId_seguimiento(rs.getLong("id_seguimiento"));
                si.setId_incidencia(rs.getLong("id_incidencia"));
                si.setFecha(rs.getDate("fecha").toLocalDate());
                si.setEstado(rs.getString("estado"));
                si.setObservacion(rs.getString("observacion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return si;
    }

    
    public int agregar(SeguimientoIncidencia si) {
        String sql = "INSERT INTO seguimiento_incidencias (id_incidencia, fecha, estado, observacion) VALUES (?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, si.getId_incidencia());
            ps.setDate(2, Date.valueOf(si.getFecha()));
            ps.setString(3, si.getEstado());
            ps.setString(4, si.getObservacion());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    // Actualizar seguimiento
    public int actualizar(SeguimientoIncidencia si) {
        String sql = "UPDATE seguimiento_incidencias SET id_incidencia = ?, fecha = ?, estado = ?, observacion = ? WHERE id_seguimiento = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, si.getId_incidencia());
            ps.setDate(2, Date.valueOf(si.getFecha()));
            ps.setString(3, si.getEstado());
            ps.setString(4, si.getObservacion());
            ps.setLong(5, si.getId_seguimiento());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

   
    public void eliminar(Long id) {
        String sql = "DELETE FROM seguimiento_incidencias WHERE id_seguimiento = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
