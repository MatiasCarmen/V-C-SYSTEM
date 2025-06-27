/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vcsystems.modelo.dao;


import util.Conexion;
import java.sql.*;
import java.util.*;

import com.vcsystems.modelo.entidades.Cliente;

public class ClientesDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Cliente buscarPorId(int id) {
        Cliente c = new Cliente();
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                c.setId_cliente(rs.getLong("id_cliente"));
                c.setId_usuario(rs.getLong("id_usuario"));
                c.setNombre_empresa(rs.getString("nombre_empresa"));
                c.setDireccion_empresa(rs.getString("direccion_empresa"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId_cliente(rs.getLong("id_cliente"));
                c.setId_usuario(rs.getLong("id_usuario"));
                c.setNombre_empresa(rs.getString("nombre_empresa"));
                c.setDireccion_empresa(rs.getString("direccion_empresa"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }


   public int agregar(Cliente cl) {
        String sql = "INSERT INTO clientes(id_usuario, nombre_empresa, direccion_empresa) VALUES(?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, cl.getId_usuario());
            ps.setString(2, cl.getDireccion_empresa());
            ps.setString(3,cl.getNombre_empresa());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public int actualizar(Cliente cl) {
        String sql = "UPDATE clientes SET id_usuario = ?, nombre_empresa = ?, direccion_empresa = ? WHERE id_cliente = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setLong(1, cl.getId_usuario());
            ps.setLong(2, cl.getId_cliente());
            ps.setString(3, cl.getNombre_empresa());
            ps.setString(4, cl.getDireccion_empresa());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

  
    public void eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}