package com.example;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class AdminController {
    private JFrame frame;
    private JFrame previousFrame;

    public AdminController(JFrame previousFrame) {
        this.previousFrame = previousFrame;
    }

    public void criarInterface() {
        frame = new JFrame("Administração");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
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

        JButton gerarRelatorioButton = new JButton("Gerar Relatório");
        gerarRelatorioButton.setToolTipText("Clique para gerar um relatório de uso");
        panel.add(gerarRelatorioButton, gbc);

        gerarRelatorioButton.addActionListener(e -> {
            ReservaService reservaService = new ReservaService();
            String relatorio = reservaService.gerarRelatorioDeUso();
            JOptionPane.showMessageDialog(panel, relatorio);
        });

        gbc.gridy++;
        JButton logoutButton = new JButton("Sair");
        logoutButton.setToolTipText("Clique para sair do sistema");
        panel.add(logoutButton, gbc);

        logoutButton.addActionListener(e -> {
            frame.dispose();
            new LoginController(new UsuarioService()).criarInterface();
        });

        gbc.gridy++;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setToolTipText("Clique para voltar à tela anterior");
        panel.add(voltarButton, gbc);

        voltarButton.addActionListener(e -> {
            frame.dispose();
            if (previousFrame != null) {
                previousFrame.setVisible(true);
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
        new AdminController(null).criarInterface();
    }
}
