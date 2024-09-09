package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class RelatorioController {
    private final ReservaService reservaService;
    private JFrame frame;
    private JFrame previousFrame;

    public RelatorioController(ReservaService reservaService, JFrame previousFrame) {
        this.reservaService = reservaService;
        this.previousFrame = previousFrame;
    }

    public void criarInterface() {
        frame = new JFrame("Relatório de Reservas");
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

        JButton relatorioButton = new JButton("Gerar Relatório");
        relatorioButton.setToolTipText("Clique para gerar um relatório de uso");
        panel.add(relatorioButton, gbc);

        relatorioButton.addActionListener((ActionEvent e) -> {
            String relatorio = reservaService.gerarRelatorioDeUso();
            JOptionPane.showMessageDialog(panel, relatorio);
        });

        gbc.gridy++;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setToolTipText("Clique para voltar à tela anterior");
        panel.add(voltarButton, gbc);

        voltarButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            previousFrame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        // Aplicar tema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RelatorioController(new ReservaService(), null).criarInterface();
    }
}
