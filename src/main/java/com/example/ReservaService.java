package com.example;

<<<<<<< HEAD
import java.time.LocalDate;
=======
import java.text.SimpleDateFormat;
>>>>>>> 925dddbde14fd829b71c7eb8a90022659437ef44
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
<<<<<<< HEAD
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
=======
import java.util.stream.Collectors;

public class ReservaService {
    private final List<Reserva> reservas = new ArrayList<>();

    public boolean reservarHorario(String evento, Date data, Usuario usuario) {
        for (Reserva reserva : reservas) {
            if (reserva.getData().equals(data)) {
                return false; // Horário já reservado
            }
>>>>>>> 925dddbde14fd829b71c7eb8a90022659437ef44
        }
        return reservas.stream()
                .map(Reserva::toString)
                .collect(Collectors.joining("\n"));
    }
<<<<<<< HEAD
}
=======

    public String getReservasDoDia(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return reservas.stream()
                .filter(r -> sdf.format(r.getData()).equals(sdf.format(data)))
                .map(r -> r.getEvento() + " - " + r.getUsuario().getNome())
                .collect(Collectors.joining("\n"));
    }

    public String gerarRelatorioDeUso() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return reservas.stream()
                .map(r -> r.getEvento() + " - " + sdf.format(r.getData()) + " - " + r.getUsuario().getNome())
                .collect(Collectors.joining("\n"));
    }
}
>>>>>>> 925dddbde14fd829b71c7eb8a90022659437ef44
