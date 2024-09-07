package com.example;

import java.awt.event.ActionEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReservaController {
    private final ReservaService reservaService;
    private final Usuario usuario;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ReservaController(ReservaService reservaService, Usuario usuario) {
        this.reservaService = reservaService;
        this.usuario = usuario;
    }

    public void criarInterface() {
        JFrame frame = new JFrame("Reserva de Horário");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, frame);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel eventoLabel = new JLabel("Evento");
        eventoLabel.setBounds(10, 20, 80, 25);
        panel.add(eventoLabel);

        JTextField eventoText = new JTextField(20);
        eventoText.setBounds(100, 20, 250, 25);
        panel.add(eventoText);

        JLabel dateTimeLabel = new JLabel("Data e Hora (dd/MM/yyyy HH:mm)");
        dateTimeLabel.setBounds(10, 50, 250, 25);
        panel.add(dateTimeLabel);

        JTextField dateTimeText = new JTextField(20);
        dateTimeText.setBounds(260, 50, 150, 25);
        panel.add(dateTimeText);

        JLabel duracaoLabel = new JLabel("Duração (hh:mm)");
        duracaoLabel.setBounds(10, 80, 100, 25);
        panel.add(duracaoLabel);

        JTextField duracaoText = new JTextField(20);
        duracaoText.setBounds(100, 80, 100, 25);
        panel.add(duracaoText);

        JButton reservarButton = new JButton("Reservar");
        reservarButton.setBounds(10, 120, 150, 25);
        panel.add(reservarButton);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(200, 120, 150, 25);
        panel.add(voltarButton);

        reservarButton.addActionListener((ActionEvent e) -> {
            try {
                String evento = eventoText.getText();
                LocalDateTime dataHoraInicio = LocalDateTime.parse(dateTimeText.getText(), dateTimeFormatter);
                String duracaoTexto = duracaoText.getText();
                Duration duracao = Duration.ofHours(Long.parseLong(duracaoTexto.split(":")[0]))
                                            .plusMinutes(Long.parseLong(duracaoTexto.split(":")[1]));
                Reserva reserva = new Reserva(evento, dataHoraInicio, duracao, usuario);
                reservaService.adicionarReserva(reserva);
                JOptionPane.showMessageDialog(panel, "Reserva realizada com sucesso!");
                frame.dispose(); // Fechar a tela de reserva
                new CalendarioController(reservaService).criarInterface(); // Abrir a tela de calendário
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(panel, "Formato de data e hora inválido. Use o formato 'dd/MM/yyyy HH:mm'.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Formato de duração inválido. Use o formato 'hh:mm'.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erro inesperado: " + ex.getMessage());
            }
        });

        voltarButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            new CalendarioController(reservaService).criarInterface(); // Voltar para a tela de calendário
        });
    }
}