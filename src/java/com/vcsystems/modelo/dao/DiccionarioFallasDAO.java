/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vcsystems.modelo.dao;


import com.vcsystems.modelo.entidades.DiccionarioFallas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

public class DiccionarioFallasDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;


    public List<DiccionarioFallas> listar() {
        List<DiccionarioFallas> lista = new ArrayList<>();
        String sql = "SELECT * FROM diccionario_fallas";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DiccionarioFallas df = new DiccionarioFallas();
                df.setId_falla(rs.getLong("id_falla"));
                df.setCodigo_falla(rs.getLong("codigo_falla"));
                df.setDescripcion(rs.getString("descripcion"));
                lista.add(df);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }


    public DiccionarioFallas buscarPorId(Long id) {
        DiccionarioFallas df = new DiccionarioFallas();
        String sql = "SELECT * FROM diccionario_fallas WHERE id_falla = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                df.setId_falla(rs.getLong("id_falla"));
                df.setCodigo_falla(rs.getLong("codigo_falla"));
                df.setDescripcion(rs.getString("descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return df;
    }

  
    public int agregar(DiccionarioFallas df) {
        String sql = "INSERT INTO diccionario_fallas (codigo_falla, descripcion) VALUES (?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, df.getCodigo_falla());
            ps.setString(2, df.getDescripcion());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

 
    public int actualizar(DiccionarioFallas df) {
        String sql = "UPDATE diccionario_fallas SET codigo_falla = ?, descripcion = ? WHERE id_falla = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, df.getCodigo_falla());
            ps.setString(2, df.getDescripcion());
            ps.setLong(3, df.getId_falla());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    
    public void eliminar(Long id) {
        String sql = "DELETE FROM diccionario_fallas WHERE id_falla = ?";
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
