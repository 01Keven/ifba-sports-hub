package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

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
        frame.setSize(800, 600); // Tamanho padrão da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true); // Permitir redimensionamento
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

        JButton reservarButton = new JButton("Reservar Horário");
        reservarButton.setToolTipText("Clique para reservar um horário");
        panel.add(reservarButton, gbc);

        reservarButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            new ReservaController(new ReservaService(), usuario, frame).criarInterface();
        });

        gbc.gridy++;
        JButton visualizarButton = new JButton("Ver Reservas");
        visualizarButton.setToolTipText("Clique para ver suas reservas");
        panel.add(visualizarButton, gbc);

        visualizarButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            new CalendarioController(new ReservaService(), frame).criarInterface();
        });

        if ("administrador".equals(usuario.getTipo())) {
            gbc.gridy++;
            JButton relatorioButton = new JButton("Gerar Relatório");
            relatorioButton.setToolTipText("Clique para gerar um relatório de todas as reservas");
            panel.add(relatorioButton, gbc);

            relatorioButton.addActionListener((ActionEvent e) -> {
                String relatorio = new ReservaService().gerarRelatorioDeUso();
                JOptionPane.showMessageDialog(panel, relatorio);
            });
        }

        // Alterar a ordem dos botões "Sair" e "Voltar"
        gbc.gridy++;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setToolTipText("Clique para voltar à tela anterior");
        panel.add(voltarButton, gbc);

        voltarButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            previousFrame.setVisible(true);
        });

        gbc.gridy++;
        JButton logoutButton = new JButton("Sair");
        logoutButton.setToolTipText("Clique para sair do sistema");
        panel.add(logoutButton, gbc);

        logoutButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            new LoginController(new UsuarioService()).criarInterface();
        });
    }

    public static void main(String[] args) {
        // Aplicar tema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new UsuarioController(new Usuario(), null).criarInterface();
    }
}
