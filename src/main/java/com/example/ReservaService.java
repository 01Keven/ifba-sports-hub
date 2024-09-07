package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservaService {
    private final Map<LocalDate, List<Reserva>> reservasPorDia = new HashMap<>();

    public void adicionarReserva(Reserva reserva) {
        LocalDate data = reserva.getDataHoraInicio().toLocalDate();
        reservasPorDia.computeIfAbsent(data, k -> new ArrayList<>()).add(reserva);
    }

    public String getReservasDoDia(LocalDate dia) {
        List<Reserva> reservas = reservasPorDia.getOrDefault(dia, new ArrayList<>());
        if (reservas.isEmpty()) {
            return "Nenhuma reserva para este dia.";
        }
        return reservas.stream()
                .map(Reserva::toString)
                .collect(Collectors.joining("\n"));
    }
}