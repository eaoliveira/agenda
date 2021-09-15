package com.example.agenda.services;

import com.example.agenda.model.Contato;
import com.example.agenda.model.Email;
import com.example.agenda.model.Endereco;
import com.example.agenda.model.Telefone;

import java.util.List;

public interface ContatoService {

    public Contato adicionaContato(Contato contato);

    public void deletaContato(Integer id);

    public Contato adicionaTelefoneContato(Integer id, Telefone telefone);

    public Contato adicionaEnderecoContato(Integer id, Endereco endereco);

    public Contato adicionaEmailContato(Integer id, Email email);
}
