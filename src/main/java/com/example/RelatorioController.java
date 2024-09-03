package com.example;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RelatorioController {
    private final ReservaService reservaService;

    public RelatorioController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    public void criarInterface() {
        JFrame frame = new JFrame("Relatórios de Uso");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel reportLabel = new JLabel("Gerar Relatório");
        reportLabel.setBounds(10, 20, 150, 25);
        panel.add(reportLabel);

        JButton gerarRelatorioButton = new JButton("Gerar");
        gerarRelatorioButton.setBounds(10, 50, 150, 25);
        panel.add(gerarRelatorioButton);

        gerarRelatorioButton.addActionListener(e -> {
            String relatorio = reservaService.gerarRelatorioDeUso();
            JOptionPane.showMessageDialog(panel, relatorio);
        });
    }
}

