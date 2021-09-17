package com.example.agenda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  EmailTipo tipo;
    private  String email;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Contato contato;

    public Email(EmailTipo tipo, String email, Contato contato) {
        this.tipo = tipo;
        this.email = email;
        this.contato = contato;
    }

    public Email(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmailTipo getTipo() {
        return tipo;
    }

    public void setTipo(EmailTipo tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }



}
