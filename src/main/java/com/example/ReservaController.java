package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;

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
        frame.setIconImage(new ImageIcon("path/to/icon.png").getImage()); // Adicionar ícone
        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);
        placeComponents(panel, frame);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel, JFrame frame) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel eventoLabel = new JLabel("Evento:");
        panel.add(eventoLabel, gbc);

        gbc.gridx = 1;
        JTextField eventoText = new JTextField(20);
        panel.add(eventoText, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel dateLabel = new JLabel("Data (dd/MM/yyyy):");
        panel.add(dateLabel, gbc);

        gbc.gridx = 1;
        JTextField dateText = new JTextField(10);
        panel.add(dateText, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel timeLabel = new JLabel("Hora (HH:mm):");
        panel.add(timeLabel, gbc);

        gbc.gridx = 1;
        JTextField timeText = new JTextField(10);
        panel.add(timeText, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel duracaoLabel = new JLabel("Duração (hh:mm):");
        panel.add(duracaoLabel, gbc);

        gbc.gridx = 1;
        JTextField duracaoText = new JTextField(10);
        panel.add(duracaoText, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton reservarButton = new JButton("Reservar");
        reservarButton.setToolTipText("Clique para reservar um horário");
        panel.add(reservarButton, gbc);

        gbc.gridx = 1;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setToolTipText("Clique para voltar à tela anterior");
        panel.add(voltarButton, gbc);

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
                String text = timeText.getText();
                long count = text.chars().filter(ch -> ch == ':').count();
                if (count < 1 && text.length() > 0 && text.charAt(text.length() - 1) != ':') {
                    timeText.setText(text + ":");
                } else {
                    duracaoText.requestFocus();
                }
            }
        });

        duracaoText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = duracaoText.getText();
                long count = text.chars().filter(ch -> ch == ':').count();
                if (count < 1 && text.length() > 0 && text.charAt(text.length() - 1) != ':') {
                    duracaoText.setText(text + ":");
                } else {
                    reservarButton.doClick();
                }
            }
        });
    }

    public static void main(String[] args) {
        // Aplicar tema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new ReservaController(new ReservaService(), new Usuario(), null).criarInterface();
    }
}
