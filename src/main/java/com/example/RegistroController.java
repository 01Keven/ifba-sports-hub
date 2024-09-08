package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JFrame previousFrame;

    public RegistroController(UsuarioService usuarioService, JFrame previousFrame) {
        this.usuarioService = usuarioService;
        this.previousFrame = previousFrame;
    }

    public void criarInterface() {
        frame = new JFrame("Registro de Usuário");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Usuário");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 250, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Senha");
        passwordLabel.setBounds(10, 60, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 60, 250, 25);
        panel.add(passwordText);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBounds(10, 100, 150, 25);
        panel.add(registerButton);

        JButton loginButton = new JButton("Voltar para Login");
        loginButton.setBounds(200, 100, 150, 25);
        panel.add(loginButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeUsuario = userText.getText();
                String senha = new String(passwordText.getPassword());
                Usuario usuario = new Usuario(nomeUsuario, senha, "aluno");
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
}
