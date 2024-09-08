package com.example;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Reserva implements Serializable {
    private String evento;
    private LocalDateTime dataHoraInicio;
    private Duration duracao;
    private Usuario usuario;

    public Reserva(String evento, LocalDateTime dataHoraInicio, Duration duracao, Usuario usuario) {
        this.evento = evento;
        this.dataHoraInicio = dataHoraInicio;
        this.duracao = duracao;
        this.usuario = usuario;
    }

    // Getters e setters
    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
