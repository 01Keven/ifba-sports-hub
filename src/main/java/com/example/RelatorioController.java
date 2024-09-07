package com.example;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RelatorioController {
    private final ReservaService reservaService;
    private JFrame frame;

    public RelatorioController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    public void criarInterface() {
        frame = new JFrame("Relatório de Reservas");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton relatorioButton = new JButton("Gerar Relatório");
        relatorioButton.setBounds(10, 20, 150, 25);
        panel.add(relatorioButton);

        relatorioButton.addActionListener(e -> {
            String relatorio = reservaService.gerarRelatorioDeUso();
            JOptionPane.showMessageDialog(panel, relatorio);
        });
    }
}

