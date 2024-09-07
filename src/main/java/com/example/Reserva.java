package com.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Reserva {
    private final String evento;
    private final LocalDateTime dataHoraInicio;
    private final Duration duracao;
    private final Usuario usuario;

    public Reserva(String evento, LocalDateTime dataHoraInicio, Duration duracao, Usuario usuario) {
        this.evento = Objects.requireNonNull(evento, "Evento não pode ser nulo");
        this.dataHoraInicio = Objects.requireNonNull(dataHoraInicio, "Data e hora de início não podem ser nulos");
        this.duracao = Objects.requireNonNull(duracao, "Duração não pode ser nula");
        this.usuario = Objects.requireNonNull(usuario, "Usuário não pode ser nulo");
    }

    public String getEvento() {
        return evento;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return String.format("Evento: %s, Início: %s, Duração: %s, Usuário: %s",
                             evento, dataHoraInicio, duracao, usuario.getNome());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return evento.equals(reserva.evento) &&
               dataHoraInicio.equals(reserva.dataHoraInicio) &&
               duracao.equals(reserva.duracao) &&
               usuario.equals(reserva.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evento, dataHoraInicio, duracao, usuario);
    }
}