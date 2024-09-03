package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaService {
    private final List<Reserva> reservas;

    public ReservaService() {
        this.reservas = new ArrayList<>();
    }

    public boolean reservarHorario(String evento, Date data, Usuario usuario) {
        // Verifica se já existe uma reserva para o mesmo horário
        for (Reserva reserva : reservas) {
            if (reserva.getData().equals(data)) {
                return false;
            }
        }
        reservas.add(new Reserva(evento, data, usuario));
        return true;
    }

    public String getReservasDoDia(Date data) {
        StringBuilder sb = new StringBuilder();
        for (Reserva reserva : reservas) {
            if (reserva.getData().equals(data)) {
                sb.append("Evento: ").append(reserva.getEvento())
                  .append(" - Reservado por: ").append(reserva.getUsuario().getNome()).append("\n");
            }
        }
        return sb.toString();
    }

    public String gerarRelatorioDeUso() {
        StringBuilder sb = new StringBuilder();
        sb.append("Relatório de Uso da Quadra:\n");
        for (Reserva reserva : reservas) {
            sb.append("Evento: ").append(reserva.getEvento())
              .append(" - Data: ").append(reserva.getData())
              .append(" - Reservado por: ").append(reserva.getUsuario().getNome()).append("\n");
        }
        return sb.toString();
    }
}
