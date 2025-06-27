/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vcsystems.modelo.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.vcsystems.modelo.entidades.Incidencia;
import util.Conexion;

public class IncidenciaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

  
    public List<Incidencia> listar() {
        List<Incidencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM incidencias";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Incidencia i = new Incidencia();
                i.setId_incidencia(rs.getLong("id_incidencia"));
                i.setId_cliente(rs.getLong("id_cliente"));
                i.setId_tecnico(rs.getLong("id_tecnico"));
                i.setId_falla(rs.getLong("id_falla"));
                i.setDescripcion(rs.getString("descripcion"));
                i.setFecha_reporte(rs.getDate("fecha_reporte").toLocalDate());
                i.setEstado(rs.getString("estado"));
                lista.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

   
    public Incidencia buscarPorId(long id) {
        Incidencia i = new Incidencia();
        String sql = "SELECT * FROM incidencias WHERE id_incidencia = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                i.setId_incidencia(rs.getLong("id_incidencia"));
                i.setId_cliente(rs.getLong("id_cliente"));
                i.setId_tecnico(rs.getLong("id_tecnico"));
                i.setId_falla(rs.getLong("id_falla"));
                i.setDescripcion(rs.getString("descripcion"));
                i.setFecha_reporte(rs.getDate("fecha_reporte").toLocalDate());
                i.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

   
    public int agregar(Incidencia i) {
        String sql = "INSERT INTO incidencias (id_cliente, id_tecnico, id_falla, descripcion, fecha_reporte, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, i.getId_cliente());
            ps.setLong(2, i.getId_tecnico());
            ps.setLong(3, i.getId_falla());
            ps.setString(4, i.getDescripcion());
            ps.setDate(5, Date.valueOf(i.getFecha_reporte()));
            ps.setString(6, i.getEstado());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

  
    public int actualizar(Incidencia i) {
        String sql = "UPDATE incidencias SET id_cliente = ?, id_tecnico = ?, id_falla = ?, descripcion = ?, fecha_reporte = ?, estado = ? WHERE id_incidencia = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, i.getId_cliente());
            ps.setLong(2, i.getId_tecnico());
            ps.setLong(3, i.getId_falla());
            ps.setString(4, i.getDescripcion());
            ps.setDate(5, Date.valueOf(i.getFecha_reporte()));
            ps.setString(6, i.getEstado());
            ps.setLong(7, i.getId_incidencia());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public void eliminar(long id) {
        String sql = "DELETE FROM incidencias WHERE id_incidencia = ?";
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
