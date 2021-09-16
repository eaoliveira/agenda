package com.example.agenda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Contato contato;

    public Endereco(){}

    public Endereco(String rua, String numero, String cidade, String estado, Contato contato) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.contato = contato;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public void setId(int id) {
        this.id = id;
    }
}
