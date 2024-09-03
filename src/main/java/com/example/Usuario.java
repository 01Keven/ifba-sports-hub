package com.example;

public class Usuario {
    private final String nome;
    private final String senha;
    private final String tipo; // Pode ser "aluno", "administrador", "coordenador"
    private final String email;

    public Usuario(String nome, String senha, String tipo) {
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
        this.email = nome + "@ifba.edu.br"; // Exemplo de geração de e-mail
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEmail() {
        return email;
    }
}
