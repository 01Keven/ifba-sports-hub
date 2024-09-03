package com.example;

import java.util.Date;

public class Reserva {
    private final String evento;
    private final Date data;
    private final Usuario usuario;

    public Reserva(String evento, Date data, Usuario usuario) {
        this.evento = evento;
        this.data = data;
        this.usuario = usuario;
    }

    public String getEvento() {
        return evento;
    }

    public Date getData() {
        return data;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
