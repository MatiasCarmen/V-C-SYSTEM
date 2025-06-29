package com.vcsystems.modelo.controlador;

import com.vcsystems.modelo.dao.UsuarioDAO;
import com.vcsystems.modelo.entidades.Usuario;
import java.util.List;

public class UsuarioControlador {

    private final UsuarioDAO usuarioDAO;

    public UsuarioControlador() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }

    // Buscar usuario por ID
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioDAO.buscarPorId(id);
    }

    // Registrar nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        int resultado = usuarioDAO.agregar(usuario);
        return resultado > 0;
    }

    // Actualizar usuario existente
    public boolean actualizarUsuario(Usuario usuario) {
        int resultado = usuarioDAO.actualizar(usuario);
        return resultado > 0;
    }

    // Eliminar usuario por ID
    public void eliminarUsuario(Long id) {
        usuarioDAO.eliminar(id);
    }
}