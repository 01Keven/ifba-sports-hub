package com.example;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
<<<<<<< HEAD
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

=======
import java.text.SimpleDateFormat;
import java.util.Date;
>>>>>>> 925dddbde14fd829b71c7eb8a90022659437ef44
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.toedter.calendar.JCalendar;

public class CalendarioController {
    private final ReservaService reservaService;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
<<<<<<< HEAD
            LocalDate dataSelecionada = calendar.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            String reservasDoDia = reservaService.getReservasDoDia(dataSelecionada);
            JOptionPane.showMessageDialog(panel, "Reservas para " + dateFormatter.format(dataSelecionada) + ":\n" + reservasDoDia);
=======
            // Mostrar reservas para o dia selecionado em uma nova tela
            Date dataSelecionada = calendar.getDate();
            mostrarReservasDoDia(dataSelecionada);
>>>>>>> 925dddbde14fd829b71c7eb8a90022659437ef44
        });

        frame.add(panel);
        frame.setVisible(true);
    }
<<<<<<< HEAD
}
=======

    private void mostrarReservasDoDia(Date dataSelecionada) {
        JFrame reservasFrame = new JFrame("Reservas para o Dia Selecionado");
        reservasFrame.setSize(400, 300);
        reservasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String reservasDoDia = reservaService.getReservasDoDia(dataSelecionada);

        String texto = "Reservas para " + sdf.format(dataSelecionada) + ":\n" + reservasDoDia;

        JTextArea textArea = new JTextArea(texto);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton fecharButton = new JButton("Fechar");
        fecharButton.addActionListener(e -> reservasFrame.dispose());
        panel.add(fecharButton, BorderLayout.SOUTH);

        reservasFrame.add(panel);
        reservasFrame.setVisible(true);
    }
}
>>>>>>> 925dddbde14fd829b71c7eb8a90022659437ef44
