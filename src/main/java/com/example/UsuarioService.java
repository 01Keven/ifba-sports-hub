package com.example;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private final List<Usuario> usuarios = new ArrayList<>();

    public Usuario autenticarUsuario(String nome, String senha) {
        return usuarios.stream()
                .filter(u -> u.getNome().equals(nome) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
}
