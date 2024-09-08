package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private JFrame previousFrame;

    public ReservaController(ReservaService reservaService, Usuario usuario, JFrame previousFrame) {
        this.reservaService = reservaService;
        this.usuario = usuario;
        this.previousFrame = previousFrame;
    }

    public void criarInterface() {
        JFrame frame = new JFrame("Reserva de Horário");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, frame);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel eventoLabel = new JLabel("Evento:");
        eventoLabel.setBounds(10, 20, 80, 25);
        panel.add(eventoLabel);

        JTextField eventoText = new JTextField(20);
        eventoText.setBounds(100, 20, 250, 25);
        panel.add(eventoText);

        JLabel dateLabel = new JLabel("Data (dd/MM/yyyy):");
        dateLabel.setBounds(10, 60, 150, 25);
        panel.add(dateLabel);

        JTextField dateText = new JTextField(10);
        dateText.setBounds(160, 60, 100, 25);
        panel.add(dateText);

        JLabel timeLabel = new JLabel("Hora (HH:mm):");
        timeLabel.setBounds(10, 100, 150, 25);
        panel.add(timeLabel);

        JTextField timeText = new JTextField(10);
        timeText.setBounds(160, 100, 100, 25);
        panel.add(timeText);

        JLabel duracaoLabel = new JLabel("Duração (hh:mm):");
        duracaoLabel.setBounds(10, 140, 150, 25);
        panel.add(duracaoLabel);

        JTextField duracaoText = new JTextField(10);
        duracaoText.setBounds(160, 140, 100, 25);
        panel.add(duracaoText);

        JButton reservarButton = new JButton("Reservar");
        reservarButton.setBounds(10, 180, 150, 25);
        panel.add(reservarButton);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(200, 180, 150, 25);
        panel.add(voltarButton);

        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String evento = eventoText.getText();
                    LocalDate data = LocalDate.parse(dateText.getText(), dateFormatter);
                    LocalTime hora = LocalTime.parse(timeText.getText(), timeFormatter);
                    String duracaoTexto = duracaoText.getText();
                    Duration duracao = Duration.ofHours(Long.parseLong(duracaoTexto.split(":")[0]))
                                                .plusMinutes(Long.parseLong(duracaoTexto.split(":")[1]));
                    LocalDateTime dataHoraInicio = LocalDateTime.of(data, hora);
                    Reserva reserva = new Reserva(evento, dataHoraInicio, duracao, usuario);
                    reservaService.adicionarReserva(reserva);
                    JOptionPane.showMessageDialog(panel, "Reserva realizada com sucesso!");
                    frame.dispose();
                    new CalendarioController(reservaService, frame).criarInterface();
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(panel, "Formato de data ou hora inválido. Use os formatos 'dd/MM/yyyy' e 'HH:mm'.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Formato de duração inválido. Use o formato 'hh:mm'.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Erro inesperado: " + ex.getMessage());
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                previousFrame.setVisible(true);
            }
        });

        eventoText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateText.requestFocus();
            }
        });

        dateText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = dateText.getText();
                long count = text.chars().filter(ch -> ch == '/').count();
                if (count < 2 && text.length() > 0 && text.charAt(text.length() - 1) != '/') {
                    dateText.setText(text + "/");
                } else {
                    timeText.requestFocus();
                }
            }
        });

        timeText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                duracaoText.requestFocus();
            }
        });

        duracaoText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservarButton.doClick();
            }
        });
    }
}
