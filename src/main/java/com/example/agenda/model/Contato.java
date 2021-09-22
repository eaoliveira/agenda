package com.example.agenda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario usuario;
//    @JsonIgnore
    @OneToMany(mappedBy = "contato",cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<>();
//    @JsonIgnore
    @OneToMany(mappedBy = "contato",cascade = CascadeType.ALL)
    private List<Email> emails = new ArrayList<>();
//    @JsonIgnore
    @OneToMany(mappedBy = "contato",cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

   private String Nome;
   private String Sobrenome;
   private String  apelido;
   private LocalDate dataNascimento;
   private String foto;

    public Contato(Usuario usuario, String nome, String sobrenome, String apelido, LocalDate dataNascimento) {
        this.usuario = usuario;
        Nome = nome;
        Sobrenome = sobrenome;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
    }

    public Contato(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Telefone adicionaTelefone(Telefone telefone){

        if(telefones.stream().filter(f->f.getNumero().equals(telefone.getNumero())).collect(Collectors.toList()).size()>0){
            return null;
        }

        this.telefones.add(telefone);
        return telefone;
    }

    public Endereco adicionaEndereco(Endereco endereco){


        if(enderecos.stream().filter(f->f.getRua().equals(endereco.getRua()) && f.getNumero().equals(endereco.getNumero())).collect(Collectors.toList()).size()>0){
            return null;
        }

        this.enderecos.add(endereco);
        return endereco;
    }

    public Email adicionaEmail(Email email){

        if(emails.stream().filter(f->f.getEmail().equals(email.getEmail())).collect(Collectors.toList()).size()>0){
            return null;
        }

        this.emails.add(email);
        return email;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", telefones=" + telefones +
                ", emails=" + emails +
                ", enderecos=" + enderecos +
                ", Nome='" + Nome + '\'' +
                ", Sobrenome='" + Sobrenome + '\'' +
                ", apelido='" + apelido + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }

    public void setAgenda(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
