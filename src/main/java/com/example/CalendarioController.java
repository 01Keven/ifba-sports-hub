package com.example;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;

public class CalendarioController {
    private final ReservaService reservaService;

    public CalendarioController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    public void criarInterface() {
        JFrame frame = new JFrame("Calendário de Reservas");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JCalendar calendar = new JCalendar();
        panel.add(calendar, BorderLayout.CENTER);

        JButton verReservasButton = new JButton("Ver Reservas");
        panel.add(verReservasButton, BorderLayout.SOUTH);

        verReservasButton.addActionListener((ActionEvent e) -> {
            // Mostrar reservas para o dia selecionado
            Date dataSelecionada = calendar.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String reservasDoDia = reservaService.getReservasDoDia(dataSelecionada);
            JOptionPane.showMessageDialog(panel, "Reservas para " + sdf.format(dataSelecionada) + ":\n" + reservasDoDia);
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
