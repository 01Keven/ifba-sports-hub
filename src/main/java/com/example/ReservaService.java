package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaService {
    private List<Reserva> reservas = new ArrayList<>();
    private static final String FILE_NAME = "reservas.dat";

    public ReservaService() {
        carregarReservas();
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
        salvarReservas();
    }

    public String gerarRelatorioDeUso() {
        StringBuilder relatorio = new StringBuilder("Relatório de Uso:\n");
        for (Reserva reserva : reservas) {
            relatorio.append("Evento: ").append(reserva.getEvento())
                     .append(" | Data e Hora: ").append(reserva.getDataHoraInicio())
                     .append(" | Duração: ").append(reserva.getDuracao().toHours()).append(" horas e ")
                     .append(reserva.getDuracao().toMinutesPart()).append(" minutos")
                     .append(" | Usuário: ").append(reserva.getUsuario().getNome()).append("\n");
        }
        return relatorio.toString();
    }

    public String getReservasDoDia(LocalDate data) {
        List<Reserva> reservasDoDia = reservas.stream()
                .filter(reserva -> reserva.getDataHoraInicio().toLocalDate().equals(data))
                .collect(Collectors.toList());

        if (reservasDoDia.isEmpty()) {
            return "Nenhuma reserva para o dia " + data.toString();
        }

        StringBuilder relatorio = new StringBuilder("Reservas do dia " + data.toString() + ":\n");
        for (Reserva reserva : reservasDoDia) {
            relatorio.append("Evento: ").append(reserva.getEvento())
                     .append(" | Data e Hora: ").append(reserva.getDataHoraInicio())
                     .append(" | Duração: ").append(reserva.getDuracao().toHours()).append(" horas e ")
                     .append(reserva.getDuracao().toMinutesPart()).append(" minutos")
                     .append(" | Usuário: ").append(reserva.getUsuario().getNome()).append("\n");
        }
        return relatorio.toString();
    }

    private void salvarReservas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarReservas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            reservas = (List<Reserva>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Arquivo não encontrado, inicializar com uma lista vazia
            reservas = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
