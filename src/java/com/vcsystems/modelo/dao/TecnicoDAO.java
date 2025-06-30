/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vcsystems.modelo.dao;



import com.vcsystems.modelo.entidades.Tecnico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

public class TecnicoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

  
    public List<Tecnico> listar() {
        List<Tecnico> lista = new ArrayList<>();
        String sql = "SELECT * FROM tecnicos";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tecnico t = new Tecnico();
                t.setIdTecnico(rs.getLong("id_tecnico"));
                t.setId_usuario(rs.getLong("id_usuario"));
                t.setEspecialidad(rs.getString("especialidad"));
                lista.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }


    public Tecnico buscarPorId(Long id) {
        Tecnico t = new Tecnico();
        String sql = "SELECT * FROM tecnicos WHERE id_tecnico = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                t.setIdTecnico(rs.getLong("id_tecnico"));
                t.setId_usuario(rs.getLong("id_usuario"));
                t.setEspecialidad(rs.getString("especialidad"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public int agregar(Tecnico t) {
        String sql = "INSERT INTO tecnicos (id_usuario, especialidad) VALUES (?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, t.getId_usuario());
            ps.setString(2, t.getEspecialidad());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    
    public int actualizar(Tecnico t) {
        String sql = "UPDATE tecnicos SET id_usuario = ?, especialidad = ? WHERE id_tecnico = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, t.getId_usuario());
            ps.setString(2, t.getEspecialidad());
            ps.setLong(3, t.getId_tecnico());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }


    public void eliminar(Long id) {
        String sql = "DELETE FROM tecnicos WHERE id_tecnico = ?";
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
