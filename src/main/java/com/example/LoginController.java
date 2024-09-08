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

public class LoginController {
    private final UsuarioService usuarioService;
    private JFrame frame;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void criarInterface() {
        frame = new JFrame("Login");
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

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 100, 150, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBounds(200, 100, 150, 25);
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeUsuario = userText.getText();
                String senha = new String(passwordText.getPassword());
                Usuario usuario = usuarioService.autenticarUsuario(nomeUsuario, senha);
                if (usuario != null) {
                    frame.dispose();
                    if (usuario.getTipo().equals("administrador")) {
                        new AdminController(frame).criarInterface();
                    } else {
                        new UsuarioController(usuario, frame).criarInterface();
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Usuário ou senha inválidos!");
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

        userText.addActionListener(new ActionListener() {
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
