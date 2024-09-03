package com.example;

import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReservaController {
    private final ReservaService reservaService;
    private final Usuario usuario;

    public ReservaController(ReservaService reservaService, Usuario usuario) {
        this.reservaService = reservaService;
        this.usuario = usuario;
    }

    public void criarInterface() {
        JFrame frame = new JFrame("Reserva de Horário");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Evento");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField eventoText = new JTextField(20);
        eventoText.setBounds(100, 20, 165, 25);
        panel.add(eventoText);

        JLabel dateLabel = new JLabel("Data (dd/MM/yyyy)");
        dateLabel.setBounds(10, 50, 150, 25);
        panel.add(dateLabel);

        JTextField dateText = new JTextField(20);
        dateText.setBounds(150, 50, 115, 25);
        panel.add(dateText);

        JButton reservarButton = new JButton("Reservar");
        reservarButton.setBounds(10, 80, 150, 25);
        panel.add(reservarButton);

        reservarButton.addActionListener((ActionEvent e) -> {
            String evento = eventoText.getText();
            @SuppressWarnings("deprecation")
            Date data = new Date(dateText.getText());
            boolean sucesso = reservaService.reservarHorario(evento, data, usuario);
            
            if (sucesso) {
                JOptionPane.showMessageDialog(panel, "Reserva realizada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(panel, "Horário indisponível.");
            }
        });
    }
}
