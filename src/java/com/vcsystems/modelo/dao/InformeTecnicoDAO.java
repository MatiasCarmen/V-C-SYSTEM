/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vcsystems.modelo.dao;

import com.vcsystems.modelo.entidades.InformeTecnico;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

public class InformeTecnicoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

  
    public List<InformeTecnico> listar() {
        List<InformeTecnico> lista = new ArrayList<>();
        String sql = "SELECT * FROM informes_tecnicos";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                InformeTecnico it = new InformeTecnico();
                it.setId_informe(rs.getLong("id_informe"));
                it.setId_incidencia(rs.getLong("id_incidencia"));
                it.setId_tecnico(rs.getLong("id_tecnico"));
                it.setResumen(rs.getString("resumen"));
                
                Date fechaSQL = rs.getDate("fecha_informe");
                if (fechaSQL != null) {
                    it.setFecha_informe(fechaSQL.toLocalDate());
                }

                lista.add(it);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

   
    public InformeTecnico buscarPorId(Long id) {
        InformeTecnico it = new InformeTecnico();
        String sql = "SELECT * FROM informes_tecnicos WHERE id_informe = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                it.setId_informe(rs.getLong("id_informe"));
                it.setId_incidencia(rs.getLong("id_incidencia"));
                it.setId_tecnico(rs.getLong("id_tecnico"));
                it.setResumen(rs.getString("resumen"));

                Date fechaSQL = rs.getDate("fecha_informe");
                if (fechaSQL != null) {
                    it.setFecha_informe(fechaSQL.toLocalDate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return it;
    }

  
    public int agregar(InformeTecnico it) {
        String sql = "INSERT INTO informes_tecnicos (id_incidencia, id_tecnico, resumen, fecha_informe) VALUES (?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, it.getId_incidencia());
            ps.setLong(2, it.getId_tecnico());
            ps.setString(3, it.getResumen());

            if (it.getFecha_informe() != null) {
                ps.setDate(4, Date.valueOf(it.getFecha_informe()));
            } else {
                ps.setNull(4, Types.DATE);
            }

            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

   
    public int actualizar(InformeTecnico it) {
        String sql = "UPDATE informes_tecnicos SET id_incidencia = ?, id_tecnico = ?, resumen = ?, fecha_informe = ? WHERE id_informe = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, it.getId_incidencia());
            ps.setLong(2, it.getId_tecnico());
            ps.setString(3, it.getResumen());

            if (it.getFecha_informe() != null) {
                ps.setDate(4, Date.valueOf(it.getFecha_informe()));
            } else {
                ps.setNull(4, Types.DATE);
            }

            ps.setLong(5, it.getId_informe());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM informes_tecnicos WHERE id_informe = ?";
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
