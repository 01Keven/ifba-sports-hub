package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UsuarioController {
    private final Usuario usuario;
    private JFrame frame;
    private JFrame previousFrame;

    public UsuarioController(Usuario usuario, JFrame previousFrame) {
        this.usuario = usuario;
        this.previousFrame = previousFrame;
    }

    public void criarInterface() {
        frame = new JFrame("Usuário");
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

        JButton reservarButton = new JButton("Reservar Horário");
        reservarButton.setBounds(10, 20, 150, 25);
        panel.add(reservarButton);

        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ReservaController(new ReservaService(), usuario, frame).criarInterface();
            }
        });

        JButton visualizarButton = new JButton("Ver Reservas");
        visualizarButton.setBounds(10, 60, 150, 25);
        panel.add(visualizarButton);

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CalendarioController(new ReservaService(), frame).criarInterface();
            }
        });

        JButton logoutButton = new JButton("Sair");
        logoutButton.setBounds(10, 100, 150, 25);
        panel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginController(new UsuarioService()).criarInterface();
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 140, 150, 25);
        panel.add(voltarButton);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                previousFrame.setVisible(true);
            }
        });
    }
}
