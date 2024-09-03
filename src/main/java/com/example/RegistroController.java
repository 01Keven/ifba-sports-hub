package com.example;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistroController {
    private final UsuarioService usuarioService;
    private JFrame frame;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void criarInterface() {
        frame = new JFrame("Registro de Usuário");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Usuário");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Senha");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBounds(10, 80, 150, 25);
        panel.add(registerButton);

        JButton loginButton = new JButton("Voltar para Login");
        loginButton.setBounds(170, 80, 150, 25);
        panel.add(loginButton);

        registerButton.addActionListener((ActionEvent e) -> {
            String nomeUsuario = userText.getText();
            String senha = new String(passwordText.getPassword());
            Usuario usuario = new Usuario(nomeUsuario, senha, "aluno"); // Definido como aluno por padrão
            usuarioService.cadastrarUsuario(usuario);
            JOptionPane.showMessageDialog(panel, "Usuário registrado com sucesso!");
            frame.dispose(); // Fechar tela de registro
            new LoginController(usuarioService).criarInterface(); // Abrir tela de login
        });

        loginButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            new LoginController(usuarioService).criarInterface(); // Voltar para a tela de login
        });
    }
}
