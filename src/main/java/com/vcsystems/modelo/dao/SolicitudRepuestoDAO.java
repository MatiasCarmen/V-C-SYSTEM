/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vcsystems.modelo.dao;

import com.vcsystems.modelo.entidades.SolicitudRepuesto;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

public class SolicitudRepuestoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;


    public List<SolicitudRepuesto> listar() {
        List<SolicitudRepuesto> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitudes_repuesto";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SolicitudRepuesto sr = new SolicitudRepuesto();
                sr.setId_solicitud(rs.getLong("id_solicitud"));
                sr.setId_incidencia(rs.getLong("id_incidencia"));
                sr.setId_proveedor(rs.getLong("id_proveedor"));
                sr.setDescripcion_repuesto(rs.getString("descripcion_repuesto"));
                sr.setCantidad(rs.getInt("cantidad"));
                Date fecha = rs.getDate("fecha_solicitud");
                if (fecha != null) {
                    sr.setFecha_solicitud(fecha.toLocalDate());
                }
                sr.setEstado(rs.getString("estado"));
                lista.add(sr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }


    public SolicitudRepuesto buscarPorId(Long id) {
        SolicitudRepuesto sr = new SolicitudRepuesto();
        String sql = "SELECT * FROM solicitudes_repuesto WHERE id_solicitud = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                sr.setId_solicitud(rs.getLong("id_solicitud"));
                sr.setId_incidencia(rs.getLong("id_incidencia"));
                sr.setId_proveedor(rs.getLong("id_proveedor"));
                sr.setDescripcion_repuesto(rs.getString("descripcion_repuesto"));
                sr.setCantidad(rs.getInt("cantidad"));
                Date fecha = rs.getDate("fecha_solicitud");
                if (fecha != null) {
                    sr.setFecha_solicitud(fecha.toLocalDate());
                }
                sr.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sr;
    }

    
    public int agregar(SolicitudRepuesto sr) {
        String sql = "INSERT INTO solicitudes_repuesto (id_incidencia, id_proveedor, descripcion_repuesto, cantidad, fecha_solicitud, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, sr.getId_incidencia());
            ps.setLong(2, sr.getId_proveedor());
            ps.setString(3, sr.getDescripcion_repuesto());
            ps.setInt(4, sr.getCantidad());
            if (sr.getFecha_solicitud() != null) {
                ps.setDate(5, Date.valueOf(sr.getFecha_solicitud()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            ps.setString(6, sr.getEstado());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    
    public int actualizar(SolicitudRepuesto sr) {
        String sql = "UPDATE solicitudes_repuesto SET id_incidencia=?, id_proveedor=?, descripcion_repuesto=?, cantidad=?, fecha_solicitud=?, estado=? WHERE id_solicitud=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, sr.getId_incidencia());
            ps.setLong(2, sr.getId_proveedor());
            ps.setString(3, sr.getDescripcion_repuesto());
            ps.setInt(4, sr.getCantidad());
            if (sr.getFecha_solicitud() != null) {
                ps.setDate(5, Date.valueOf(sr.getFecha_solicitud()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            ps.setString(6, sr.getEstado());
            ps.setLong(7, sr.getId_solicitud());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }


    public void eliminar(Long id) {
        String sql = "DELETE FROM solicitudes_repuesto WHERE id_solicitud = ?";
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
