package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import javax.swing.*;
import com.toedter.calendar.JCalendar;

public class CalendarioController {
    private final ReservaService reservaService;
    private JFrame previousFrame;

    public CalendarioController(ReservaService reservaService, JFrame previousFrame) {
        this.reservaService = reservaService;
        this.previousFrame = previousFrame;
    }

    public void criarInterface() {
        JFrame frame = new JFrame("Calendário de Reservas");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("path/to/icon.png").getImage()); // Adicionar ícone
        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        JCalendar calendar = new JCalendar();
        panel.add(calendar, BorderLayout.CENTER);

        JButton verReservasButton = new JButton("Ver Reservas");
        verReservasButton.setToolTipText("Clique para ver as reservas do dia selecionado");
        panel.add(verReservasButton, BorderLayout.SOUTH);

        verReservasButton.addActionListener((ActionEvent e) -> {
            LocalDate dataSelecionada = calendar.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            String reservasDoDia = reservaService.getReservasDoDia(dataSelecionada);
            JOptionPane.showMessageDialog(panel, reservasDoDia);
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setToolTipText("Clique para voltar à tela anterior");
        panel.add(voltarButton, BorderLayout.NORTH);

        voltarButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            previousFrame.setVisible(true);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Aplicar tema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new CalendarioController(new ReservaService(), null).criarInterface();
    }
}
