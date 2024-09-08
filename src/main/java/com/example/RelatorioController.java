package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton relatorioButton = new JButton("Gerar Relatório");
        relatorioButton.setBounds(10, 20, 150, 25);
        panel.add(relatorioButton);

        relatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String relatorio = reservaService.gerarRelatorioDeUso();
                JOptionPane.showMessageDialog(panel, relatorio);
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 60, 150, 25);
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
