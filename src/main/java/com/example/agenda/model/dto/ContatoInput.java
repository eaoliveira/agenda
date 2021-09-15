package com.example.agenda.model.dto;

import com.example.agenda.model.Contato;
import com.example.agenda.model.Usuario;

import java.time.LocalDate;

public class ContatoInput {

    private Integer UsuarioId;
    private String Nome;
    private String Sobrenome;
    private String  apelido;
    private LocalDate dataNascimento;

    public ContatoInput(Integer UsuarioId, String nome, String sobrenome, String apelido, LocalDate dataNascimento) {
        this.UsuarioId = UsuarioId;
        Nome = nome;
        Sobrenome = sobrenome;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
    }

    public ContatoInput(){}

    public Integer getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        UsuarioId = usuarioId;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
