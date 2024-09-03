package com.example;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private final List<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario autenticarUsuario(String nome, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
}
