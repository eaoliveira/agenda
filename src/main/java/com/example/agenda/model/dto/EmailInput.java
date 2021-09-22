package com.example.agenda.model.dto;


public class EmailInput {

    private String destinatario;
    private String corpo;
    private String assunto;

    public EmailInput(String destinatario, String assunto) {
        this.destinatario = destinatario;
        this.assunto = assunto;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getCorpo() {
        return corpo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }
}
