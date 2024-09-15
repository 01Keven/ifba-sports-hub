package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginController {
    private final UsuarioService usuarioService;
    private JFrame frame;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void criarInterface() {
        frame = new JFrame("Login");
        frame.setSize(800, 600); // Tamanho padrão da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true); // Permitir redimensionamento

        // Definir o ícone da janela
        frame.setIconImage(new ImageIcon(getClass().getResource("/icons/favicon.png")).getImage());

        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);
        placeComponents(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel identifierLabel = new JLabel("Usuário ou Email:");
        panel.add(identifierLabel, gbc);

        gbc.gridx = 1;
        JTextField identifierText = new JTextField(20);
        panel.add(identifierText, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Senha:");
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordText = new JPasswordField(20);
        panel.add(passwordText, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton loginButton = new JButton("Login");
        loginButton.setToolTipText("Clique para fazer login");
        panel.add(loginButton, gbc);

        gbc.gridx = 1;
        JButton registerButton = new JButton("Registrar");
        registerButton.setToolTipText("Clique para registrar um novo usuário");
        panel.add(registerButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificador = identifierText.getText().trim();
                String senha = new String(passwordText.getPassword()).trim();
                
                if (identificador.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Usuário/Email e senha não podem estar vazios.");
                    return;
                }

                Usuario usuario = usuarioService.autenticarUsuario(identificador, senha);
                if (usuario != null) {
                    JOptionPane.showMessageDialog(panel, "Login bem-sucedido!");
                    frame.dispose();
                    new UsuarioController(usuario, frame).criarInterface();
                } else {
                    JOptionPane.showMessageDialog(panel, "Usuário/Email ou senha incorretos.");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new RegistroController(usuarioService, frame).criarInterface();
            }
        });

        identifierText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordText.requestFocus();
            }
        });

        passwordText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });
    }
}
