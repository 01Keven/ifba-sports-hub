package com.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UsuarioService {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private static final String FILE_NAME = "usuarios.dat";

    public UsuarioService() {
        carregarUsuarios();
    }

    public Usuario autenticarUsuario(String nomeUsuario, String senha) {
        Usuario usuario = usuarios.get(nomeUsuario);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNome(), usuario);
        salvarUsuarios();
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNome(), usuario);
        salvarUsuarios();
    }

    private void salvarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            usuarios = (Map<String, Usuario>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Arquivo não encontrado, inicializar com uma lista vazia
            usuarios = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
