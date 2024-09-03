package com.example;

public class Main {
    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();

        // Adicionar um usuário admin por padrão
        Usuario admin = new Usuario("admin", "admin123", "administrador");
        usuarioService.cadastrarUsuario(admin);

        // Abrir tela de login
        new LoginController(usuarioService).criarInterface();
    }
}
