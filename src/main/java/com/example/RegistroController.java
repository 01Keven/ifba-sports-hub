package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RegistroController {
    private final UsuarioService usuarioService;
    private JFrame frame;
    private JFrame previousFrame;

    public RegistroController(UsuarioService usuarioService, JFrame previousFrame) {
        this.usuarioService = usuarioService;
        this.previousFrame = previousFrame;
    }

    public void criarInterface() {
        frame = new JFrame("Registro de Usuário");
        frame.setSize(800, 600); // Tamanho padrão da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true); // Permitir redimensionamento
        frame.setIconImage(new ImageIcon("path/to/icon.png").getImage()); // Adicionar ícone
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

        JLabel userLabel = new JLabel("Usuário:");
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        JTextField userText = new JTextField(20);
        panel.add(userText, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email:");
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        JTextField emailText = new JTextField(20);
        panel.add(emailText, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Senha:");
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordText = new JPasswordField(20);
        panel.add(passwordText, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton registerButton = new JButton("Registrar");
        registerButton.setToolTipText("Clique para registrar um novo usuário");
        panel.add(registerButton, gbc);

        gbc.gridx = 1;
        JButton loginButton = new JButton("Voltar para Login");
        loginButton.setToolTipText("Clique para voltar à tela de login");
        panel.add(loginButton, gbc);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeUsuario = userText.getText().trim();
                String email = emailText.getText().trim();
                String senha = new String(passwordText.getPassword()).trim();
                
                if (nomeUsuario.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Nome de usuário, email e senha não podem estar vazios.");
                    return;
                }

                Usuario usuario = new Usuario(nomeUsuario, senha, "aluno");
                usuario.setEmail(email);
                usuarioService.cadastrarUsuario(usuario);
                JOptionPane.showMessageDialog(panel, "Usuário registrado com sucesso!");
                frame.dispose();
                new LoginController(usuarioService).criarInterface();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                previousFrame.setVisible(true);
            }
        });

        userText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailText.requestFocus();
            }
        });

        emailText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordText.requestFocus();
            }
        });

        passwordText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerButton.doClick();
            }
        });
    }

    public static void main(String[] args) {
        // Aplicar tema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RegistroController(new UsuarioService(), null).criarInterface();
    }
}
