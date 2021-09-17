package com.example.agenda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private TelefoneTipo tipo;
    private Integer DDD;
    private Integer DDI;
    private Long numero;

    @JsonIgnore
    @ManyToOne
    private Contato contato;

    public Telefone(TelefoneTipo tipo, Integer DDD, Integer DDI, Long numero, Contato contato) {
        this.tipo = tipo;
        this.DDD = DDD;
        this.DDI = DDI;
        this.numero = numero;
        this.contato = contato;
    }


    public Telefone(){}

    public Integer getId() {
        return id;
    }

    public TelefoneTipo getTipo() {
        return tipo;
    }

    public Integer getDDD() {
        return DDD;
    }

    public Integer getDDI() {
        return DDI;
    }

    public Long getNumero() {
        return numero;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipo(TelefoneTipo tipo) {
        this.tipo = tipo;
    }

    public void setDDD(Integer DDD) {
        this.DDD = DDD;
    }

    public void setDDI(Integer DDI) {
        this.DDI = DDI;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public void setContato(Contato contato){
        this.contato = contato;
    };


}
