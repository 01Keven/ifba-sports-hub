package com.example;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminController {
    private JFrame frame;
    private final JFrame previousFrame;

    public AdminController(JFrame previousFrame) {
        this.previousFrame = previousFrame;
    }

    public void criarInterface() {
        frame = new JFrame("Administração");
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

        JButton gerarRelatorioButton = new JButton("Gerar Relatório");
        gerarRelatorioButton.setBounds(10, 20, 150, 25);
        panel.add(gerarRelatorioButton);

        gerarRelatorioButton.addActionListener((ActionEvent e) -> {
            ReservaService reservaService = new ReservaService();
            String relatorio = reservaService.gerarRelatorioDeUso();
            JOptionPane.showMessageDialog(panel, relatorio);
        });

        JButton logoutButton = new JButton("Sair");
        logoutButton.setBounds(10, 60, 150, 25);
        panel.add(logoutButton);

        logoutButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            new LoginController(new UsuarioService()).criarInterface();
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 100, 150, 25);
        panel.add(voltarButton);

        voltarButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            previousFrame.setVisible(true);
        });
    }
}
