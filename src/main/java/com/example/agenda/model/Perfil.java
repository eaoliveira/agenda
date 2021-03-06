package com.example.agenda.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    public Perfil(String nome) {
        this.nome = nome;
    }

    public  Perfil(){}

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String getAuthority() {
        return nome;
    }
}
