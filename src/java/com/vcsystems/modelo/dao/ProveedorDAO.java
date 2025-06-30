/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vcsystems.modelo.dao;

import com.vcsystems.modelo.entidades.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

public class ProveedorDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

   
    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId_proveedor(rs.getLong("id_proveedor"));
                p.setNombre_empresa(rs.getString("nombre_empresa"));
                p.setTelefono(rs.getLong("telefono"));
                p.setCorreo(rs.getString("correo"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    
    public Proveedor buscarPorId(Long id) {
        Proveedor p = new Proveedor();
        String sql = "SELECT * FROM proveedores WHERE id_proveedor = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setId_proveedor(rs.getLong("id_proveedor"));
                p.setNombre_empresa(rs.getString("nombre_empresa"));
                p.setTelefono(rs.getLong("telefono"));
                p.setCorreo(rs.getString("correo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

   
    public int agregar(Proveedor p) {
        String sql = "INSERT INTO proveedores (nombre_empresa, telefono, correo) VALUES (?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre_empresa());
            ps.setLong(2, p.getTelefono());
            ps.setString(3, p.getCorreo());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }


    public int actualizar(Proveedor p) {
        String sql = "UPDATE proveedores SET nombre_empresa = ?, telefono = ?, correo = ? WHERE id_proveedor = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre_empresa());
            ps.setLong(2, p.getTelefono());
            ps.setString(3, p.getCorreo());
            ps.setLong(4, p.getId_proveedor());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    
    public void eliminar(Long id) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";
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
