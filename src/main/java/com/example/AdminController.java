package com.example;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminController {
    private JFrame frame;

    public void criarInterface() {
        frame = new JFrame("Administração");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton gerarRelatorioButton = new JButton("Gerar Relatório");
        gerarRelatorioButton.setBounds(10, 20, 150, 25);
        panel.add(gerarRelatorioButton);

        gerarRelatorioButton.addActionListener((ActionEvent e) -> {
            ReservaService reservaService = new ReservaService(); // Deve ser passado o mesmo objeto usado na aplicação
            String relatorio = reservaService.gerarRelatorioDeUso();
            JOptionPane.showMessageDialog(panel, relatorio);
        });

        JButton logoutButton = new JButton("Sair");
        logoutButton.setBounds(10, 60, 150, 25);
        panel.add(logoutButton);

        logoutButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            new LoginController(new UsuarioService()).criarInterface(); // Retorna para a tela de login
        });
    }
}


