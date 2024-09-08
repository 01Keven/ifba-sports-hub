package com.example;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JCalendar calendar = new JCalendar();
        panel.add(calendar, BorderLayout.CENTER);

        JButton verReservasButton = new JButton("Ver Reservas");
        panel.add(verReservasButton, BorderLayout.SOUTH);

        verReservasButton.addActionListener((ActionEvent e) -> {
            LocalDate dataSelecionada = calendar.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            String reservasDoDia = reservaService.getReservasDoDia(dataSelecionada);
            JOptionPane.showMessageDialog(panel, reservasDoDia);
        });

        JButton voltarButton = new JButton("Voltar");
        panel.add(voltarButton, BorderLayout.NORTH);

        voltarButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            previousFrame.setVisible(true);
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
