package com.example;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UsuarioController {
    private final Usuario usuario;
    private JFrame frame;

    public UsuarioController(Usuario usuario) {
        this.usuario = usuario;
    }

    public void criarInterface() {
        frame = new JFrame("Usuário");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton reservarButton = new JButton("Reservar Horário");
        reservarButton.setBounds(10, 20, 150, 25);
        panel.add(reservarButton);

        reservarButton.addActionListener((ActionEvent e) -> {
            new ReservaController(new ReservaService(), usuario).criarInterface(); // Abre a tela de reserva
        });

        JButton visualizarButton = new JButton("Ver Reservas");
        visualizarButton.setBounds(10, 60, 150, 25);
        panel.add(visualizarButton);

        visualizarButton.addActionListener((ActionEvent e) -> {
            new CalendarioController(new ReservaService()).criarInterface(); // Abre o calendário
        });

        JButton logoutButton = new JButton("Sair");
        logoutButton.setBounds(10, 100, 150, 25);
        panel.add(logoutButton);

        logoutButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            new LoginController(new UsuarioService()).criarInterface(); // Retorna para a tela de login
        });
    }
}


