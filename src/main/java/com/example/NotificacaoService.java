package com.example;



public class NotificacaoService {

    public void enviarConfirmacao(Usuario usuario, String mensagem) {
        // Simulação de envio de email ou notificação
        System.out.println("Enviando email para " + usuario.getEmail() + ": " + mensagem);
    }

    public void enviarLembrete(Usuario usuario, String mensagem) {
        // Simulação de envio de lembrete
        System.out.println("Enviando lembrete para " + usuario.getEmail() + ": " + mensagem);
    }
}
